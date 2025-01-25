package com.example.android_websocket_challange.network

import okhttp3.OkHttpClient
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okhttp3.Request
import okhttp3.Response

class WebSocketClient(private val url: String) {
    private var webSocket: WebSocket? = null
    private val client = OkHttpClient()

    fun connect(onMessageReceived: (String) -> Unit, onError: (String) -> Unit) {
        val request = Request.Builder().url(url).build()
        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onMessage(webSocket: WebSocket, text: String) {
                onMessageReceived(text)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                onError(t.message ?: "Unknown error")

            }
        })
    }

    fun sendMessage(message: String) {
        webSocket?.send(message)
    }

    fun close() {
        webSocket?.close(1000, "Closing connection")
    }
}