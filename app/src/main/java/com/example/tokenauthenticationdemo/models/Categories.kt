package com.example.tokenauthenticationdemo.models


import androidx.room.Entity
import com.google.gson.annotations.SerializedName


data class Categories(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("links")
    val links: Any,
    @SerializedName("message")
    val message: String,
    @SerializedName("meta")
    val meta: Any
)