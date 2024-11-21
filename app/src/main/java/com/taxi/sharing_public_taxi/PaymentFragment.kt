package com.taxi.sharing_public_taxi

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
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

        // 기본 색상 설정
        val defaultButtonBackground = R.color.white // 초기 배경색은 흰색
        val selectedButtonBackground = R.color.nav_icon_color // 선택된 배경색
        val defaultTextColor = Color.BLACK // 초기 텍스트 색상
        val selectedTextColor = Color.WHITE // 선택된 텍스트 색상

        // 모든 버튼의 초기 상태를 설정
        val buttons = listOf(bankaccountButton, kakaopayButton, tossButton)
        buttons.forEach { button ->
            button.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), defaultButtonBackground))
            button.setTextColor(defaultTextColor)
        }

        // '다음으로' 버튼 초기 설정
        nextButton.isEnabled = false
        nextButton.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray))
        nextButton.setTextColor(Color.GRAY)

        // 버튼 클릭 리스너 설정
        buttons.forEach { button ->
            button.setOnClickListener {
                // 이전 선택된 버튼이 있으면 색상 원래대로 돌리기
                selectedButton?.apply {
                    backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), defaultButtonBackground))
                    setTextColor(defaultTextColor)
                }

                // 클릭한 버튼 색상 변경
                button.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), selectedButtonBackground))
                button.setTextColor(selectedTextColor)

                selectedButton = button // 현재 선택된 버튼 저장

                // '다음으로' 버튼 활성화
                nextButton.isEnabled = true
                nextButton.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(requireContext(), selectedButtonBackground))
                nextButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

                Toast.makeText(requireContext(), "${button.text}를 선택했습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
