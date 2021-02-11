package br.com.rodrigoamora.desario_mesa.ui.news

import br.com.rodrigoamora.desario_mesa.model.News

interface NewsContract {

    interface View {
        fun showProgressBar()
        fun hideProgressBar()
        fun showError(message: String)
        fun searchHighlights()
        fun searchNews()
        fun populateRecyclerViewOfHighlights(newsList: List<News>?)
        fun populateRecyclerViewOfNews(newsList: List<News>?)
    }

    interface NewsPresenter {
        fun populateRecyclerView()
        fun searchNews()
        fun showError()
    }

    interface HighlightsPresenter {
        fun populateRecyclerView()
        fun searchHighlights()
        fun showError()
    }

}