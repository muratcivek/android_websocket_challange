package com.example.android_websocket_challange.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_websocket_challange.repository.AuthenticationRepository
import com.example.android_websocket_challange.model.ResponseAuthenticate.AuthenticateResponse
import com.google.gson.Gson

class AuthenticationViewModel(private val repository: AuthenticationRepository) : ViewModel() {
    private val gson = Gson()

    private val _response = MutableLiveData<AuthenticateResponse>()
    val response: LiveData<AuthenticateResponse> get() = _response

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun Authenticate() {
        repository.authenticate(
            onResult = { responseJson ->
                val responseObj = gson.fromJson(responseJson, AuthenticateResponse::class.java)
                _response.postValue(responseObj)
            },
            onError = { errorMsg ->
                _error.postValue(errorMsg)
            }
        )
    }

}