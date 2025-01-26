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
    private lateinit var binding: ActivityControlBinding // View Binding Nesnesi
    private lateinit var webSocketClient: WebSocketClient
    private lateinit var repository: ControlListRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Binding Nesnesini Başlat
        binding = ActivityControlBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Edge-to-edge etkinleştir
        enableEdgeToEdge()

        // ViewCompat yerine doğrudan ViewBinding kullanımı
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // WebSocketClient ve Repository başlatma
        webSocketClient = WebSocketClient("wss://ws.postman-echo.com/raw")
        repository = ControlListRepository(webSocketClient)

        // ViewModel başlatma
        val viewModel: ControlListViewModel by viewModels {
            ControlListViewModelFactory(repository)
        }

        binding.LYTLINEARLIGHTING.setOnClickListener {
            viewModel.fetchControlList()
        }

        // ViewModel'dan gelen yanıtı gözlemle
        viewModel.response.observe(this) { response ->
            // Yanıtı logla ve kontrol et
            Toast.makeText(this, "$response", Toast.LENGTH_SHORT).show()
            val controls = response.params?.get(0)?.data
            Toast.makeText(this, "$controls", Toast.LENGTH_SHORT).show()
            if (controls != null && controls.isNotEmpty()) {
                val firstControlName = controls[0].name
                Toast.makeText(this, "First Control: $firstControlName", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No controls available.", Toast.LENGTH_SHORT).show()
            }

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