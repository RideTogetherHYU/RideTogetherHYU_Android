package com.taxi.sharing_public_taxi.model

data class ChatMessage(
    val senderName: String,
    val messageText: String,
    val isMyMessage: Boolean // true: 내가 보낸 메시지, false: 상대방이 보낸 메시지
)
