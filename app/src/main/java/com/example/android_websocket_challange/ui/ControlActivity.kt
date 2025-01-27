package com.example.android_websocket_challange.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_websocket_challange.R
import com.example.android_websocket_challange.databinding.ActivityControlBinding
import com.example.android_websocket_challange.network.WebSocketClient
import com.example.android_websocket_challange.repository.ControlListRepository
import com.example.android_websocket_challange.viewmodel.ControlListViewModel
import com.example.android_websocket_challange.viewmodel.ControlListViewModelFactory
class ControlActivity : AppCompatActivity() {

    private lateinit var binding: ActivityControlBinding
    private lateinit var webSocketClient: WebSocketClient
    private lateinit var repository: ControlListRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityControlBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // WebSocket bağlantısını oluştur
        webSocketClient = WebSocketClient("wss://ws.postman-echo.com/raw")
        repository = ControlListRepository(webSocketClient)


        val viewModel: ControlListViewModel by viewModels {
            ControlListViewModelFactory(repository)
        }

        // Aydınlatma butonuna tıklama dinleyicisi
        binding.LYTLINEARLIGHTING.setOnClickListener {
            // Veri çekme işlemi başlatılır
            viewModel.fetchControlList()
        }

        // Sunucudan gelen başarı durumunda yanıt gözlemleniyor
        viewModel.response.observe(this) { response ->
            // Gelen yanıtı kullanıcıya göster
            Toast.makeText(this, "$response", Toast.LENGTH_SHORT).show()

            // Yeni bir aktiviteye geçiş yap
            val intent = Intent(this, LampActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Hata durumunda gözlemleme
        viewModel.error.observe(this) { error ->
            // Hata mesajını kullanıcıya göster
            Toast.makeText(this, "Hata: $error", Toast.LENGTH_SHORT).show()
        }
    }
}
