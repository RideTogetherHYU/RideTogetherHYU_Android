package com.chobo.hyu_main

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
import android.widget.Toast

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
        // Fragment의 레이아웃을 인플레이트 합니다.
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        // 백 버튼 클릭 이벤트 설정
        val backButton = view.findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack() // 이전 Fragment로 돌아가기
        }

        // UI 요소 초기화
        passengerCountTextView = view.findViewById(R.id.passengerCount)
        val plusButton = view.findViewById<Button>(R.id.plusButton)
        val minusButton = view.findViewById<Button>(R.id.minusButton)

        val startPointEditText = view.findViewById<EditText>(R.id.startPoint)
        val endPointEditText = view.findViewById<EditText>(R.id.endPoint)
        nextButton = view.findViewById(R.id.nextButton)
        val swapButton = view.findViewById<Button>(R.id.swapButton)

        // 기본적으로 버튼 비활성화 및 회색으로 설정
        updateNextButtonState()

        // 탑승 인원 수 버튼 클릭 이벤트
        plusButton.setOnClickListener {
            passengerCountValue++
            updatePassengerCount()
        }

        minusButton.setOnClickListener {
            if (passengerCountValue > 1) {
                passengerCountValue--
                updatePassengerCount()
            }
        }

        // 성별 선택 텍스트 초기화
        genderAny = view.findViewById(R.id.genderAny)
        genderMale = view.findViewById(R.id.genderMale)
        genderFemale = view.findViewById(R.id.genderFemale)

        // 탑승 방향 텍스트 초기화
        directionSchoolToStation = view.findViewById(R.id.boardingDirectionSchoolToStation)
        directionStationToSchool = view.findViewById(R.id.boardingDirectionStationToSchool)

        // 버튼 클릭 이벤트 설정
        nextButton.setOnClickListener {
            val paymentFragment = PaymentFragment() // 인스턴스 생성
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_container, paymentFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // 출발지와 도착지 스왑 버튼 클릭 이벤트 설정
        swapButton.setOnClickListener {
            val temp = startPointEditText.text.toString()
            startPointEditText.setText(endPointEditText.text)
            endPointEditText.setText(temp)
        }

        // 성별 선택 클릭 이벤트 설정
        genderAny.setOnClickListener { changeGenderColor(genderAny) }
        genderMale.setOnClickListener { changeGenderColor(genderMale) }
        genderFemale.setOnClickListener { changeGenderColor(genderFemale) }

        // 탑승 방향 클릭 이벤트 설정
        directionSchoolToStation.setOnClickListener {
            changeDirectionColor(directionSchoolToStation, directionStationToSchool)
            isDirectionSelected = true // 방향이 선택되었음을 표시
            updateNextButtonState() // 버튼 상태 업데이트
        }
        directionStationToSchool.setOnClickListener {
            changeDirectionColor(directionStationToSchool, directionSchoolToStation)
            isDirectionSelected = true // 방향이 선택되었음을 표시
            updateNextButtonState() // 버튼 상태 업데이트
        }

        return view
    }

    private fun updatePassengerCount() {
        passengerCountTextView.text = passengerCountValue.toString()
        // - 버튼을 보이거나 숨기는 로직
        val minusButton = view?.findViewById<Button>(R.id.minusButton)
        minusButton?.visibility = if (passengerCountValue > 1) View.VISIBLE else View.GONE

        updateNextButtonState() // 버튼 상태 업데이트
    }

    // 성별 텍스트 클릭 시 색상 변경
    private fun changeGenderColor(selected: TextView) {
        genderAny.setTextColor(Color.BLACK)
        genderMale.setTextColor(Color.BLACK)
        genderFemale.setTextColor(Color.BLACK)
        selected.setTextColor(Color.RED)
    }

    // 탑승 방향 텍스트 클릭 시 색상 변경
    private fun changeDirectionColor(selected: TextView, unselected: TextView) {
        selected.setTextColor(Color.RED)
        unselected.setTextColor(Color.BLACK)

        isDirectionSelected = true
        updateNextButtonState()
    }

    private fun updateNextButtonState() {
        // 방향과 탑승 인원 수가 선택된 경우에만 버튼 활성화
        nextButton.isEnabled = isDirectionSelected && passengerCountValue > 0
        nextButton.setBackgroundColor(if (nextButton.isEnabled) Color.parseColor("#800080") else Color.parseColor("#A9A9A9")) // 보라색과 회색
    }
}
