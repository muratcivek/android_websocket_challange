package com.example.android_websocket_challange.model

data class Control(
    val id: String,
    val name: String,
    val type_id: String,
    val current_value: Int,
    val is_active: Boolean
)