package com.example.tokenauthenticationdemo.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.tokenauthenticationdemo.data.remote.ApiService
import com.example.tokenauthenticationdemo.data.repository.AuthRepository
import com.example.tokenauthenticationdemo.utils.Constants.BASE_URL
import com.example.tokenauthenticationdemo.utils.Constants.SHARED_PREF_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMainRepository(apiService: ApiService,preferences: SharedPreferences):AuthRepository{
        return AuthRepository(apiService,preferences)
    }


    @Provides
    @Singleton
    fun provideSharedPref(application: Application):SharedPreferences{
        return application.getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE)
    }
}