package br.com.rodrigoamora.desario_mesa.ui.news.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rodrigoamora.desario_mesa.model.News
import br.com.rodrigoamora.desario_mesa.ui.news.viewholder.NewsViewHolder
import android.view.LayoutInflater
import br.com.rodrigoamora.desario_mesa.R


class NewsAdapter(context: Context, newsList: List<News>?) : RecyclerView.Adapter<NewsViewHolder>() {

    val context: Context = context
    var  newsList : List<News>? = newsList

    override fun getItemCount(): Int = newsList!!.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.setValues(newsList!!.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(context)
                            .inflate(R.layout.adapter_news, parent, false)
        return NewsViewHolder(view)
    }

}