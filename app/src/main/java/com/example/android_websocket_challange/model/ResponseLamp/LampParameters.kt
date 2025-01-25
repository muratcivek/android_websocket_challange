package com.example.android_websocket_challange.model.ResponseLamp

data class LampParameters(
    val default_value: Int,
    val end_time: String,
    val is_notification: Boolean,
    val output_number: Int,
    val should_output_reverse: Boolean,
    val should_remember_last_value: Boolean,
    val start_time: String
)