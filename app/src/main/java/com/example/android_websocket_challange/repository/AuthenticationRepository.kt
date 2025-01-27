package com.example.android_websocket_challange.repository

import android.util.Log
import com.example.android_websocket_challange.model.RequestAuthenticate.AuthenticateRequest
import com.example.android_websocket_challange.model.RequestAuthenticate.UserCredentials
import com.example.android_websocket_challange.network.WebSocketClient
import com.google.gson.Gson

class AuthenticationRepository(private val webSocketClient: WebSocketClient) {
    private val gson = Gson()

    fun authenticate(
        onResult: (String) -> Unit,   // Başarılı durumda dönecek callback
        onError: (String) -> Unit    // Hata durumunda dönecek callback
    ) {
        // Kullanıcı giriş bilgilerini içeren bir liste oluşturuyoruz.
        val userCredentialsList = listOf(UserCredentials(username = "demo", password = "123456"))

        // Kimlik doğrulama isteği oluşturuluyor.
        val request = AuthenticateRequest(
            is_request = true,
            id = 123,
            params = userCredentialsList,
            method = "POST"
        )

        // İstek objesini JSON formatına çeviriyoruz.
        val requestJson = gson.toJson(request)

        // WebSocket bağlantısını başlatıyoruz.
        webSocketClient.connect(
            onMessageReceived = { responseJson ->
                // WebSocket'ten gelen yanıtı işliyoruz.
                Log.d("WebSocketResponse", "Gelen Yanıt: $responseJson")
                onResult(responseJson) // Başarı durumunda callback çağrılır.
            },
            onError = { error ->
                // Hata durumunda gelen mesajı logluyoruz.
                Log.e("WebSocketError", "WebSocket Hatası: $error")
                onError(error) // Hata callback'ini tetikliyoruz.
            }
        )

        // WebSocket üzerinden JSON mesajını gönderiyoruz.
        webSocketClient.sendMessage(requestJson)
        Log.d("WebSocketRequest", "Gönderilen İstek: $requestJson")
    }
}
