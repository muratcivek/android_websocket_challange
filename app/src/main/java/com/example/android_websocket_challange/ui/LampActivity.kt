package com.example.android_websocket_challange.ui

import LampControlRepository
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_websocket_challange.R
import com.example.android_websocket_challange.databinding.ActivityControlBinding
import com.example.android_websocket_challange.databinding.ActivityLampBinding
import com.example.android_websocket_challange.network.WebSocketClient
import com.example.android_websocket_challange.repository.ControlListRepository
import com.example.android_websocket_challange.viewmodel.ControlListViewModel
import com.example.android_websocket_challange.viewmodel.ControlListViewModelFactory
import com.example.android_websocket_challange.viewmodel.LampControlViewModel
import com.example.android_websocket_challange.viewmodel.LampControlViewModelFactory
class LampActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLampBinding
    private lateinit var webSocketClient: WebSocketClient
    private lateinit var repository: LampControlRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLampBinding.inflate(layoutInflater)
        setContentView(binding.root)

        webSocketClient = WebSocketClient("wss://ws.postman-echo.com/raw")

        repository = LampControlRepository(webSocketClient)

        val viewModel: LampControlViewModel by viewModels {
            LampControlViewModelFactory(repository)
        }

        // Lamba düğmesine tıklanma olayını dinle
        binding.BTNLAMP.setOnClickListener {
            // ViewModel üzerinden lamba isteği gönder
            viewModel.sendLampRequest()

            Toast.makeText(this, "İstek gönderildi", Toast.LENGTH_SHORT).show()
        }

        // Gelen yanıtı gözlemle
        viewModel.response.observe(this) { response ->

            Log.d("LampActivity", "Gelen yanıt: $response")

            Toast.makeText(this, "Yanıt: $response", Toast.LENGTH_SHORT).show()
        }

        // Hataları gözlemle
        viewModel.error.observe(this) { error ->
            // Hata mesajını logla
            Log.e("LampActivity", "Hata: $error")

            // Kullanıcıya hata mesajı göster
            Toast.makeText(this, "Hata: $error", Toast.LENGTH_SHORT).show()
        }
    }
}
