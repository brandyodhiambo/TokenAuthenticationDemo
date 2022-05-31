package com.example.tokenauthenticationdemo.utils

data class AuthState(
    val isLoading:Boolean = false,
    val isSuccessful:Boolean = false,
    val error:String? = null
)