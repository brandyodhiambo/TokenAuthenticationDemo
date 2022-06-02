package com.example.tokenauthenticationdemo.data.remote.response.registerres

data class RegisterResponse(
    val `data`: Data,
    val links: Links,
    val message: String,
    val meta: Meta,
    val code: Int,
    val errors: Errors,

    )