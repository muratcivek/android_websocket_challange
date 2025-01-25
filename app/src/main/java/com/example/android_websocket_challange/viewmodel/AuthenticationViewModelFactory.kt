package com.example.android_websocket_challange.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_websocket_challange.repository.AuthenticationRepository

// AuthenticationViewModelFactory sınıfı, parametreli ViewModel'lar için kullanılır.
class AuthenticationViewModelFactory(
    private val repository: AuthenticationRepository // Gerekli bağımlılık
) : ViewModelProvider.Factory {

    // ViewModel'ı başlatmak için create() fonksiyonunu override ederiz
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthenticationViewModel::class.java)) {
            return AuthenticationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
