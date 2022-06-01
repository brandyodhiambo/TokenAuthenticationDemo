package com.example.tokenauthenticationdemo.data.repository

import com.example.tokenauthenticationdemo.data.remote.ApiService
import com.example.tokenauthenticationdemo.data.remote.SafeApiCall
import com.example.tokenauthenticationdemo.models.RefreshModel
import com.example.tokenauthenticationdemo.data.remote.request.RegisterRequest
import com.example.tokenauthenticationdemo.data.remote.response.RegisterResponse
import com.example.tokenauthenticationdemo.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class AuthRepository (private val apiService: ApiService){

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
            return Resource.Failure("Oops! something went wrong. Please try again")
        }

    }

}

