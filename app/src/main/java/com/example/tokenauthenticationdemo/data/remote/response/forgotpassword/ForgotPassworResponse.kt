package com.example.tokenauthenticationdemo.data.remote.response.forgotpassword


import com.google.gson.annotations.SerializedName

data class ForgotPassworResponse(
    @SerializedName("data")
    val `data`: Any,
    @SerializedName("links")
    val links: Any,
    @SerializedName("message")
    val message: String,
    @SerializedName("meta")
    val meta: Any
)