package com.example.tokenauthenticationdemo.ui.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokenauthenticationdemo.data.repository.CategoriesRepository
import com.example.tokenauthenticationdemo.utils.Resource
import com.example.tokenauthenticationdemo.utils.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoriesRepository: CategoriesRepository
) : ViewModel() {

    private val _selectedCategory = mutableStateOf("All")

    val selectedCategory: State<String> = _selectedCategory
    fun setCategory(value: String) {
        _selectedCategory.value = value
    }

    private val _productState = mutableStateOf(ProductsState())
    val productsState: State<ProductsState> = _productState

    private val _searchTerm = mutableStateOf("")
    val searchTerm: State<String> = _searchTerm

    fun setSearchTerm(term: String) {
        _searchTerm.value = term
    }

    init {
        getCategories(selectedCategory.value)
    }

     fun getCategories(category:String) {
        viewModelScope.launch {
            categoriesRepository.getItem().collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        if(category == "All"){wor
                            _productState.value = productsState.value.copy(
                                categories = result.data?.categories ?: emptyList(),
                                products = result.data?.diagram ?: emptyList(),
                                isLoading = false)
                        }
                        else{
                            _productState.value = productsState.value.copy(
                                categories = result.data?.categories ?: emptyList(),
                                products = result.data?.diagram?.filter { it.name == category } ?: emptyList(),
                                isLoading = false
                            )
                        }


                    }
                    is Resource.Loading -> {
                        _productState.value = productsState.value.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Failure -> {
                        _productState.value = productsState.value.copy(
                            error = result.message ?: "Unknown error occurred",
                            isLoading = false
                        )
                    }
                    else -> {}
                }
            }
        }
    }
}
