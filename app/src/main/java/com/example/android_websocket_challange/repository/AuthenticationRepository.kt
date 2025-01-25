package com.example.android_websocket_challange.repository

import android.util.Log
import com.example.android_websocket_challange.model.AuthenticateRequest
import com.example.android_websocket_challange.model.GetControlListRequest
import com.example.android_websocket_challange.model.UserCredentials
import com.example.android_websocket_challange.network.WebSocketClient
import com.google.gson.Gson


class AuthenticationRepository(private val webSocketClient: WebSocketClient) {
    private val gson = Gson()

    fun authenticate(
        onResult: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        val userCredentialsList = listOf(UserCredentials(username = "demo", password = "123456"))
        val request = AuthenticateRequest(
            is_request = true,            // Boolean değeri
            id = 123,                     // Integer değer
            params = userCredentialsList, // UserCredentials listesi
            method = "POST"               // String değer
        )

        val requestJson = gson.toJson(request)


        webSocketClient.connect(
            onMessageReceived = { responseJson ->
                // Yanıtı loglayalım
                Log.d("WebSocketResponse", responseJson)
                onResult(responseJson)
            },
            onError = { error -> onError(error) }
        )

        webSocketClient.sendMessage(requestJson)


    }
}