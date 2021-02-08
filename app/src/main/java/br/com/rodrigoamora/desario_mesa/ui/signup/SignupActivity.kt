package br.com.rodrigoamora.desario_mesa.ui.signup

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.rodrigoamora.desario_mesa.R
import br.com.rodrigoamora.desario_mesa.application.MyApplication
import br.com.rodrigoamora.desario_mesa.callback.SignupCallback
import br.com.rodrigoamora.desario_mesa.model.Token
import br.com.rodrigoamora.desario_mesa.model.Usuario
import br.com.rodrigoamora.desario_mesa.service.LoginService
import kotlinx.android.synthetic.main.activity_signup.*
import retrofit2.Call
import javax.inject.Inject

class SignupActivity : AppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var service : LoginService

    lateinit var call : Call<Token>
    lateinit var callback : SignupCallback

    lateinit var name: String
    lateinit var email : String
    lateinit var password : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        getComponents()

        callback = SignupCallback()

        val btLogin = bt_signup
        btLogin.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view == bt_signup) {
            signup()
        }
    }

    private fun getComponents() {
        val myApplication = this.getApplication() as MyApplication
        val component = myApplication.loginComponent
        component.inject(this)
    }

    private fun signup() {
        progress_bar.visibility = View.VISIBLE
        name = input_name.text.toString()
        email = input_email.text.toString()
        password = input_senha.text.toString()

        val usuario = Usuario(name, email, password)

        call = service.signUp(usuario)
        call.enqueue(callback)
    }

}