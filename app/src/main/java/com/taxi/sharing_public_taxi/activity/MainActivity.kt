package com.taxi.sharing_public_taxi.activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.taxi.sharing_public_taxi.fragment.AddFragment
import com.taxi.sharing_public_taxi.CustomDialog
import com.taxi.sharing_public_taxi.fragment.HomeFragment
import com.taxi.sharing_public_taxi.R
import com.taxi.sharing_public_taxi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var isPlusButtonClicked = false // 플러스 버튼 클릭 상태

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setBottomNavigationView()

        // 앱 초기 실행 시 홈화면으로 설정
        if (savedInstanceState == null) {
            binding.bottomNavigationView.selectedItemId = R.id.fragment_home
        }

        val fragmentToLoad = intent.getStringExtra("fragmentToLoad")
        if (fragmentToLoad == "home") {
            // HomeFragment를 로드
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        } else {
            // 기본 Fragment 로드 (예: HomeFragment 또는 다른 Fragment)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        // 플러스 버튼을 찾아서 클릭 리스너 설정
        val plusButton: FloatingActionButton = binding.fabPlus // 바인딩 객체에서 FAB 가져오기
        plusButton.setOnClickListener {
            // 다이얼로그를 표시하는 함수 호출
            showMatchingDialog(plusButton) // plusButton을 전달하여 클릭 상태를 유지

            // 클릭 상태에 따라 아이콘 변경
            if (isPlusButtonClicked) {
                plusButton.setImageResource(R.drawable.ic_plus) // 기본 아이콘으로 변경
                plusButton.backgroundTintList = ContextCompat.getColorStateList(this,
                    R.color.nav_icon_color
                ) // 원래 색상으로 변경
                isPlusButtonClicked = false // 상태 초기화
            } else {
                plusButton.setImageResource(R.drawable.ic_cancel) // 클릭 시 아이콘으로 변경
                plusButton.backgroundTintList = ContextCompat.getColorStateList(this, R.color.gray) // 클릭 시 색상 변경
                isPlusButtonClicked = true // 상태 변경
            }
        }
    }

//    fun setBottomNavigationView() {
//        binding.bottomNavigationView.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.fragment_home -> {
//                    supportFragmentManager.beginTransaction().replace(R.id.main_container, HomeFragment()).commit()
//                    true
//                }
//                R.id.fragment_add -> {
//                    supportFragmentManager.beginTransaction().replace(R.id.main_container, AddFragment()).commit()
//                    true
//                }
//                R.id.fragment_mypage -> {
//                    supportFragmentManager.beginTransaction().replace(R.id.main_container, MyPageFragment()).commit()
//                    true
//                }
//                else -> false
//            }
//        }
//    }

    fun setBottomNavigationView() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.fragment_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, HomeFragment())
                        .commit()
                    true
                }
                R.id.fragment_add -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, AddFragment())
                        .commit()
                    true
                }
//                R.id.menu_mypage -> { // XML에 정의된 ID와 일치시킴
//                    val intent = Intent(this@MainActivity, MyPageActivity::class.java)
//                    startActivity(intent)
//                    true
//                }
                else -> false
            }
        }
    }


//    private fun loadFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.main_container, fragment)
//            .commit()
//    }

    private fun showMatchingDialog(plusButton: FloatingActionButton) {
        val alertDialog = CustomDialog(this) // this로 변경하여 Context 전달
        alertDialog.setDialogTitle("매칭을 진행하겠습니까?")
        alertDialog.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        val layoutParams = alertDialog.window?.attributes
        layoutParams?.dimAmount = 0.5f // 흐림 정도 설정 (0.0 - 1.0)
        alertDialog.window?.attributes = layoutParams

        alertDialog.setItemClickListener(object : CustomDialog.ItemClickListener {
            override fun onYesClick() {
                // AddFragment로 이동하는 코드
                val addFragment = AddFragment() // AddFragment 인스턴스 생성
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, addFragment) // 실제 Fragment의 container ID를 넣어야 합니다.
                    .addToBackStack(null) // 뒤로가기 시 이전 Fragment로 돌아올 수 있도록
                    .commit()
            }

            override fun onNoClick() {
                Toast.makeText(this@MainActivity, "매칭을 취소합니다.", Toast.LENGTH_SHORT).show() // this@MainActivity로 변경
            }
        })

        alertDialog.setOnDismissListener {
            plusButton.setImageResource(R.drawable.ic_big_plus)
            plusButton.backgroundTintList = ContextCompat.getColorStateList(this,
                R.color.nav_icon_color
            )
            isPlusButtonClicked = false
        }

        alertDialog.show()
    }
}
