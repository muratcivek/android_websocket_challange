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

        // WebSocketClient'ı başlatıyoruz (örnek WebSocket adresi)
        webSocketClient = WebSocketClient("wss://ws.postman-echo.com/raw")
        // AuthenticationRepository ile bağlantıyı kuruyoruz
        repository = AuthenticationRepository(webSocketClient)


        val viewModel: AuthenticationViewModel by viewModels {
            AuthenticationViewModelFactory(repository)
        }

        // "Hesabım" butonuna tıklama işlemini ayarlıyoruz
        binding.BTNACCOUNT.setOnClickListener {
            // ViewModel üzerinden authenticate çağrısı yapıyoruz
            viewModel.authenticate()
            Toast.makeText(this, "Kimlik doğrulama başlatıldı", Toast.LENGTH_SHORT).show()
        }

        // ViewModel'in response değişkenini gözlemliyoruz
        viewModel.response.observe(this) { response ->
            // Başarılı yanıtı logluyoruz ve kullanıcıya bir Toast ile gösteriyoruz
            Log.d("LoginActivity", "Yanıt: $response")
            Toast.makeText(this, "Başarılı: $response", Toast.LENGTH_SHORT).show()

            // Başarılı olduğunda ControlActivity'e geçiş yapıyoruz
            val intent = Intent(this, ControlActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Hata durumlarını gözlemliyoruz ve logluyoruz
        viewModel.error.observe(this) { error ->
            Log.e("LoginActivity", "Hata: $error") // Hata durumunu loglama
            Toast.makeText(this, "Hata: $error", Toast.LENGTH_SHORT).show()
        }
    }
}
