package com.example.tokenauthenticationdemo.ui.screens.splash

import android.widget.Toast
import android.window.SplashScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tokenauthenticationdemo.ui.screens.destinations.HomeScreenDestination
import com.example.tokenauthenticationdemo.ui.screens.destinations.LoginScreenDestination
import com.example.tokenauthenticationdemo.utils.UiEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext
import timber.log.Timber


@Destination(start = true)
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator,
    viewModel: SplashScreenViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        withContext(Dispatchers.Main) {
            delay(3000)
            val result = viewModel.eventFlow.value
            Timber.d("vent ${result.isSuccessful}")

            if (result.isSuccessful) {
                Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
                navigator.popBackStack()
                navigator.navigate(HomeScreenDestination)
            } else {
                Toast.makeText(context, "failure", Toast.LENGTH_SHORT).show()
                navigator.popBackStack()
                navigator.navigate(LoginScreenDestination)
            }
        }
    }


    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Splash Screen")
    }
}