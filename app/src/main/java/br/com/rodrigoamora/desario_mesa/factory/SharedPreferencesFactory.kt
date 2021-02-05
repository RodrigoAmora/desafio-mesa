package br.com.rodrigoamora.desario_mesa.factory

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences



class SharedPreferencesFactory {

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("desafio_mesa", Context.MODE_PRIVATE)
    }


}