package br.com.rodrigoamora.desario_mesa.application

import android.app.Application
import br.com.rodrigoamora.desario_mesa.component.DaggerLoginComponent
import br.com.rodrigoamora.desario_mesa.component.DaggerNewsComponent
import br.com.rodrigoamora.desario_mesa.component.LoginComponent
import br.com.rodrigoamora.desario_mesa.component.NewsComponent


class MyApplication : Application() {

    lateinit var loginComponent : LoginComponent
    lateinit var newsComponent: NewsComponent

    override fun onCreate() {
        super.onCreate()
        createComponents()
    }

    private fun createComponents() {
        loginComponent = DaggerLoginComponent.create()
        newsComponent = DaggerNewsComponent.create()
    }

}