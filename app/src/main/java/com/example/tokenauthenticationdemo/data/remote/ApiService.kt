package com.example.tokenauthenticationdemo.data.remote

import com.example.tokenauthenticationdemo.data.remote.request.ForgotPasswordRequest
import com.example.tokenauthenticationdemo.data.remote.request.LoginRequest
import com.example.tokenauthenticationdemo.data.remote.request.RegisterRequest
import com.example.tokenauthenticationdemo.data.remote.response.forgotpassword.ForgotPassworResponse
import com.example.tokenauthenticationdemo.data.remote.response.loginres.LoginResponse
import com.example.tokenauthenticationdemo.data.remote.response.registerres.RegisterResponse
import com.example.tokenauthenticationdemo.models.*
import com.example.tokenauthenticationdemo.utils.Constants.FORGOT_PASSWORD_ENDPOINT
import com.example.tokenauthenticationdemo.utils.Constants.LOGIN_ENDPOINT
import com.example.tokenauthenticationdemo.utils.Constants.REGISTER_ENDPOINT
import com.example.tokenauthenticationdemo.utils.Constants.RESET_PASSWORD_ENDPOINT
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    //Register
    @POST(REGISTER_ENDPOINT)
   suspend fun registerUser(
        @Body registerRequest: RegisterRequest
    ): RegisterResponse


   //Login
    @POST(LOGIN_ENDPOINT)
     suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): LoginResponse


     //forgot password
    @POST(FORGOT_PASSWORD_ENDPOINT)
    fun forgotPassword(
         email:String
    ):ForgotPassworResponse

    @POST(RESET_PASSWORD_ENDPOINT)
    @FormUrlEncoded
    fun resetPassword(
        @Body resetPasswordModel: ResetPasswordModel
    ):Call<RefreshModel>


}


