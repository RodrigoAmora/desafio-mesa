package br.com.rodrigoamora.desario_mesa.ui.news.presenter

import android.content.Context
import br.com.rodrigoamora.desario_mesa.R
import br.com.rodrigoamora.desario_mesa.application.MyApplication
import br.com.rodrigoamora.desario_mesa.callback.NewsCallback
import br.com.rodrigoamora.desario_mesa.callback.model.ListaNews
import br.com.rodrigoamora.desario_mesa.dao.TokenDao
import br.com.rodrigoamora.desario_mesa.service.NewsService
import br.com.rodrigoamora.desario_mesa.ui.news.NewsContract
import br.com.rodrigoamora.desario_mesa.util.NetworkUtil
import retrofit2.Call
import javax.inject.Inject

class NewsPresenter(context: Context) :
    NewsContract.NewsPresenter {

    @Inject
    lateinit var service : NewsService

    lateinit var view: NewsContract.View
    lateinit var call : Call<ListaNews>
    val context: Context = context

    var callback : NewsCallback

    init {
        callback = NewsCallback(this)
        injectComponents()
    }

    override fun searchNews() {
        if (NetworkUtil.checkConnection(context)) {
            view.showProgressBar()

            call = service.listNews(createHeaders())
            call.enqueue(callback)
        } else {
            view.showError(context.getString(R.string.error_no_internet))
        }
    }

    override fun showError() {
        view.showError(context.getString(R.string.error_load_news))
        view.hideProgressBar()
    }

    override fun populateRecyclerView() {
        view.populateRecyclerViewOfNews(callback.data?.data)
        view.hideProgressBar()
    }

    private fun injectComponents() {
        val myApplication = this.context.applicationContext as MyApplication
        val component = myApplication.newsComponent
        component.inject(this)
    }

    private fun createHeaders(): HashMap<String, String> {
        val token = "Bearer " + TokenDao.getAccessToken(context)

        var headers = HashMap<String, String>()
        headers["Content-Type"] = "application/json"
        headers["Authorization"] = token

        return headers
    }

}