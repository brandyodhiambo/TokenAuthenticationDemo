package com.example.tokenauthenticationdemo.data.remote.response.loginres


import com.google.gson.annotations.SerializedName

data class LoginError(
    @SerializedName("code")
    val code: Int,
    @SerializedName("errors")
    val errors: Errors,
    @SerializedName("message")
    val message: String
)