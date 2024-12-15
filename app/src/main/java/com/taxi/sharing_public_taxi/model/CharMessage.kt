package com.taxi.sharing_public_taxi.model

data class ChatMessage(
    val sender: String,
    val message: String,
    val isMyMessage: Boolean
)