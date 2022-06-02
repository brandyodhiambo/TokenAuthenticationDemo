package com.example.tokenauthenticationdemo.data.remote.response.loginres


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("access_token_expires_in")
    val accessTokenExpiresIn: Int,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("refresh_token_expires_in")
    val refreshTokenExpiresIn: Int
)