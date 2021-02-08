package br.com.rodrigoamora.desario_mesa.ui.news

import br.com.rodrigoamora.desario_mesa.model.News

interface NewsContract {

    interface View {
        fun showProgressBar()
        fun hideProgressBar()
        fun showError(message: String)
        fun searchNews()
        fun populateRecyclerView(newsList: List<News>?)
    }

    interface Presenter {
        fun populateRecyclerView()
        fun searchNews()
        fun showError()
    }

}