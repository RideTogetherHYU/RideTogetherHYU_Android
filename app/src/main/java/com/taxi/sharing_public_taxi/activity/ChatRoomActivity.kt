package com.taxi.sharing_public_taxi.activity

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.addListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.taxi.sharing_public_taxi.R
import com.taxi.sharing_public_taxi.adapter.ChatAdapter
import com.taxi.sharing_public_taxi.model.ChatMessage

class ChatRoomActivity : AppCompatActivity() {
    private lateinit var moreButton: TextView
    private lateinit var expandableView: LinearLayout
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var chatAdapter: ChatAdapter

    private var isExpanded = false
    private val chatList = mutableListOf<ChatMessage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_room_activity)

        moreButton = findViewById(R.id.moreButton)
        expandableView = findViewById(R.id.expandableView)
        chatRecyclerView = findViewById(R.id.chatRecyclerView)

        val messageInput: EditText = findViewById(R.id.messageInput)
        val sendButton: Button = findViewById(R.id.sendButton)

        // RecyclerView 설정
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatAdapter = ChatAdapter(chatList)
        chatRecyclerView.adapter = chatAdapter

        // 채팅 메시지 샘플 추가
        populateChatMessages()

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

        sendButton.setOnClickListener {
            val messageText = messageInput.text.toString().trim()
            if (messageText.isNotEmpty()) {
                // 메시지를 리스트에 추가
                chatList.add(ChatMessage("나", messageText, true))
                chatAdapter.notifyItemInserted(chatList.size - 1) // RecyclerView 업데이트
                chatRecyclerView.scrollToPosition(chatList.size - 1) // 가장 최근 메시지로 스크롤
                messageInput.text.clear() // 입력 필드 비우기
            }
        }

    }


    private fun populateChatMessages() {
        // 샘플 메시지
        chatList.add(ChatMessage("최 페페", "안녕하세요!", false))
        chatList.add(ChatMessage("김승님", "다 같이 출발 준비하세요.", false))
        chatList.add(ChatMessage("나", "안녕하세요! 반갑습니다.", true))
        chatAdapter.notifyDataSetChanged() // 데이터 업데이트 알림
    }

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
