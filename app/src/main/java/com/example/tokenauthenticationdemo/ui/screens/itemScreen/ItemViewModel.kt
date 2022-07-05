package com.example.tokenauthenticationdemo.ui.screens.itemScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokenauthenticationdemo.data.local.CustomItemEntity
import com.example.tokenauthenticationdemo.data.repository.CategoriesRepository
import com.example.tokenauthenticationdemo.utils.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val categoriesRepository: CategoriesRepository
): ViewModel() {
    private val _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow: SharedFlow<UiEvents> = _eventFlow.asSharedFlow()

    fun insertItemToFavorite(itemEntity: CustomItemEntity){
        viewModelScope.launch {
            categoriesRepository.insertItem(itemEntity)
        }
    }
}