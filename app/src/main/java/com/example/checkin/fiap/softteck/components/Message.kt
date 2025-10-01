package com.example.checkin.fiap.softteck.components


data class Message(
    val id: Int,
    val sender: String,
    val text: String,
    val isUser: Boolean = true,
    val isReply: Boolean = false
)