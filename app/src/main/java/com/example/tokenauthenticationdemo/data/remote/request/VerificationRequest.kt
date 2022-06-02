package com.example.tokenauthenticationdemo.data.remote.request


import com.google.gson.annotations.SerializedName

data class VerificationRequest(
    @SerializedName("email")
    val email: String
)