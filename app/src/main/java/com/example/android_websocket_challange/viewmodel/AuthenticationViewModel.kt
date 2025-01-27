package com.example.android_websocket_challange.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_websocket_challange.repository.AuthenticationRepository
import com.example.android_websocket_challange.model.ResponseAuthenticate.AuthenticateResponse
import com.google.gson.Gson
class AuthenticationViewModel(private val repository: AuthenticationRepository) : ViewModel() {

    private val gson = Gson()

    // Sunucudan dönen yanıtın tutulduğu LiveData
    private val _response = MutableLiveData<AuthenticateResponse>()
    val response: LiveData<AuthenticateResponse> get() = _response

    // Hata mesajlarının tutulduğu LiveData
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    /**
     * Kullanıcı kimlik doğrulama işlemini başlatır.
     * Repository'den gelen yanıt işlenir ve uygun LiveData'ya post edilir.
     */
    fun authenticate() {
        repository.authenticate(
            onResult = { responseJson ->
                try {
                    // Yanıt JSON formatından nesneye dönüştürülüyor
                    val responseObj = gson.fromJson(responseJson, AuthenticateResponse::class.java)
                    _response.postValue(responseObj)
                } catch (e: Exception) {
                    // JSON dönüşümünde hata olursa loglanır ve hata mesajı yayınlanır
                    Log.e("AuthenticationViewModel", "JSON parse hatası: ${e.message}", e)
                    _error.postValue("Bir hata oluştu: Yanıt işlenemedi.")
                }
            },
            onError = { errorMsg ->
                // Hata mesajı loglanır ve LiveData'ya post edilir
                Log.e("AuthenticationViewModel", "Authentication hatası: $errorMsg")
                _error.postValue(errorMsg)
            }
        )
    }
}
