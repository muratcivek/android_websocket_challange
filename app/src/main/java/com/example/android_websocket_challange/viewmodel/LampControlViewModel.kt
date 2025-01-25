package com.example.android_websocket_challange.viewmodel

import LampControlRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_websocket_challange.model.GetControlListResponse
import com.example.android_websocket_challange.model.RequestLamp.LampRequest
import com.example.android_websocket_challange.model.ResponseLamp.LampResponse
import com.google.gson.Gson
class LampControlViewModel(private val repository: LampControlRepository) : ViewModel() {
    private val gson = Gson()

    private val _response = MutableLiveData<LampResponse>()
    val response: LiveData<LampResponse> get() = _response

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun sendLampRequest() {
        repository.getLampControlList(
            onResult = { responseJson ->
                val responseObj = gson.fromJson(responseJson, LampResponse::class.java)
                _response.postValue(responseObj)
            },
            onError = { errorMsg ->
                _error.postValue(errorMsg)
            }
        )
    }
}
