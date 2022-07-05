package com.example.tokenauthenticationdemo.models


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("sub_categories")
    val subCategories: List<Any>
)