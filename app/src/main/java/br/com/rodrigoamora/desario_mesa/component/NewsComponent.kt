package br.com.rodrigoamora.desario_mesa.component

import br.com.rodrigoamora.desario_mesa.module.NewsModule
import br.com.rodrigoamora.desario_mesa.ui.news.presenter.HighlightsPresenter
import br.com.rodrigoamora.desario_mesa.ui.news.NewsFragment
import br.com.rodrigoamora.desario_mesa.ui.news.presenter.NewsPresenter
import dagger.Component

@Component(modules = [NewsModule::class])
interface NewsComponent {

    fun inject(fragment: NewsFragment)
    fun inject(presenter: HighlightsPresenter)
    fun inject(presenter: NewsPresenter)

}