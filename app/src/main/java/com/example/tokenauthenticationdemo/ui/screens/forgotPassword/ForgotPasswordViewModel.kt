package com.example.tokenauthenticationdemo.ui.screens.forgotPassword

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokenauthenticationdemo.data.repository.AuthRepository
import com.example.tokenauthenticationdemo.utils.AuthState
import com.example.tokenauthenticationdemo.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class ForgotPasswordViewModel @Inject constructor(private val authRepository: AuthRepository):ViewModel() {

    //forgot Password
    private val _forgotPass = mutableStateOf(AuthState())
    val forgotPass: State<AuthState> = _forgotPass

    private val _emailState = mutableStateOf("")
    val emailState:State<String> = _emailState

    fun setEmail(value:String){
        _emailState.value =value
    }

    //forgot password
    fun forgotPassword(){
        if (emailState.value.isBlank()){
            "Email is null "
        } else{
            _forgotPass.value = AuthState(isLoading = true)
            viewModelScope.launch {
                when (val result = authRepository.forgotPassword(emailState.value)) {
                    is Resource.Success -> {
                        _forgotPass.value = AuthState(isLoading = false, isSuccessful = true)

                    }
                    is Resource.Failure -> {
                        _forgotPass.value = AuthState(isLoading = false, error = result.message)

                    }
                    else -> {}
                }
            }

        }
    }
}