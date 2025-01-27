import com.example.android_websocket_challange.model.RequestLamp.LampControlParam
import com.example.android_websocket_challange.model.RequestLamp.LampRequest
import com.example.android_websocket_challange.network.WebSocketClient
import com.google.gson.Gson
import android.util.Log

class LampControlRepository(private val webSocketClient: WebSocketClient) {
    private val gson = Gson()

    fun getLampControlList(
        onResult: (String) -> Unit, // Başarılı sonuç için callback
        onError: (String) -> Unit  // Hata durumunda çağrılacak callback
    ) {
        try {

            val request = LampRequest(
                is_request = true,
                id = 84,
                params = listOf(LampControlParam("a2830d60-ddff-4dad-8f3d-dfca0ded2462", 1)),
                method = "UpdateControlValue"
            )

            // Request nesnesi JSON formatına dönüştürülüyor
            val requestJson = gson.toJson(request)
            Log.d("LampControlRepository", "Request JSON oluşturuldu: $requestJson")

            // WebSocket bağlantısı başlatılıyor
            webSocketClient.connect(
                onMessageReceived = { responseJson ->
                    // Sunucudan gelen yanıt alındığında çalışır
                    Log.d("LampControlRepository", "Sunucudan gelen yanıt: $responseJson")
                    onResult(responseJson)
                },
                onError = { error ->
                    // Bağlantı hatası durumunda çalışır
                    Log.e("LampControlRepository", "WebSocket hata aldı: $error")
                    onError(error)
                }
            )

            // Mesaj gönderiliyor
            webSocketClient.sendMessage(requestJson)
            Log.d("LampControlRepository", "Mesaj gönderildi: $requestJson")

        } catch (e: Exception) {
            // Beklenmeyen bir hata durumunda loglama ve hata callback'i
            val errorMessage = "Hata oluştu: ${e.message}"
            Log.e("LampControlRepository", errorMessage, e)
            onError(errorMessage)
        }
    }
}
