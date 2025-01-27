package com.example.android_websocket_challange.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_websocket_challange.repository.AuthenticationRepository

class AuthenticationViewModelFactory(
    private val repository: AuthenticationRepository
) : ViewModelProvider.Factory {

    /**
     * Belirtilen ViewModel sınıfını oluşturur ve döner.
     * Eğer sınıf `AuthenticationViewModel` değilse hata fırlatır.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AuthenticationViewModel::class.java)) {
            // AuthenticationViewModel oluşturuluyor
            AuthenticationViewModel(repository) as T
        } else {
            // Uygun olmayan bir ViewModel sınıfı durumunda hata loglanıyor ve IllegalArgumentException fırlatılıyor
            Log.e("AuthenticationVMFactory", "Bilinmeyen ViewModel sınıfı: ${modelClass.name}")
            throw IllegalArgumentException("Bilinmeyen ViewModel sınıfı: ${modelClass.name}")
        }
    }
}

