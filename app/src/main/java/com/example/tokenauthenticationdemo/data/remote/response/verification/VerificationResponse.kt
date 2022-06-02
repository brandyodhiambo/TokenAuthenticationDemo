package com.example.tokenauthenticationdemo.data.remote.response.verification


import com.google.gson.annotations.SerializedName

data class VerificationResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("links")
    val links: Any,
    @SerializedName("message")
    val message: String,
    @SerializedName("meta")
    val meta: Any
)