package com.example.tokenauthenticationdemo.data.repository

import android.widget.Toast
import com.example.tokenauthenticationdemo.data.remote.ApiService
import com.example.tokenauthenticationdemo.data.remote.SafeApiCall
import com.example.tokenauthenticationdemo.models.RefreshModel
import com.example.tokenauthenticationdemo.models.RegisterModel
import com.example.tokenauthenticationdemo.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import javax.inject.Inject

class AuthRepository @Inject constructor(private val apiService: ApiService) : SafeApiCall() {

    //register
    suspend fun registerUser(registerModel: RegisterModel): Resource<Any> {
        return withContext(Dispatchers.IO) {
            safeApiCall {
                apiService.registerUser(registerModel).enqueue(object : Callback<RefreshModel> {
                    override fun onResponse(
                        call: Call<RefreshModel>,
                        response: Response<RefreshModel>
                    ) {
                        //Toast.makeText(coroutineContext, "", Toast.LENGTH_SHORT).show()
                    }
                    override fun onFailure(call: Call<RefreshModel>, t: Throwable) {
                        //TODO("Not yet implemented")
                    }
                })
            }
        }
    }
    
}

