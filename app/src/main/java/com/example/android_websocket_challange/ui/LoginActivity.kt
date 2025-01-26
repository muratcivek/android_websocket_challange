package com.example.android_websocket_challange.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.android_websocket_challange.R
import com.example.android_websocket_challange.databinding.ActivityLoginBinding
import com.example.android_websocket_challange.network.WebSocketClient
import com.example.android_websocket_challange.repository.AuthenticationRepository
import com.example.android_websocket_challange.viewmodel.AuthenticationViewModel
import com.example.android_websocket_challange.viewmodel.AuthenticationViewModelFactory
import com.example.android_websocket_challange.viewmodel.LampControlViewModel
import com.example.android_websocket_challange.viewmodel.LampControlViewModelFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var webSocketClient: WebSocketClient
    private lateinit var repository: AuthenticationRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // WebSocketClient ve Repository başlatma
        webSocketClient = WebSocketClient("wss://ws.postman-echo.com/raw")
        repository = AuthenticationRepository(webSocketClient)

        // ViewModel başlatma
        val viewModel: AuthenticationViewModel by viewModels {
            AuthenticationViewModelFactory(repository)
        }

        // Buton Tıklama İşlemi
        binding.BTNACCOUNT.setOnClickListener {
            viewModel.Authenticate()
            Toast.makeText(this, "$", Toast.LENGTH_SHORT).show()

        }

        // ViewModel'dan gelen yanıtı gözlemle
        viewModel.response.observe(this) { response ->
            // Yanıtı logla ve kontrol et
            Toast.makeText(this, "$response", Toast.LENGTH_SHORT).show()


            val intent = Intent(this, ControlActivity::class.java)
            startActivity(intent)
            finish() // Eğer mevcut aktiviteyi kapatmak istiyorsanız
        }

        viewModel.error.observe(this) { error ->
            // Hata mesajını logla ve göster
            Log.d("hatahata", " $error ")
            Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
        }


    }
}