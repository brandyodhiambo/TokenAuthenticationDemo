package com.example.tokenauthenticationdemo.data.remote.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    val confirm_password: String,
    val country_code: String,
    val email: String,
    val name: String,
    val password: String,
    val phone_number: String
)