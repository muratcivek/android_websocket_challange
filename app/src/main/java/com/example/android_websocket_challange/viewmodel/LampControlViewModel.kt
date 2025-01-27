package com.example.android_websocket_challange.viewmodel

import LampControlRepository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_websocket_challange.model.ResponseLamp.LampResponse
import com.google.gson.Gson

class LampControlViewModel(private val repository: LampControlRepository) : ViewModel() {
    private val gson = Gson()

    // Sunucudan gelen yanıtların tutulduğu LiveData
    private val _response = MutableLiveData<LampResponse>()
    val response: LiveData<LampResponse> get() = _response

    // Hata mesajlarının tutulduğu LiveData
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    /**
     * Kontrol isteğini repository'den gönderir ve yanıtı işler.
     * JSON formatındaki yanıt, nesneye dönüştürülerek _response LiveData'sına gönderilir.
     */
    fun sendLampRequest() {
        repository.getLampControlList(
            onResult = { responseJson ->
                try {
                    // Yanıt JSON formatından nesneye dönüştürülüyor
                    val responseObj = gson.fromJson(responseJson, LampResponse::class.java)
                    _response.postValue(responseObj)
                } catch (e: Exception) {
                    // JSON dönüşümünde bir hata oluşursa, hata loglanır ve hata mesajı yayınlanır
                    Log.e("LampControlViewModel", "JSON parse hatası: ${e.message}", e)
                    _error.postValue("Bir hata oluştu: Yanıt işlenemedi.")
                }
            },
            onError = { errorMsg ->
                // Hata mesajı loglanır ve LiveData'ya post edilir
                Log.e("LampControlViewModel", "Lamba kontrol hatası: $errorMsg")
                _error.postValue(errorMsg)
            }
        )
    }
}
