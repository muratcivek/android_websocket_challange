package com.example.android_websocket_challange.ui

import LampControlRepository
import android.content.Intent
import android.os.Bundle
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
    private lateinit var binding: ActivityLampBinding // View Binding Nesnesi
    private lateinit var webSocketClient: WebSocketClient
    private lateinit var repository: LampControlRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Binding Nesnesini Başlat
        binding = ActivityLampBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // WebSocketClient ve Repository başlatma
         webSocketClient = WebSocketClient("wss://ws.postman-echo.com/raw")
         repository = LampControlRepository(webSocketClient)

        // ViewModel başlatma
        val viewModel: LampControlViewModel by viewModels {
            LampControlViewModelFactory(repository)
        }

        // Buton Tıklama İşlemi
        binding.lampButton.setOnClickListener {
            viewModel.sendLampRequest()
            Toast.makeText(this, "$", Toast.LENGTH_SHORT).show()

        }

        // ViewModel'dan gelen yanıtı gözlemle
        viewModel.response.observe(this) { response ->
            // Yanıtı logla ve kontrol et
            Toast.makeText(this, "$response", Toast.LENGTH_SHORT).show()


            val intent = Intent(this, LampActivity::class.java)
            startActivity(intent)
            finish() // Eğer mevcut aktiviteyi kapatmak istiyorsanız
        }

        viewModel.error.observe(this) { error ->
            // Hata mesajını logla ve göster

            Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
        }
    }
}