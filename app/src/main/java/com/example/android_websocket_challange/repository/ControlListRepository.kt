package com.example.android_websocket_challange.repository

import com.example.android_websocket_challange.model.RequestControl.GetControlListRequest
import com.example.android_websocket_challange.network.WebSocketClient
import com.google.gson.Gson


class ControlListRepository(private val webSocketClient: WebSocketClient) {
    private val gson = Gson()

    fun getControlList(
        onResult: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        val request = GetControlListRequest()
        val requestJson = gson.toJson(request)

        webSocketClient.connect(
            onMessageReceived = { responseJson -> onResult(responseJson) },
            onError = { error -> onError(error) }
        )

        webSocketClient.sendMessage(requestJson)


    }
}