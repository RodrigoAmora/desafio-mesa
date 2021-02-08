package br.com.rodrigoamora.desario_mesa.component

import br.com.rodrigoamora.desario_mesa.module.NewsModule
import br.com.rodrigoamora.desario_mesa.ui.news.NewsFragment
import br.com.rodrigoamora.desario_mesa.ui.news.NewsPresenter
import dagger.Component

@Component(modules = [NewsModule::class])
interface NewsComponent {

    fun inject(fragment: NewsFragment)
    fun inject(presenter: NewsPresenter)

}