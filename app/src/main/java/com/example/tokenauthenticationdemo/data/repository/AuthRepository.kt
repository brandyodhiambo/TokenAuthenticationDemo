package com.example.tokenauthenticationdemo.data.repository

import android.content.SharedPreferences
import com.example.tokenauthenticationdemo.data.remote.ApiService
import com.example.tokenauthenticationdemo.data.remote.request.LoginRequest
import com.example.tokenauthenticationdemo.data.remote.request.RegisterRequest
import com.example.tokenauthenticationdemo.data.remote.response.forgotpassword.ForgotPassworResponse
import com.example.tokenauthenticationdemo.data.remote.response.loginres.LoginResponse
import com.example.tokenauthenticationdemo.data.remote.response.registerres.RegisterResponse
import com.example.tokenauthenticationdemo.utils.Resource
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.lang.Exception

class AuthRepository(
    private val apiService: ApiService,
    private val pref: SharedPreferences
) {

    //register
    suspend fun registerUser(registerRequest: RegisterRequest): Resource<RegisterResponse> {
        return try {
            val response = apiService.registerUser(registerRequest)
            Timber.d(response.message)

            if (response.message == "user created successfully") {
                Resource.Success(data = response)
            } else {
                Resource.Failure(response.errors.errr)
            }
        } catch (e: IOException) {
            return Resource.Failure("Oops! couldn't reach server, check your internet connection.")
        } catch (e: HttpException) {
            if (e.code() == 409) {
                return Resource.Failure("User already exist")
            } else if (e.code() == 400) {
                return Resource.Failure("CountryCode must be a valid numeric value")
            }
            return Resource.Failure("Oops! something went wrong. Please try again")

        }
    }

    //login
    suspend fun loginUser(loginRequest: LoginRequest): Resource<LoginResponse> {
        return try {
            val response = apiService.loginUser(loginRequest)
            pref.edit()
                .putString("token", response.accessToken)
                .apply()

            Resource.Success(response)

        } catch (e: IOException) {
            return Resource.Failure("Oops! couldn't reach server, check your internet connection.")
        } catch (e: HttpException) {
            if (e.code() == 404) {
                return Resource.Failure("Incorrect password, check your credential")
            }
            return Resource.Failure("Oops! something went wrong. Please try again")
        }
    }

    //forgot password
    fun forgotPassword(email: String): Resource<ForgotPassworResponse> {
        return try {
            val response = apiService.forgotPassword(email)
            Resource.Success(response)
        } catch (e: Exception) {
            return Resource.Failure("Not Registered")
        }
    }

    suspend fun authorise(): Resource<Unit> {
        return try {
            val token = pref.getString("token", null) ?: return Resource.Failure("Not Verified")
            apiService.authorise("Bearer $token")
            Resource.Authorised()
        } catch (e: Exception) {
            return Resource.Failure("Unknown Error")
        }
    }

    fun autoLogin(): Resource<String?> {
        Timber.d("AutoLogin Called")
        return try {
            val token = pref.getString("token", null)
            Timber.d(" token $token")
            if (token!=null){
                Timber.d("success")
                Resource.Success(token)
            }else {
                Timber.d("failure")
                Resource.Failure("Unknown Error")
            }
        } catch (e: Exception) {
            return Resource.Failure("Unknown Error")
        }

    }


}

