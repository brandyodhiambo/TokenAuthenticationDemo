package com.example.tokenauthenticationdemo.models

data class ResetPasswordModel(
    val confirm_password: String,
    val password: String,
    val token: String
)