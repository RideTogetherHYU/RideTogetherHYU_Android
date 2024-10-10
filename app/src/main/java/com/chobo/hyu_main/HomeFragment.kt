package com.chobo.hyu_main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AlertDialog

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment의 레이아웃을 인플레이트 합니다.
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // 플러스 버튼을 찾아서 클릭 리스너 설정 (FloatingActionButton 사용 가정)
        val plusButton: FloatingActionButton = view.findViewById(R.id.fab_plus)
        plusButton.setOnClickListener {
            // 다이얼로그를 표시하는 함수 호출
            showMatchingDialog()
        }

        return view
    }

    // 매칭을 진행할지 묻는 다이얼로그 표시
    private fun showMatchingDialog() {
        // AlertDialog를 생성하고 메시지를 설정합니다.
        AlertDialog.Builder(requireContext())
            .setTitle("매칭을 진행하겠습니까?")
            .setMessage("매칭을 진행하시겠습니까?")
            .setPositiveButton("예") { dialog, which ->
                // "예"를 눌렀을 때의 동작
                dialog.dismiss()
                // 추가적인 행동을 여기에 정의할 수 있습니다.
            }
            .setNegativeButton("아니오") { dialog, which ->
                // "아니오"를 눌렀을 때의 동작
                dialog.dismiss()
            }
            .create()
            .show()
    }
}
