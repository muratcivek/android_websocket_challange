package com.example.android_websocket_challange.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_websocket_challange.model.GetControlListResponse
import com.example.android_websocket_challange.repository.ControlListRepository
import com.google.gson.Gson

class ControlListViewModel(private val repository: ControlListRepository) : ViewModel() {
    private val gson = Gson()

    private val _response = MutableLiveData<GetControlListResponse>()
    val response: LiveData<GetControlListResponse> get() = _response

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchControlList() {
        repository.getControlList(
            onResult = { responseJson ->
                val responseObj = gson.fromJson(responseJson, GetControlListResponse::class.java)
                _response.postValue(responseObj)
            },
            onError = { errorMsg ->
                _error.postValue(errorMsg)
            }
        )
    }
}
