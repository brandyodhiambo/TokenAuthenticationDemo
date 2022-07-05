package com.example.tokenauthenticationdemo.ui.screens.login

import android.media.tv.TvInputManager
import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokenauthenticationdemo.data.remote.request.LoginRequest
import com.example.tokenauthenticationdemo.data.repository.AuthRepository
import com.example.tokenauthenticationdemo.ui.screens.destinations.HomeScreenDestination
import com.example.tokenauthenticationdemo.ui.screens.destinations.LoginScreenDestination
import com.example.tokenauthenticationdemo.utils.AuthState
import com.example.tokenauthenticationdemo.utils.Constants
import com.example.tokenauthenticationdemo.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {
    //login
    private val _login = mutableStateOf(AuthState())
    val login :State<AuthState> = _login


    private val _emailState=  mutableStateOf("")
    val emailState:State<String> = _emailState

    private val _passwordState = mutableStateOf("")
    val passwordState:State<String> = _passwordState

    fun setEmail(value:String){
        _emailState.value = value
    }

    fun setPassword(value: String){
        _passwordState.value = value
    }



    //login
    fun loginUser(){
        var error = if(emailState.value.isBlank()||passwordState.value.isBlank()){
            "Null Values are not accepted"
        }else if (passwordState.value.length< Constants.MIN_PASSWORD_LENGTH){
            "Password too short"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailState.value).matches()){
            "Email not valid"
        } else null

        error?.let {
            _login.value = AuthState(error = it)
            return
        }
        _login.value = AuthState(isLoading = true)
        viewModelScope.launch{
            val request = LoginRequest(
                email = emailState.value,
                password = passwordState.value
            )

            when(val result = authRepository.loginUser(request)){
                is Resource.Success ->{
                    _login.value = AuthState(isLoading = false,isSuccessful = true)
                }
                is Resource.Failure ->{
                    _login.value = AuthState(isLoading = false, error = result.message)
                }
                else -> Unit
            }
        }

    }



}