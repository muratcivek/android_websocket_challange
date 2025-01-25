package com.example.android_websocket_challange.model.RequestLamp

data class LampRequest(
    val is_request: Boolean,
    val id: Int,
    val params: List<LampControlParam>,
    val method: String
)