package br.com.rodrigoamora.desario_mesa.application

import android.app.Application
import br.com.rodrigoamora.desario_mesa.component.DaggerLoginComponent
import br.com.rodrigoamora.desario_mesa.component.LoginComponent


class MyApplication : Application() {

    lateinit var loginComponent : LoginComponent

    override fun onCreate() {
        super.onCreate()
        createComponents()
    }

    private fun createComponents() {
        loginComponent = DaggerLoginComponent.create()
    }

}