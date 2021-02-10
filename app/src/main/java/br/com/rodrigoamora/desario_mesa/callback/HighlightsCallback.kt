package br.com.rodrigoamora.desario_mesa.callback

import br.com.rodrigoamora.desario_mesa.callback.model.ListaNews
import br.com.rodrigoamora.desario_mesa.ui.news.presenter.HighlightsPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HighlightsCallback(presenter: HighlightsPresenter) : Callback<ListaNews> {

    var data: ListaNews? = null
    var presenter: HighlightsPresenter = presenter

    override fun onFailure(call: Call<ListaNews>, t: Throwable) {
        presenter.showError()
    }

    override fun onResponse(call: Call<ListaNews>, response: Response<ListaNews>) {
        if (response.isSuccessful()) {
            data = response.body()
        }
        presenter.populateRecyclerView()
    }

}