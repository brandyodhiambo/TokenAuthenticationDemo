package com.example.tokenauthenticationdemo.utils

import com.example.tokenauthenticationdemo.ui.screens.destinations.Destination

sealed class UiEvent{
    data class Navigate(val destination: Destination):UiEvent()
    data class SnackBarEvent(val message:String):UiEvent()
}
