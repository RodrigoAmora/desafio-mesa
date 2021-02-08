package br.com.rodrigoamora.desario_mesa.callback

import br.com.rodrigoamora.desario_mesa.model.Token
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupCallback : Callback<Token> {

    override fun onFailure(call: Call<Token>, t: Throwable) {
        //
    }

    override fun onResponse(call: Call<Token>, response: Response<Token>) {
        if (response.isSuccessful()) {

        } else {

        }
    }

}