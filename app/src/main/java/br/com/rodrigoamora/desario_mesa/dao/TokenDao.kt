package br.com.rodrigoamora.desario_mesa.dao

import android.content.Context
import android.content.SharedPreferences

class TokenDao {

    private val ACCESS_TOKEN = "access_token"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("desafio_mesa", Context.MODE_PRIVATE)
    }

    fun deleteAccessToken(context: Context) {
        val sharedPreferences = getSharedPreferences(context)
        val editor = sharedPreferences.edit()
        editor.putString(ACCESS_TOKEN, "")
        editor.commit()
    }

    fun saveAccessToken(context: Context, accessToken: String) {
        val sharedPreferences = getSharedPreferences(context)
        val editor = sharedPreferences.edit()
        editor.putString(ACCESS_TOKEN, accessToken)
        editor.commit()
    }

    fun getAccessToken(context: Context): String? {
        val sharedPreferences = getSharedPreferences(context)
        return sharedPreferences.getString(ACCESS_TOKEN, "")
    }

}