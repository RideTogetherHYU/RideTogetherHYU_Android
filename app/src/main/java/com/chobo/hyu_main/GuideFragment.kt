package com.chobo.hyu_main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView // ImageView import 추가

class GuideFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_guide, container, false)

        // 뒤로 가기 버튼을 찾아서 클릭 리스너 설정
        val backButton: ImageView = view.findViewById(R.id.back_button)
        backButton.setOnClickListener {
            // Activity의 onBackPressedDispatcher 호출
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        // 버튼 클릭 리스너 설정
        val buttonGoToAddFragment: Button = view.findViewById(R.id.button_go_to_add_fragment) // 버튼 ID 변경에 유의
        buttonGoToAddFragment.setOnClickListener {
            val addFragment = AddFragment() // AddFragment 인스턴스 생성
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, addFragment) // R.id.fragment_container는 FrameLayout ID
                .addToBackStack(null) // 뒤로 가기 버튼을 통해 이전 Fragment로 돌아갈 수 있도록 설정
                .commit()
        }

        return view
    }



}
