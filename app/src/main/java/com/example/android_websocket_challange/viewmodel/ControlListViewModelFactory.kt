package com.example.android_websocket_challange.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_websocket_challange.repository.ControlListRepository

class ControlListViewModelFactory(
    private val repository: ControlListRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ControlListViewModel::class.java)) {
            return ControlListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
