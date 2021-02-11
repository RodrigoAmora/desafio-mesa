package br.com.rodrigoamora.desario_mesa.ui.news.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rodrigoamora.desario_mesa.R
import br.com.rodrigoamora.desario_mesa.model.News
import br.com.rodrigoamora.desario_mesa.ui.news.listener.OnItemListClickListener
import br.com.rodrigoamora.desario_mesa.ui.news.viewholder.NewsViewHolder
import br.com.rodrigoamora.desario_mesa.util.ShareUtil
import kotlinx.android.synthetic.main.adapter_news.view.*


class NewsAdapter(context: Context, newsList: List<News>?) : RecyclerView.Adapter<NewsViewHolder>() {

    val context: Context = context
    var newsList: List<News>? = newsList
    var listener: OnItemListClickListener<News>? = null

    override fun getItemCount(): Int = newsList!!.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.setValues(newsList!!.get(position))

        holder.itemView.lb_title.setOnClickListener ( object : View.OnClickListener {
            override fun onClick(v: View) {
                listener?.onItemClick(newsList?.get(position)!!)
            }
        })
        holder.itemView.iv_share.setOnClickListener {
            val newsSelected = newsList!!.get(position)
            val text  = newsSelected.title+" - "+newsSelected.url

            ShareUtil.directShare(context, context.getString(R.string.menu_share), text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(context)
                            .inflate(R.layout.adapter_news, parent, false)
        return NewsViewHolder(view)
    }

    fun itemClickListener(listener: OnItemListClickListener<News>?) {
        this.listener = listener
    }

}