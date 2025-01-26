package com.example.android_websocket_challange.model.ResponseAuthenticate

data class AuthenticateResponse(
    val id: Int,
    val params: List<Map<String, Any>>?, // JSON nesnelerini desteklemek için güncelledik
    val method: String,
    val error: String?,
    val is_request: Boolean
)