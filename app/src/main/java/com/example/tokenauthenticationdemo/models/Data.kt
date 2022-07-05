package com.example.tokenauthenticationdemo.models


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("diagrams")
    val diagrams: List<Diagram>,
    @SerializedName("models")
    val models: List<Model>
)