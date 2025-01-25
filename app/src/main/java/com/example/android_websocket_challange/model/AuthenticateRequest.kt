package com.example.android_websocket_challange.model

data class AuthenticateRequest(
    val is_request: Boolean,
    val id: Int,
    val params: List<UserCredentials>,
    val method: String
)