package br.com.rodrigoamora.desario_mesa.service

import br.com.rodrigoamora.desario_mesa.model.Token
import br.com.rodrigoamora.desario_mesa.model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {

    @Headers("Content-Type: application/json")
    @POST("v1/client/auth/signup")
    fun signUp(@Body usuario: Usuario) : Call<Token>

    @Headers("Content-Type: application/json")
    @POST("v1/client/auth/signin")
    fun signIn(@Body usuario: Usuario) : Call<Token>

}