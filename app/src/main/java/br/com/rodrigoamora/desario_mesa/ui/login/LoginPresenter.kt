package br.com.rodrigoamora.desario_mesa.ui.login

import android.content.Context
import br.com.rodrigoamora.desario_mesa.R
import br.com.rodrigoamora.desario_mesa.application.MyApplication
import br.com.rodrigoamora.desario_mesa.callback.LoginCallback
import br.com.rodrigoamora.desario_mesa.dao.TokenDao
import br.com.rodrigoamora.desario_mesa.model.Token
import br.com.rodrigoamora.desario_mesa.model.Usuario
import br.com.rodrigoamora.desario_mesa.service.LoginService
import br.com.rodrigoamora.desario_mesa.util.NetworkUtil
import retrofit2.Call
import javax.inject.Inject

class LoginPresenter(context: Context) : LoginContract.Presenter {

    @Inject
    lateinit var service : LoginService

    lateinit var view: LoginContract.View
    lateinit var call : Call<Token>
    val context: Context = context

    var callback : LoginCallback

    init {
        callback = LoginCallback(this)
        injectComponents()
    }

    override fun login(email: String, password: String) {
        if (email.isEmpty() && password.isEmpty()) {
            view.showError(context.getString(R.string.error_email_or_password_empty))
            return
        }

        if (NetworkUtil.checkConnection(context)) {
            view.showProgressBar()

            val name = ""
            val usuario = Usuario(name, email, password)

            call = service.signIn(usuario)
            call.enqueue(callback)
        }  else {
            view.showError(context.getString(R.string.error_no_internet))
        }
    }

    override fun goToMainActivity() {
        if (callback.token != null) {
            var tokenDao = TokenDao()
            var token :Token = callback.token!!
            tokenDao.saveAccessToken(context, token.token)
            view.goToMainActivity()
        } else {
            view.showError(context.getString(R.string.error_login))
        }

        view.hideProgressBar()
    }

    private fun injectComponents() {
        val myApplication = this.context.applicationContext as MyApplication
        val component = myApplication.loginComponent
        component.inject(this)
    }

}