package br.com.rodrigoamora.desario_mesa.callback

import android.util.Log
import br.com.rodrigoamora.desario_mesa.model.Token
import br.com.rodrigoamora.desario_mesa.ui.login.LoginPresenter
import br.com.rodrigoamora.desario_mesa.ui.signup.SignupPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginCallback (presenter: LoginPresenter) : Callback<Token> {

    var token: Token? = null
    var presenter: LoginPresenter = presenter

    override fun onFailure(call: Call<Token>, t: Throwable) {
        Log.i("onFailure", call.execute().message())
        presenter.goToMainActivity()
    }

    override fun onResponse(call: Call<Token>, response: Response<Token>) {
        if (response.isSuccessful()) {
            token = response.body()
        }
        presenter.goToMainActivity()
    }

}