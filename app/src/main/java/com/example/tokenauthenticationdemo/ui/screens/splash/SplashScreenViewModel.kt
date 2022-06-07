package com.example.tokenauthenticationdemo.ui.screens.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokenauthenticationdemo.data.repository.AuthRepository
import com.example.tokenauthenticationdemo.utils.AuthState
import com.example.tokenauthenticationdemo.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class SplashScreenViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    private val _eventFlow = mutableStateOf(AuthState())
    val eventFlow: State<AuthState> = _eventFlow

    init {
        Timber.d("init called")
        viewModelScope.launch {
            val result: Resource<String?> = authRepository.autoLogin()

            Timber.d("Result: ${result.message}")
            when (result) {
                is Resource.Success -> {
                    Timber.d("success Called")
                    _eventFlow.value = eventFlow.value.copy(isSuccessful = true)
                }
                is Resource.Failure -> {
                    Timber.d("failure Called")
                    _eventFlow.value = eventFlow.value.copy(isSuccessful = false)
                }
                else -> {}
            }
        }
    }
}