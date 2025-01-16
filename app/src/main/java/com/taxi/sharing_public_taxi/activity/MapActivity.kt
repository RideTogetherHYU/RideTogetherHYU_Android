package com.taxi.sharing_public_taxi.activity

import android.Manifest
import android.annotation.SuppressLint
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.taxi.sharing_public_taxi.R
import com.taxi.sharing_public_taxi.databinding.ActivityMapBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Call
import okhttp3.Callback
import java.io.IOException
import org.json.JSONObject

internal class MapActivity : AppCompatActivity(), OnMapReadyCallback {


    companion object {
        const val TAG = "MapActivity"
        val DEFAULT_LOCATION = LatLng(37.297561, 126.835465) // 서울역
    }

    lateinit var binding: ActivityMapBinding
    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private var currentMarker: Marker? = null

    private lateinit var locationPermission: ActivityResultLauncher<Array<String>>
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    private var selectedMode: String = "walking" // 기본값: 자동차

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this@MapActivity)

        // Spinner 초기화
        val modeSpinner = findViewById<Spinner>(R.id.modeSpinner)
        modeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedMode = when (position) {
                    0 -> "driving"   // 자동차
                    1 -> "walking"   // 도보
                    2 -> "bicycling" // 자전거
                    3 -> "transit"   // 대중교통
                    else -> "driving"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // 기본값 유지
            }
        }

        // 위치 권한 요청
        locationPermission = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (permissions.all { it.value }) {
                startLocationUpdates()
            } else {
                Toast.makeText(this, "권한 거부됨. 기본 위치를 표시합니다.", Toast.LENGTH_LONG).show()
                updateDefaultLocation()
            }
        }

        locationPermission.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }


    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap

        // 지도 ui 설정 활성화 (확대 축소 버튼 우측 하단)
        googleMap.uiSettings.isZoomControlsEnabled = true // 확대 / 축소 버튼 표시
        googleMap.uiSettings.isZoomGesturesEnabled = true // 제스처로 확대/축소 가능

        // 기본 위치에서 목적지까지 경로 요청
        val destinationLatLng = LatLng(37.300120, 126.837648) // 목적지
        requestDirections(DEFAULT_LOCATION, destinationLatLng, "transit")

    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val locationRequest = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 1000
        }

        val destinationLatLng = LatLng(37.300120, 126.837648) // 목적지

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.locations.lastOrNull()?.let { location ->
                    val currentLatLng = LatLng(location.latitude, location.longitude)
                    updateCurrentLocation(location)

                    // Directions API로 경로 요청
                    requestDirections(currentLatLng, destinationLatLng, selectedMode)
                }
            }
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }



    private fun updateCurrentLocation(location: Location) {
        val currentLatLng = LatLng(location.latitude, location.longitude)
        val markerOptions = MarkerOptions().position(currentLatLng).title("현재 위치")

        // 기존 마커 제거
        currentMarker?.remove()
        currentMarker = googleMap.addMarker(markerOptions)

        // 카메라 업데이트
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
    }

    private fun updateDefaultLocation() {
        val markerOptions = MarkerOptions().position(DEFAULT_LOCATION).title("기본 위치 (에리카)")

        // 기존 마커 제거
        currentMarker?.remove()
        currentMarker = googleMap.addMarker(markerOptions)

        // 카메라 업데이트
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION, 15f))
    }

    private fun requestDirections(origin: LatLng, destination: LatLng, mode: String) {
        val apiKey = getString(R.string.scheme_google_direction_key)
        val url = "https://maps.googleapis.com/maps/api/directions/json?" +
                "origin=${origin.latitude},${origin.longitude}" +
                "&destination=${destination.latitude},${destination.longitude}" +
                "&mode=$mode" +
                "&key=$apiKey"

        Log.d("DirectionsURL", "Request URL: $url") // 요청 URL 확인

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("DirectionsError", "Request failed: ${e.message}")
                runOnUiThread {
                    Toast.makeText(this@MapActivity, "경로 요청 실패: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    Log.e("DirectionsError", "Unsuccessful response: ${response.message}")
                    runOnUiThread {
                        Toast.makeText(this@MapActivity, "응답 실패: ${response.message}", Toast.LENGTH_LONG).show()
                    }
                    return
                }

                response.body?.string()?.let { responseBody ->
                    Log.d("DirectionsResponse", "Response: $responseBody") // 응답 확인
                    parseAndDisplayRoute(responseBody)
                }
            }
        })
    }



    private fun parseAndDisplayRoute(jsonData: String) {
        try {
            val jsonObject = JSONObject(jsonData)
            val routes = jsonObject.optJSONArray("routes") ?: return

            if (routes.length() > 0) {
                val legs = routes.getJSONObject(0).optJSONArray("legs") ?: return
                val steps = legs.getJSONObject(0).optJSONArray("steps") ?: return

                val path = mutableListOf<LatLng>()
                for (i in 0 until steps.length()) {
                    val step = steps.getJSONObject(i)
                    val travelMode = step.optString("travel_mode")

                    // WALKING 모드만 필터링
                    if (travelMode == "WALKING") {
                        val polyline = step.getJSONObject("polyline").getString("points")
                        path.addAll(decodePolyline(polyline))
                    }
                }

                runOnUiThread {
                    googleMap.clear() // 기존 경로 제거
                    googleMap.addPolyline(
                        PolylineOptions()
                            .addAll(path)
                            .color(Color.BLUE)
                            .width(10f)
                    )
                }
            } else {
                runOnUiThread {
                    Toast.makeText(this@MapActivity, "경로를 찾을 수 없습니다.", Toast.LENGTH_LONG).show()
                }
            }
        } catch (e: Exception) {
            Log.e("ParseRouteError", "Error parsing route: ${e.message}")
        }
    }



    private fun decodePolyline(encoded: String): List<LatLng> {
        val poly = ArrayList<LatLng>()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0

        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat

            shift = 0
            result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng

            val p = LatLng((lat / 1E5), (lng / 1E5))
            poly.add(p)
        }
        return poly
    }


    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
        if (::locationCallback.isInitialized) {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }
}
