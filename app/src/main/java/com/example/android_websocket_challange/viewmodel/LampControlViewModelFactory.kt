package com.example.android_websocket_challange.viewmodel

import LampControlRepository
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LampControlViewModelFactory(
    private val repository: LampControlRepository
) : ViewModelProvider.Factory {

    /**
     * Belirtilen ViewModel sınıfını oluşturur ve döner.
     * Eğer sınıf `LampControlViewModel` değilse hata fırlatır.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LampControlViewModel::class.java)) {
            // LampControlViewModel oluşturuluyor
            LampControlViewModel(repository) as T
        } else {
            // Uygun olmayan bir ViewModel sınıfı durumunda hata loglanıyor ve IllegalArgumentException fırlatılıyor
            Log.e("LampControlVMFactory", "Bilinmeyen ViewModel sınıfı: ${modelClass.name}")
            throw IllegalArgumentException("Bilinmeyen ViewModel sınıfı: ${modelClass.name}")
        }
    }
}

