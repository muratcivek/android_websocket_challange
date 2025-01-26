import com.example.android_websocket_challange.model.RequestLamp.LampControlParam
import com.example.android_websocket_challange.model.RequestLamp.LampRequest
import com.example.android_websocket_challange.network.WebSocketClient
import com.google.gson.Gson

class LampControlRepository(private val webSocketClient: WebSocketClient) {
    private val gson = Gson()

    fun getLampControlList(
        onResult: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        val request = LampRequest( is_request = true,
            id = 84,
            params = listOf(LampControlParam("a2830d60-ddff-4dad-8f3d-dfca0ded2462", 1)),
            method = "UpdateControlValue")
        val requestJson = gson.toJson(request)

        webSocketClient.connect(
            onMessageReceived = { responseJson -> onResult(responseJson) },
            onError = { error -> onError(error) }
        )

        webSocketClient.sendMessage(requestJson)


    }
}
