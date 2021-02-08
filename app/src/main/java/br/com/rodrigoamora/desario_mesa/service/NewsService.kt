package br.com.rodrigoamora.desario_mesa.service

import br.com.rodrigoamora.desario_mesa.callback.model.ListaNews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap

interface NewsService {

    //@Headers("Content-Type: application/json")
    @GET("v1/client/news")
    fun listNews(@HeaderMap headers: Map<String, String>) : Call<ListaNews>

}