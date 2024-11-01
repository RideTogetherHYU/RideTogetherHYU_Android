package com.chobo.hyu_main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class PaymentFragment : Fragment() {
    private var selectedButton: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 레이아웃을 인플레이트합니다.
        val view = inflater.inflate(R.layout.fragment_payment, container, false)

        // UI 요소 초기화
        val bankaccountButton = view.findViewById<Button>(R.id.bankaccountButton)
        val kakaopayButton = view.findViewById<Button>(R.id.kakaopayButton)
        val tossButton = view.findViewById<Button>(R.id.tossButton)
        val nextButton = view.findViewById<Button>(R.id.nextButton)

        // 기본 버튼 색상 설정
        val defaultButtonColor = Color.WHITE
        val defaultTextColor = Color.BLACK
        val selectedButtonColor = Color.parseColor("#FFBB86FC") // 보라색
        val selectedTextColor = Color.WHITE

        bankaccountButton.setBackgroundColor(defaultButtonColor)
        bankaccountButton.setTextColor(defaultTextColor)

        kakaopayButton.setBackgroundColor(defaultButtonColor)
        kakaopayButton.setTextColor(defaultTextColor)

        tossButton.setBackgroundColor(defaultButtonColor)
        tossButton.setTextColor(defaultTextColor)

        nextButton.setBackgroundColor(Color.GRAY) // 초기 비활성화 상태
        nextButton.isEnabled = false // 초기 비활성화

        // 버튼 클릭 리스너 설정
        val buttons = listOf(bankaccountButton, kakaopayButton, tossButton)

        buttons.forEach { button ->
            button.setOnClickListener {
                // 이전 선택된 버튼이 있으면 색상 원래대로 돌리기
                selectedButton?.setBackgroundColor(defaultButtonColor)
                selectedButton?.setTextColor(defaultTextColor)

                // 클릭한 버튼 색상 변경
                button.setBackgroundColor(selectedButtonColor)
                button.setTextColor(selectedTextColor)

                selectedButton = button // 현재 선택된 버튼 저장

                // '다음으로' 버튼 활성화
                nextButton.setBackgroundColor(selectedButtonColor)
                nextButton.isEnabled = true

                Toast.makeText(requireContext(), "${button.text}를 선택했습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
