package com.example.android_websocket_challange.model.ResponseLamp

data class LampResponse(
    val id: Int,
    val params: List<LampEntity>,
    val method: String,
    val error: String?,
    val is_request: Boolean
)