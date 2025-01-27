package com.example.android_websocket_challange.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_websocket_challange.repository.ControlListRepository

class ControlListViewModelFactory(
    private val repository: ControlListRepository
) : ViewModelProvider.Factory {

    /**
     * Belirtilen ViewModel sınıfını oluşturur ve döner.
     * Eğer sınıf `ControlListViewModel` değilse hata fırlatır.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ControlListViewModel::class.java)) {
            // ControlListViewModel oluşturuluyor
            ControlListViewModel(repository) as T
        } else {
            // Uygun olmayan bir ViewModel sınıfı durumunda hata loglanıyor ve IllegalArgumentException fırlatılıyor
            Log.e("ControlListVMFactory", "Bilinmeyen ViewModel sınıfı: ${modelClass.name}")
            throw IllegalArgumentException("Bilinmeyen ViewModel sınıfı: ${modelClass.name}")
        }
    }
}
