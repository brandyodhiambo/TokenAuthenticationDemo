package com.example.tokenauthenticationdemo.ui.screens.home

import com.example.tokenauthenticationdemo.models.Category
import com.example.tokenauthenticationdemo.models.Diagram

data class ProductsState(
    val products: List<Diagram> = emptyList(),
    val categories: List<Category> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)