package com.example.tokenauthenticationdemo.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.Shapes
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tokenauthenticationdemo.R
import com.example.tokenauthenticationdemo.ui.screens.destinations.LoginScreenDestination
import com.example.tokenauthenticationdemo.ui.screens.forgotPassword.ForgotPasswordViewModel
import com.example.tokenauthenticationdemo.ui.theme.*

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun PassWord(
    navigator: DestinationsNavigator,
    viewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkPurple),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(
            text = "FORGOT YOUR PASSWORD?",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            value = viewModel.emailState.value,
            onValueChange = { viewModel.setEmail(it) },
            placeholder = {Text(text = "johndoe@gmail.com")},
            label = {
                Text(
                    "Enter Email",
                    color = white
                )
            },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Pink,
                unfocusedBorderColor = DarkGray,
                textColor = white
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            colors = ButtonDefaults.buttonColors(Orange),
            onClick = {
                viewModel.forgotPassword()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(text = "Send", color = white)
        }


        val context = LocalContext.current

        val state = viewModel.forgotPass.value
        if (state.isLoading){
            CircularProgressIndicator(
                modifier = Modifier.align(CenterHorizontally)
            )
        }
        LaunchedEffect(state){
            if(state.error != null){
                Toast.makeText(context, state.error.toString(), Toast.LENGTH_SHORT).show()
            }
            if(state.isSuccessful){
                Toast.makeText(context, state.successMessage, Toast.LENGTH_SHORT).show()
                navigator.popBackStack()
                navigator.navigate(LoginScreenDestination)
            }
        }

    }
}