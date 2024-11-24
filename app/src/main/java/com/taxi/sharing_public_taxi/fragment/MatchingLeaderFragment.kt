package com.taxi.sharing_public_taxi.fragment

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
import com.taxi.sharing_public_taxi.R

//하단 버튼 모양 바꿔야하는데 어떻게 해야할지 생각해야함.
class MatchingLeaderFragment : Fragment() {
    private var selectedButton: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 레이아웃을 인플레이트합니다.
        val view = inflater.inflate(R.layout.fragment_matchingleader, container, false)

        // 기본 색상 설정
        val defaultButtonBackground = R.color.white // 초기 배경색은 흰색
        val selectedButtonBackground = R.color.nav_icon_color // 선택된 배경색
        val defaultTextColor = Color.BLACK // 초기 텍스트 색상
        val selectedTextColor = Color.WHITE // 선택된 텍스트 색상

        val nextButton = view.findViewById<Button>(R.id.nextButton)

        return view
    }

}
