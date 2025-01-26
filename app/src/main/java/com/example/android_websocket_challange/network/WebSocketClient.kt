package com.example.android_websocket_challange.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okhttp3.Request
import okhttp3.Response

class WebSocketClient(private val url: String) {
    private var webSocket: WebSocket? = null
    private val client = OkHttpClient() // OkHttpClient nesnesi oluşturuluyor

    // Bağlantı kurma fonksiyonu
    fun connect(onMessageReceived: (String) -> Unit, onError: (String) -> Unit) {
        // WebSocket için istek oluşturuluyor
        val request = Request.Builder().url(url).build()
        Log.d("WebSocketClient", "Bağlantı kuruluyor: $url")

        // WebSocket bağlantısı başlatılıyor
        webSocket = client.newWebSocket(request, object : WebSocketListener() {

            // Bağlantı başarılı olduğunda tetiklenen metot
            override fun onOpen(webSocket: WebSocket, response: Response) {
                Log.d("WebSocketClient", "WebSocket bağlantısı kuruldu: ${response.message}")
            }

            // WebSocket üzerinden mesaj alındığında tetiklenen metot
            override fun onMessage(webSocket: WebSocket, text: String) {
                Log.d("WebSocketClient", "Mesaj alındı: $text")
                onMessageReceived(text) // Mesaj alındığında callback fonksiyonu çalıştırılır
            }

            // WebSocket bağlantısında bir hata oluştuğunda tetiklenen metot
            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                val errorMessage = t.message ?: "Bilinmeyen hata" // Hata mesajını alıyoruz
                Log.e("WebSocketClient", "WebSocket hatası: $errorMessage")
                onError(errorMessage) // Hata alındığında callback fonksiyonu çalıştırılır
            }

            // WebSocket bağlantısı kapanırken tetiklenen metot
            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                Log.d("WebSocketClient", "WebSocket kapanıyor: Kod $code, Sebep: $reason")
            }
        })
    }

    // WebSocket üzerinden mesaj gönderme fonksiyonu
    fun sendMessage(message: String) {
        Log.d("WebSocketClient", "Mesaj gönderiliyor: $message")
        webSocket?.send(message) // Mesaj WebSocket üzerinden gönderiliyor
    }

    // WebSocket bağlantısını kapama fonksiyonu
    fun close() {
        Log.d("WebSocketClient", "WebSocket bağlantısı kapatılıyor")
        webSocket?.close(1000, "Bağlantıyı kapatıyorum") // Bağlantıyı sonlandırıyoruz
    }
}
