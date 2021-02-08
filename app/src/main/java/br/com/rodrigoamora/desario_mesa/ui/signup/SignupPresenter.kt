package br.com.rodrigoamora.desario_mesa.ui.signup

import android.content.Context
import br.com.rodrigoamora.desario_mesa.R
import br.com.rodrigoamora.desario_mesa.application.MyApplication
import br.com.rodrigoamora.desario_mesa.callback.SignupCallback
import br.com.rodrigoamora.desario_mesa.dao.TokenDao
import br.com.rodrigoamora.desario_mesa.model.Token
import br.com.rodrigoamora.desario_mesa.model.Usuario
import br.com.rodrigoamora.desario_mesa.service.LoginService
import retrofit2.Call
import javax.inject.Inject

class SignupPresenter(context: Context) : SignupContract.Presenter {

    @Inject
    lateinit var service: LoginService

    lateinit var view: SignupContract.View
    lateinit var call: Call<Token>
    val context: Context = context

    var callback: SignupCallback

    init {
        callback = SignupCallback(this)
        injectComponents()
    }

    override fun signup(name: String, email: String, password: String) {
        if (name.isEmpty()) {
            view.showError(context.getString(R.string.error_nome_vazio))
            return
        }
        if (email.isEmpty()) {
            view.showError(context.getString(R.string.error_email_vazio))
            return
        }
        if (password.isEmpty()) {
            view.showError(context.getString(R.string.error_sena_vazio))
            return
        }

        view.showProgressBar()

        val usuario = Usuario(name, email, password)

        call = service.signUp(usuario)
        call.enqueue(callback)
    }

    override fun goToMainActivity() {
        if (callback.token != null) {
            var tokenDao = TokenDao()
            var token :Token = callback.token!!
            tokenDao.saveAccessToken(context, token.token)
            view.goToMainActivity()
        } else {
            view.showError(context.getString(R.string.error_signup))
        }

        view.hideProgressBar()
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun injectComponents() {
        val myApplication = this.context.applicationContext as MyApplication
        val component = myApplication.loginComponent
        component.inject(this)
    }

}