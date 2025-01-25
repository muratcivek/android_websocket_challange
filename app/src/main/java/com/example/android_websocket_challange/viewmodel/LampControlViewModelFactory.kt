package com.example.android_websocket_challange.viewmodel

import LampControlRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LampControlViewModelFactory(
    private val repository: LampControlRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LampControlViewModel::class.java)) {
            return LampControlViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
