package com.example.tokenauthenticationdemo.ui.screens.home

import com.example.tokenauthenticationdemo.data.local.parts.PartsEntity
import com.example.tokenauthenticationdemo.models.Category
import com.example.tokenauthenticationdemo.models.Diagram

data class ProductsState(
    val products: List<Diagram> = emptyList(),
    val categories: List<Category> = emptyList(),
    val partsEntity: PartsEntity? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)