package com.example.android_websocket_challange.model.RequestControl

data class GetControlListRequest(
    val is_request: Boolean = true,
    val id: Int = 5,
    val params: List<Map<String, Any>> = listOf(emptyMap()),
    val method: String = "GetControlList"
)