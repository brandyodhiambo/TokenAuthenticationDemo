package com.example.tokenauthenticationdemo.ui.screens.login

import android.content.Context
import android.content.SharedPreferences
import com.example.tokenauthenticationdemo.R

class SessionManager(context: Context) {
    private var pref:SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE)

    companion object{
        const val USER_TOKEN = "user_token"
    }

    fun saveAuthToken(token:String){
        val editor = pref.edit()
        editor.putString(USER_TOKEN,token)
        editor.apply()
    }

    fun fetchAuthToken():String?{
        return pref.getString(USER_TOKEN,null)
    }
}