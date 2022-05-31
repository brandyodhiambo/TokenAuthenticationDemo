package com.example.tokenauthenticationdemo.models

import com.google.gson.annotations.SerializedName

data class RegisterModel(
    //val confirm_password: String,
   // val country_code: String,
    val email: String,
    val name: String,
    val password: String,
    val phone_number: String
)