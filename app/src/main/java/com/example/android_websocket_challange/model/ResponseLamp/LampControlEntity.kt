package com.example.android_websocket_challange.model.ResponseLamp

data class LampControlEntity(
    val id: String,
    val name: String,
    val type_id: String,
    val bridge_device_id: String,
    val current_value: Int,
    val slot: Int,
    val is_active: Boolean,
    val area_id: String,
    val parameters: LampParameters
)