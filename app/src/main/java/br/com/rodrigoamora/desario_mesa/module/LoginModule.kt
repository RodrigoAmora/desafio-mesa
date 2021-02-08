package br.com.rodrigoamora.desario_mesa.module

import br.com.rodrigoamora.desario_mesa.BuildConfig
import br.com.rodrigoamora.desario_mesa.factory.RetrofitFactory
import br.com.rodrigoamora.desario_mesa.model.Usuario
import br.com.rodrigoamora.desario_mesa.service.LoginService
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    fun login() : LoginService {
        val retrofit = RetrofitFactory.createRetrofit(BuildConfig.BASE_URL_MESA_API)
        return retrofit.create(LoginService::class.java)
    }

}