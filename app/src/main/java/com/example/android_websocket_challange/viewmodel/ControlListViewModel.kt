package com.example.android_websocket_challange.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_websocket_challange.model.ResponseControl.GetControlListResponse
import com.example.android_websocket_challange.repository.ControlListRepository
import com.google.gson.Gson
class ControlListViewModel(private val repository: ControlListRepository) : ViewModel() {
    private val gson = Gson()

    // Sunucudan dönen kontrol listesi yanıtının tutulduğu LiveData
    private val _response = MutableLiveData<GetControlListResponse>()
    val response: LiveData<GetControlListResponse> get() = _response

    // Hata mesajlarının tutulduğu LiveData
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    /**
     * Kontrol listesini repository'den alır ve yanıtı işler.
     * JSON formatındaki yanıt, nesneye dönüştürülerek _response LiveData'sına gönderilir.
     */
    fun fetchControlList() {
        repository.getControlList(
            onResult = { responseJson ->
                try {
                    // Yanıt JSON formatından nesneye dönüştürülüyor
                    val responseObj = gson.fromJson(responseJson, GetControlListResponse::class.java)
                    _response.postValue(responseObj)
                } catch (e: Exception) {
                    // JSON dönüşümünde bir hata oluşursa, hata loglanır ve hata mesajı yayınlanır
                    Log.e("ControlListViewModel", "JSON parse hatası: ${e.message}", e)
                    _error.postValue("Bir hata oluştu: Yanıt işlenemedi.")
                }
            },
            onError = { errorMsg ->
                // Hata mesajı loglanır ve LiveData'ya post edilir
                Log.e("ControlListViewModel", "Kontrol listesi hatası: $errorMsg")
                _error.postValue(errorMsg)
            }
        )
    }
}

