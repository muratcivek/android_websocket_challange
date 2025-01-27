package com.example.android_websocket_challange.repository

import com.example.android_websocket_challange.model.RequestControl.GetControlListRequest
import com.example.android_websocket_challange.network.WebSocketClient
import com.google.gson.Gson
import android.util.Log

class ControlListRepository(private val webSocketClient: WebSocketClient) {
    private val gson = Gson()

    // WebSocket üzerinden kontrol listesini almak için bir fonksiyon
    fun getControlList(
        onResult: (String) -> Unit, // Başarılı sonuç callback'i
        onError: (String) -> Unit  // Hata callback'i
    ) {
        // İstek modelini (GetControlListRequest) JSON formatına dönüştür
        val request = GetControlListRequest()
        val requestJson = gson.toJson(request)

        // WebSocket bağlantısını başlat
        webSocketClient.connect(
            onMessageReceived = { responseJson ->
                // Sunucudan gelen cevabı logla ve onResult callback'ini çağır
                Log.d("ControlListRepository", "Sunucudan cevap alındı: $responseJson")
                onResult(responseJson)
            },
            onError = { error ->
                // Hata durumunda logla ve onError callback'ini çağır
                Log.e("ControlListRepository", "WebSocket hatası: $error")
                onError("WebSocket bağlantısı sırasında bir hata oluştu: $error")
            }
        )

        // İstek mesajını gönder ve logla
        try {
            Log.d("ControlListRepository", "İstek gönderiliyor: $requestJson")
            webSocketClient.sendMessage(requestJson)
        } catch (exception: Exception) {
            // Mesaj gönderimi sırasında bir hata olursa, logla ve onError callback'ini çağır
            Log.e("ControlListRepository", "Mesaj gönderme hatası: ${exception.message}")
            onError("WebSocket mesajı gönderilemedi: ${exception.message}")
        }
    }
}
