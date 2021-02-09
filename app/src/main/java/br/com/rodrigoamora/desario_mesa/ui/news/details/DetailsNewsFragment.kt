package br.com.rodrigoamora.desario_mesa.ui.news.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.rodrigoamora.desario_mesa.R
import br.com.rodrigoamora.desario_mesa.model.News
import kotlinx.android.synthetic.main.fragment_details_news.*

class DetailsNewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_details_news, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val news: News = arguments?.get("news") as News

        val webView = web_view
        webView.loadUrl(news.url)
    }

}