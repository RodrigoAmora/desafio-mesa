package br.com.rodrigoamora.desario_mesa.module

import br.com.rodrigoamora.desario_mesa.BuildConfig
import br.com.rodrigoamora.desario_mesa.factory.RetrofitFactory
import br.com.rodrigoamora.desario_mesa.service.NewsService
import dagger.Module
import dagger.Provides

@Module
class NewsModule {

    @Provides
    fun login() : NewsService {
        val retrofit = RetrofitFactory.createRetrofit(BuildConfig.BASE_URL_MESA_API)
        return retrofit.create(NewsService::class.java)
    }

}