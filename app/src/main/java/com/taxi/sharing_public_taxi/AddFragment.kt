package com.taxi.sharing_public_taxi

import android.content.res.ColorStateList
import android.os.Bundle
import android.graphics.Color
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.widget.TextView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat

class AddFragment : Fragment() {
    private var passengerCountValue = 1
    private lateinit var passengerCountTextView: TextView

    private lateinit var genderAny: TextView
    private lateinit var genderMale: TextView
    private lateinit var genderFemale: TextView
    private lateinit var directionSchoolToStation: TextView
    private lateinit var directionStationToSchool: TextView

    private lateinit var nextButton: Button

    private var isDirectionSelected = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        // Back button listener
        val backButton = view.findViewById<ImageView>(R.id.backButton)
        backButton?.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        // Initialize UI components
        passengerCountTextView = view.findViewById(R.id.passengerCount)
        val plusButton = view.findViewById<ImageButton>(R.id.plusButton) // Correct initialization
        val minusButton = view.findViewById<ImageButton>(R.id.minusButton)
        val startPointEditText = view.findViewById<EditText>(R.id.startPoint)
        val endPointEditText = view.findViewById<EditText>(R.id.endPoint)
        nextButton = view.findViewById(R.id.nextButton)
        val swapButton = view.findViewById<ImageButton>(R.id.swapButton) // Correct ImageButton initialization



        updateNextButtonState()

        // Passenger count adjustment
        plusButton?.setOnClickListener {
            passengerCountValue++
            updatePassengerCount()
        }

        minusButton?.setOnClickListener {
            if (passengerCountValue > 1) {
                passengerCountValue--
                updatePassengerCount()
            }
        }

        // Gender selection
        genderAny = view.findViewById(R.id.genderAny)
        genderMale = view.findViewById(R.id.genderMale)
        genderFemale = view.findViewById(R.id.genderFemale)

        genderAny.setOnClickListener { changeGenderColor(genderAny) }
        genderMale.setOnClickListener { changeGenderColor(genderMale) }
        genderFemale.setOnClickListener { changeGenderColor(genderFemale) }

        // Boarding direction
        directionSchoolToStation = view.findViewById(R.id.boardingDirectionSchoolToStation)
        directionStationToSchool = view.findViewById(R.id.boardingDirectionStationToSchool)

        directionSchoolToStation.setOnClickListener {
            changeDirectionColor(directionSchoolToStation, directionStationToSchool)
            updateNextButtonState()
        }

        directionStationToSchool.setOnClickListener {
            changeDirectionColor(directionStationToSchool, directionSchoolToStation)
            updateNextButtonState()
        }

        // Swap start and end points
        swapButton?.setOnClickListener {
            val temp = startPointEditText.text.toString()
            startPointEditText.setText(endPointEditText.text)
            endPointEditText.setText(temp)
        }

        nextButton.setOnClickListener {
            // Navigate to PaymentFragment or next process
            val paymentFragment = PaymentFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, paymentFragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }

    private fun updatePassengerCount() {
        passengerCountTextView.text = passengerCountValue.toString()
        view?.findViewById<ImageButton>(R.id.minusButton)?.visibility =
            if (passengerCountValue > 1) View.VISIBLE else View.INVISIBLE // Keep space using INVISIBLE
        updateNextButtonState()
    }

    private fun changeGenderColor(selected: TextView) {
        listOf(genderAny, genderMale, genderFemale).forEach {
            it.setTextColor(Color.BLACK)
        }
        selected.setTextColor(ContextCompat.getColor(requireContext(), R.color.nav_icon_color))
    }

    private fun changeDirectionColor(selected: TextView, unselected: TextView) {
        selected.setTextColor(ContextCompat.getColor(requireContext(), R.color.nav_icon_color))
        unselected.setTextColor(Color.BLACK)
        isDirectionSelected = true
        updateNextButtonState()
    }

    private fun updateNextButtonState() {
        val enabledColor = ContextCompat.getColor(requireContext(), R.color.nav_icon_color) // 활성화 색상
        val disabledColor = ContextCompat.getColor(requireContext(), R.color.gray) // 비활성화 색상

        nextButton.isEnabled = isDirectionSelected && passengerCountValue > 0

        val color = if (nextButton.isEnabled) enabledColor else disabledColor
        nextButton.backgroundTintList = ColorStateList.valueOf(color) // backgroundTintList로 변경
    }

}
