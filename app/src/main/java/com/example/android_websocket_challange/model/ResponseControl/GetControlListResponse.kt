package com.example.android_websocket_challange.model.ResponseControl


data class GetControlListResponse(
    val id: Int,
    val params: List<ControlListParams>?,
    val method: String,
    val error: Any?,
    val is_request: Boolean
)