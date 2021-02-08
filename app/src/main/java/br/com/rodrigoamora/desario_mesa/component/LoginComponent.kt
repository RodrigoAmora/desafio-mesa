package br.com.rodrigoamora.desario_mesa.component

import br.com.rodrigoamora.desario_mesa.module.LoginModule
import br.com.rodrigoamora.desario_mesa.ui.login.LoginActivity
import br.com.rodrigoamora.desario_mesa.ui.signup.SignupActivity
import dagger.Component

@Component(modules = [LoginModule::class])
interface LoginComponent {

    fun inject(loginActivity : LoginActivity)
    fun inject(signinActivity : SignupActivity)

}