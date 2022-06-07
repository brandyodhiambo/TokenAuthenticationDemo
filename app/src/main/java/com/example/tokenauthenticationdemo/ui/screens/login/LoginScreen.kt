package com.example.tokenauthenticationdemo.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tokenauthenticationdemo.ui.screens.destinations.HomeScreenDestination
import com.example.tokenauthenticationdemo.ui.screens.destinations.LoginScreenDestination
import com.example.tokenauthenticationdemo.ui.screens.destinations.PassWordDestination
import com.example.tokenauthenticationdemo.ui.screens.destinations.RegisterScreenDestination
import com.example.tokenauthenticationdemo.ui.screens.login.LoginViewModel
import com.example.tokenauthenticationdemo.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
    viewModel:LoginViewModel = hiltViewModel()
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(DarkPurple)
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        Text(
            text = "Will Manager",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Orange
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Login",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = white
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
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            value = viewModel.passwordState.value,
            onValueChange = { viewModel.setPassword(it) },
            label = {
                Text(
                    "Enter password",
                    color = white
                )
            },
            placeholder = {Text(text = "password")},
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Pink,
                unfocusedBorderColor = DarkGray,
                textColor = white
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Forgot Password?",
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp)
                .clickable {
                           navigator.popBackStack()
                    navigator.navigate(PassWordDestination)
                },
            color = white
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            colors = ButtonDefaults.buttonColors(Orange),
            onClick = {
                viewModel.loginUser()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(text = "Sign in", color = white)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Don't Have an account Sign up?",
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp)
                .clickable {
                    navigator.popBackStack()
                    navigator.navigate(RegisterScreenDestination)
                },
            color = white,
        )



        val context = LocalContext.current
        //login
        val state = viewModel.login.value
        if(state.isLoading){
            CircularProgressIndicator(
                modifier = Modifier.align(CenterHorizontally)
            )
        }
       LaunchedEffect(state){
           if (state.error != null) {
               Toast.makeText(context, state.error.toString(), Toast.LENGTH_SHORT).show()
           }
           if (state.isSuccessful) {
               Toast.makeText(context, "Logged in successfully", Toast.LENGTH_SHORT).show()
               navigator.popBackStack()
               navigator.navigate(HomeScreenDestination)
           }
        }




    }

}