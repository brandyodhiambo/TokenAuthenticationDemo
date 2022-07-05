package com.example.tokenauthenticationdemo.models


import com.google.gson.annotations.SerializedName

data class Model(
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("manufacturer")
    val manufacturer: Manufacturer,
    @SerializedName("name")
    val name: String,
    @SerializedName("serials")
    val serials: List<Serial>
)