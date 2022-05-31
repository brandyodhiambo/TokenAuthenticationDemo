package com.example.tokenauthenticationdemo.viewmodel

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokenauthenticationdemo.data.repository.AuthRepository
import com.example.tokenauthenticationdemo.models.RefreshModel
import com.example.tokenauthenticationdemo.models.RegisterModel
import com.example.tokenauthenticationdemo.utils.AuthState
import com.example.tokenauthenticationdemo.utils.Constants.MIN_PASSWORD_LENGTH
import com.example.tokenauthenticationdemo.utils.Constants.MIN_PHONE_LENGTH
import com.example.tokenauthenticationdemo.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _register = mutableStateOf(AuthState())
    val register: State<AuthState> = _register


    private val _nameState = mutableStateOf("")
    val nameState:State<String> = _nameState

    private val _emailState = mutableStateOf("")
    val emailState:State<String> = _emailState

    private val _phoneState = mutableStateOf("")
    val phoneState:State<String> = _phoneState

    private val _passwordState = mutableStateOf("")
    val passwordState:State<String> = _passwordState

    private val _confirmPasswordState = mutableStateOf("")
    val confirmPasswordState:State<String> = _confirmPasswordState


    fun setName(value: String){
        _nameState.value = value
    }

    fun setEmail(value: String){
        _emailState.value = value
    }
    fun setPhone(value: String){
        _phoneState.value = value
    }

    fun setPassword(value: String){
        _passwordState.value = value
    }

    fun setConfirmPassword(value: String){
        _confirmPasswordState.value = value
    }

    fun registerUsers(){
        var error = if(nameState.value.isBlank()||emailState.value.isBlank()||phoneState.value.isBlank()||passwordState.value.isBlank()||confirmPasswordState.value.isBlank()){
            "Null Values"
        }  else if (phoneState.value.length < MIN_PHONE_LENGTH) {
            "Phone to short"
        } else if (phoneState.value.length > MIN_PHONE_LENGTH) {
            "Phone to long"
        }else if (passwordState.value.length<MIN_PASSWORD_LENGTH){
            "Password too short"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailState.value).matches()){
            "Email not valid"
        } else null

        error?.let {
            _register.value = AuthState(error = it)
            return
        }
        _register.value = AuthState(isLoading = true)
        viewModelScope.launch(Dispatchers.Main){
            val result = authRepository.registerUser(RegisterModel(nameState.value,phoneState.value,emailState.value,passwordState.value))
            when(result){
                is Resource.Success<*> -> {
                    _register.value = AuthState(isLoading = false, isSuccessful = true)
                    Timber.d("Account Created Successfully")

                }
                is Resource.Error<*> ->{
                    _register.value = AuthState(isLoading = false, error = result.message)
                    Timber.d(result.message)

                }
            }
        }
    }

}





