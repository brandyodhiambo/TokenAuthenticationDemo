package com.example.tokenauthenticationdemo.utils

import com.example.tokenauthenticationdemo.ui.screens.destinations.Destination

sealed class UiEvents {
    data class SnackbarEvent(val message: String) : UiEvents()
    data class NavigateEvent(val route: String) : UiEvents()
}
