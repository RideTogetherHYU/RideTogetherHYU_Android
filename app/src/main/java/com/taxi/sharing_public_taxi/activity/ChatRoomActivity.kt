package com.taxi.sharing_public_taxi.activity

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.addListener
import com.taxi.sharing_public_taxi.R

class ChatRoomActivity : AppCompatActivity() {
    private lateinit var moreButton: TextView
    private lateinit var expandableView: LinearLayout
    private var isExpanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_room_activity) // XML 파일 연결

        moreButton = findViewById(R.id.moreButton)
        expandableView = findViewById(R.id.expandableView)

        moreButton.setOnClickListener {
            if (isExpanded) {
                collapseView(expandableView)
                moreButton.text = "더보기 ▼"
            } else {
                expandView(expandableView)
                moreButton.text = "더보기 ▲"
            }
            isExpanded = !isExpanded
        }
    }

    // 확장 애니메이션
    private fun expandView(view: View) {
        view.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val targetHeight = view.measuredHeight

        view.layoutParams.height = 0
        view.visibility = View.VISIBLE

        val animator = ValueAnimator.ofInt(0, targetHeight)
        animator.addUpdateListener { animation ->
            val value = animation.animatedValue as Int
            view.layoutParams.height = value
            view.requestLayout()
        }
        animator.duration = 300
        animator.start()
    }

    // 축소 애니메이션
    private fun collapseView(view: View) {
        val initialHeight = view.measuredHeight

        val animator = ValueAnimator.ofInt(initialHeight, 0)
        animator.addUpdateListener { animation ->
            val value = animation.animatedValue as Int
            view.layoutParams.height = value
            view.requestLayout()
        }
        animator.duration = 300
        animator.start()

        animator.addListener(onEnd = {
            view.visibility = View.GONE
        })
    }
}