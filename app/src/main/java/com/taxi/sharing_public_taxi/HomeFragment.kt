package com.taxi.sharing_public_taxi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.view.WindowManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.tabs.TabLayout
import androidx.core.content.ContextCompat
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import android.widget.Button
import android.content.Intent
import android.widget.ImageView

class HomeFragment : Fragment() {
    private lateinit var containerLayout: ViewGroup
    private lateinit var tabLayout: TabLayout
    private var isPlusButtonClicked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment의 레이아웃을 인플레이트 합니다.
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // 가이드 버튼 클릭 리스너 설정
        val buttonGuide: Button = view.findViewById(R.id.button_guide)
        buttonGuide.setOnClickListener {
            // GuideFragment로 전환
            val guideFragment = GuideFragment() // 새로운 GuideFragment 인스턴스 생성
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, guideFragment) // R.id.fragment_container는 MainActivity에 있는 FrameLayout
                .addToBackStack(null) // 뒤로 가기 버튼을 통해 이전 Fragment로 돌아갈 수 있도록 백스택에 추가
                .commit()
        }

        // TabLayout을 참조합니다.
        tabLayout = view.findViewById(R.id.tab_layout)
        setupTabLayout()

        // ShapeAppearanceModel을 사용하여 하단 모서리만 둥글게 설정
        val shapeAppearanceModel = ShapeAppearanceModel().toBuilder()
            .setBottomLeftCorner(CornerFamily.ROUNDED, 16f)  // 왼쪽 하단 모서리 둥글게
            .setBottomRightCorner(CornerFamily.ROUNDED, 16f)  // 오른쪽 하단 모서리 둥글게
            .setTopLeftCorner(CornerFamily.CUT, 0f)          // 상단 모서리는 직선
            .setTopRightCorner(CornerFamily.CUT, 0f)         // 상단 모서리는 직선
            .build()

        // MaterialShapeDrawable에 ShapeAppearanceModel 적용
        val materialShapeDrawable = MaterialShapeDrawable(shapeAppearanceModel).apply {
            fillColor = ContextCompat.getColorStateList(requireContext(), R.color.white) // 배경색 설정
        }

        // TabLayout에 배경으로 설정
        tabLayout.background = materialShapeDrawable

        // 카드 레이아웃을 참조합니다.
        containerLayout = view.findViewById(R.id.card_container)

        // 기본적으로 출발 카드 표시
        showDepartureCards()

        return view
    }

    private fun setupTabLayout() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> showDepartureCards() // "학교에서 역까지" 탭 선택
                    1 -> showReturnCards()    // "역에서 학교까지" 탭 선택
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun showDepartureCards() {
        containerLayout.removeAllViews() // 기존 카드 제거

        // 출발 카드 추가
        val card1 = createCardView("출발 임박", "정문 출발", "제가 절반 부담할게요... 벤382", "3/4", 75, R.color.red)
        containerLayout.addView(card1)

        val card2 = createCardView("3분 후 출발", "컨벤션센터 출발", "모든 비용 부담합니다... 벤742", "3/4", 75, R.color.blue)
        containerLayout.addView(card2)

        val card3 = createCardView("15분 후 출발", "제2공학관 출발", "모든 비용 부담합니다... 벤742", "3/4", 75, R.color.blue)
        containerLayout.addView(card3)
    }

    private fun showReturnCards() {
        containerLayout.removeAllViews() // 기존 카드 제거

        // 반납 카드 추가
        val card1 = createCardView("5분 후 출발", "정문에서 내림", "모든 비용 부담합니다... 벤852", "4/4", 100, R.color.red)
        containerLayout.addView(card1)

        val card2 = createCardView("7분 후 반납", "기숙사에서 내림", "모든 비용 부담합니다... 벤962", "2/4", 50, R.color.blue)
        containerLayout.addView(card2)
    }

    private fun showMatchingDialog(notificationIcon: ImageView) {
        val alertDialog = CustomDialog(requireContext())
        alertDialog.setDialogTitle("매칭에 참여하겠습니까?")
        alertDialog.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        val layoutParams = alertDialog.window?.attributes
        layoutParams?.dimAmount = 0.5f // 흐림 정도 설정 (0.0 - 1.0)
        alertDialog.window?.attributes = layoutParams

        alertDialog.setItemClickListener(object : CustomDialog.ItemClickListener {

            override fun onYesClick() {
                // MatchingProgressFragment로 이동
                val progressFragment = MatchingProgressFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.main_container, progressFragment) // 실제 Fragment의 container ID를 넣어야 합니다.
                    .addToBackStack(null) // 뒤로가기 시 이전 Fragment로 돌아올 수 있도록
                    .commit()
            }

            override fun onNoClick() {
                Toast.makeText(requireContext(), "매칭을 취소합니다.", Toast.LENGTH_SHORT).show()
                notificationIcon.visibility = View.VISIBLE
            }
        })

        alertDialog.setOnDismissListener {
            notificationIcon.visibility = View.VISIBLE // ImageView를 다시 보이게 함
        }

        alertDialog.show()
    }

    // 재사용 가능한 카드를 생성하는 함수
    private fun createCardView(
        statusLabel: String,
        title: String,
        description: String,
        seatInfo: String,
        progress: Int,
        statusColorRes: Int
    ): View {
        // 카드 레이아웃을 인플레이트
        val cardView = layoutInflater.inflate(R.layout.card_item, containerLayout, false) as CardView

        // 데이터 바인딩
        val statusTextView = cardView.findViewById<TextView>(R.id.status_label)
        val titleTextView = cardView.findViewById<TextView>(R.id.card_title)
        val descriptionTextView = cardView.findViewById<TextView>(R.id.card_description)
        val seatInfoTextView = cardView.findViewById<TextView>(R.id.card_seat_info)
        val progressBar = cardView.findViewById<ProgressBar>(R.id.card_progress)
        val notificationIcon = cardView.findViewById<ImageView>(R.id.icon_notification)

        // 설정
        statusTextView.text = statusLabel
        titleTextView.text = title
        descriptionTextView.text = description
        seatInfoTextView.text = seatInfo
        progressBar.progress = progress

        // 상태 레이블의 배경 색상을 동적으로 설정
        val background = ContextCompat.getDrawable(requireContext(), R.drawable.status_background)
        background?.setTint(ContextCompat.getColor(requireContext(), statusColorRes))
        statusTextView.background = background

        // 알림 아이콘 클릭 리스너 설정
        notificationIcon.setOnClickListener {
            showMatchingDialog(notificationIcon) // 다이얼로그 표시 함수 호출
        }

        return cardView
    }
}
