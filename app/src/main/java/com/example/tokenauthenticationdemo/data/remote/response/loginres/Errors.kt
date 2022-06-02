package com.example.tokenauthenticationdemo.data.remote.response.loginres


import com.google.gson.annotations.SerializedName

data class Errors(
    @SerializedName("email")
    val email: String
)