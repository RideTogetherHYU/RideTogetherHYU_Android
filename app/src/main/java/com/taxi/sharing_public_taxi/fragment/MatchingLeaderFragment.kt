package com.taxi.sharing_public_taxi.fragment

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.taxi.sharing_public_taxi.R

class MatchingLeaderFragment : Fragment() {
    private var selectedButton: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 레이아웃을 인플레이트합니다.
        val view = inflater.inflate(R.layout.fragment_matchingleader, container, false)

        // 애니메이션 대상 View와 Button 초기화
        val circleView = view.findViewById<View>(R.id.circleView)
        val matchingText = view.findViewById<TextView>(R.id.matchingText)
        val nextButton = view.findViewById<Button>(R.id.nextButton)

        // 버튼 색상 변경 설정
        val defaultButtonBackground = R.color.white
        val selectedButtonBackground = R.color.nav_icon_color
        val defaultTextColor = Color.BLACK
        val selectedTextColor = Color.WHITE

        nextButton.setOnClickListener {
            // 버튼 색상 및 상태 변경
            selectedButton?.apply {
                setBackgroundResource(defaultButtonBackground)
                setTextColor(defaultTextColor)
            }
            nextButton.setBackgroundResource(selectedButtonBackground)
            nextButton.setTextColor(selectedTextColor)
            selectedButton = nextButton

            // 애니메이션 실행
            runCircleAnimation(circleView, matchingText)
        }

        return view
    }

    private fun runCircleAnimation(circleView: View, matchingText: TextView) {
        // 동그라미 확장
        val expandCircleX = ObjectAnimator.ofFloat(circleView, "scaleX", 0f, 1.5f)
        val expandCircleY = ObjectAnimator.ofFloat(circleView, "scaleY", 0f, 1.5f)
        expandCircleX.duration = 500
        expandCircleY.duration = 500

        // 텍스트 표시
        val fadeInText = ObjectAnimator.ofFloat(matchingText, "alpha", 0f, 1f)
        fadeInText.duration = 500

        // 동그라미 축소
        val shrinkCircleX = ObjectAnimator.ofFloat(circleView, "scaleX", 1.5f, 0f)
        val shrinkCircleY = ObjectAnimator.ofFloat(circleView, "scaleY", 1.5f, 0f)
        shrinkCircleX.duration = 500
        shrinkCircleY.duration = 500

        // 애니메이션 순서 지정
        val animatorSet = AnimatorSet()
        animatorSet.play(expandCircleX).with(expandCircleY)
        animatorSet.play(fadeInText).after(expandCircleX)
        animatorSet.play(shrinkCircleX).with(shrinkCircleY).after(fadeInText)

        animatorSet.start()
    }
}
