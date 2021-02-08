package br.com.rodrigoamora.desario_mesa.callback

import android.util.Log
import br.com.rodrigoamora.desario_mesa.model.Token
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SigninAndSignupCallback : Callback<Token> {

    var token: Token? = null

    override fun onFailure(call: Call<Token>, t: Throwable) {
        Log.i("onFailure", call.execute().message())
    }

    override fun onResponse(call: Call<Token>, response: Response<Token>) {
        if (response.isSuccessful()) {
            token = response.body()
        }
    }

}