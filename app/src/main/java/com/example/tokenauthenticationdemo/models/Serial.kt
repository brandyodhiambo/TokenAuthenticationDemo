package com.example.tokenauthenticationdemo.models


import com.google.gson.annotations.SerializedName

data class Serial(
    @SerializedName("id")
    val id: String,
    @SerializedName("serial_end")
    val serialEnd: String,
    @SerializedName("serial_start")
    val serialStart: String,
    @SerializedName("year_end")
    val yearEnd: String,
    @SerializedName("year_start")
    val yearStart: String
)