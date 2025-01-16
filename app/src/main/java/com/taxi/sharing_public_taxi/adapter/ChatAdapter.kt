package com.taxi.sharing_public_taxi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.taxi.sharing_public_taxi.R
import com.taxi.sharing_public_taxi.model.ChatMessage
class ChatAdapter(private val chatList: List<ChatMessage>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val senderMessageLayout: LinearLayout = itemView.findViewById(R.id.senderMessageLayout)
        val receiverMessageLayout: LinearLayout = itemView.findViewById(R.id.receiverMessageLayout)
        val senderMessageText: TextView = itemView.findViewById(R.id.senderMessageText) // 수정된 ID
        val receiverMessageText: TextView = itemView.findViewById(R.id.receiverMessageText) // 수정된 ID
        val receiverName: TextView = itemView.findViewById(R.id.receiverName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_item, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val message = chatList[position]

        if (message.isMyMessage) {
            holder.senderMessageLayout.visibility = View.VISIBLE
            holder.receiverMessageLayout.visibility = View.GONE
            holder.senderMessageText.text = message.messageText // 올바른 참조
        } else {
            holder.senderMessageLayout.visibility = View.GONE
            holder.receiverMessageLayout.visibility = View.VISIBLE
            holder.receiverMessageText.text = message.messageText // 올바른 참조
            holder.receiverName.text = message.senderName // 보낸 사람 이름 설정
        }
    }


    override fun getItemCount(): Int = chatList.size
}

