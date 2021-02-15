package br.com.rodrigoamora.desario_mesa.dao

import android.content.Context
import android.content.SharedPreferences

class SettingsDao {

    companion object {
        private val TIME_TO_REFRESH = "time_to_refresh"

        private fun getSharedPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences("desafio_mesa", Context.MODE_PRIVATE)
        }

        fun saveTimeToRefreshNews(context: Context, time: Long) {
            val sharedPreferences = getSharedPreferences(context)
            val editor = sharedPreferences.edit()
            editor.putLong(TIME_TO_REFRESH, time)
            editor.commit()
        }

        fun getTimeToRefreshNews(context: Context): Long {
            val sharedPreferences = getSharedPreferences(context)
            return sharedPreferences.getLong(TIME_TO_REFRESH, 30000)
        }
    }

}