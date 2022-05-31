package com.example.tokenauthenticationdemo.data.remote

import com.example.tokenauthenticationdemo.data.remote.request.RegisterRequest
import com.example.tokenauthenticationdemo.data.remote.response.RegisterResponse
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
    @POST("auth/register")
    //@FormUrlEncoded
   suspend fun registerUser(
        @Body registerRequest: RegisterRequest
    ): RegisterResponse

    @POST(LOGIN_ENDPOINT)
    @FormUrlEncoded
     suspend fun loginUser(
        @Body loginModel: LoginModel
    ):Call<RefreshModel>

    @POST(FORGOT_PASSWORD_ENDPOINT)
    @FormUrlEncoded
    fun forgotPassword(
        @Body forgotPasswordModel: ForgotPasswordModel
    ):Call<RefreshModel>

    @POST(RESET_PASSWORD_ENDPOINT)
    @FormUrlEncoded
    fun resetPassword(
        @Body resetPasswordModel: ResetPasswordModel
    ):Call<RefreshModel>

   /* @POST(REFRESH_ENDPOINT)
    fun refresh(
        refresh_token: String
    ):Call<RefreshModel*/
}


