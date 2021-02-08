package br.com.rodrigoamora.desario_mesa.factory

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


class RetrofitFactory {

    companion object {
        fun createRetrofit(baseUrl : String) : Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

}