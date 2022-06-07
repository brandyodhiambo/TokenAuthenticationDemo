package com.example.tokenauthenticationdemo.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tokenauthenticationdemo.ui.screens.destinations.LoginScreenDestination
import com.example.tokenauthenticationdemo.ui.theme.*
import com.example.tokenauthenticationdemo.ui.screens.register.RegisterViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination()
@Composable
fun RegisterScreen(
    navigator: DestinationsNavigator,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(DarkPurple)
    ) {
        val state = viewModel.register.value
        Spacer(modifier = Modifier.height(10.dp))
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
            text = "Sign Up",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = white
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            value = viewModel.nameState.value,
            onValueChange = { viewModel.setName(it) },
            label = {
                Text(
                    "Enter Name",
                    color = white

                )
            },
            placeholder = { Text(text = "John Doe") },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Pink,
                unfocusedBorderColor = DarkGray,
                textColor = white
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            value = viewModel.emailState.value,
            onValueChange = { viewModel.setEmail(it) },
            label = {
                Text(
                    "Enter Email",
                    color = white

                )
            },
            placeholder = { Text(text = "johndoe@gmail.com") },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Pink,
                unfocusedBorderColor = DarkGray,
                textColor = white
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            value = viewModel.countryCodeState.value,
            onValueChange = { viewModel.setCountryCode(it) },
            label = {
                Text(
                    "Enter country code",
                    color = white

                )
            },
            placeholder = { Text(text = "254") },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Pink,
                unfocusedBorderColor = DarkGray,
                textColor = white
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            value = viewModel.phoneState.value,
            onValueChange = { viewModel.setPhone(it) },
            label = {
                Text(
                    "Enter Phone Number",
                    color = white

                )
            },
            placeholder = { Text(text = "0712345678") },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Pink,
                unfocusedBorderColor = DarkGray,
                textColor = white
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        Spacer(modifier = Modifier.height(4.dp))
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
            placeholder = { Text(text = "Strong Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Pink,
                unfocusedBorderColor = DarkGray,
                textColor = white
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            value = viewModel.confirmPasswordState.value,
            onValueChange = { viewModel.setConfirmPassword(it) },
            label = {
                Text(
                    "Re-enter password",
                    color = white
                )
            },
            placeholder = { Text(text = "Strong Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Pink,
                unfocusedBorderColor = DarkGray,
                textColor = white
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Button(
            colors = ButtonDefaults.buttonColors(Orange),
            onClick = {
                viewModel.registerUsers()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(text = "Sign up", color = white)
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = buildAnnotatedString {
                val boldStyle = SpanStyle(
                    color = Orange
                )
                append("Already have and account?")
                pushStyle(boldStyle)
                append(" Sign in here")
            },
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp)
                .clickable {
                    navigator.popBackStack()
                    navigator.navigate(LoginScreenDestination)
                },
            color = white,
        )

        val context = LocalContext.current

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(CenterHorizontally)
            )
        }

        LaunchedEffect(state) {
            if (state.error != null) {
                Toast.makeText(context, state.error.toString(), Toast.LENGTH_SHORT).show()
            }
            if (state.isSuccessful) {
                Toast.makeText(context, state.successMessage, Toast.LENGTH_SHORT).show()
                Toast.makeText(context, "Check your email to verify your account", Toast.LENGTH_SHORT).show()
                navigator.popBackStack()
                navigator.navigate(LoginScreenDestination)
            }
        }

    }
}