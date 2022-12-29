package com.example.motivationex

import android.content.Context

class SecurityPreferences(context: Context) {

    val security = context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)

    fun getString(key: String): String {
        return security.getString(key, "") ?: ""
    }

    fun storeString(key: String, value: String) {
        security.edit().putString(key, value).commit()
    }

    fun deleteKey() {
        security.edit().putString(MotivationConstants.KEY.USER_NAME, "").commit()
    }

}