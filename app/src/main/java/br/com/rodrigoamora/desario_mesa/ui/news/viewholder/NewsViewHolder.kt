package br.com.rodrigoamora.desario_mesa.ui.news.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.rodrigoamora.desario_mesa.model.News
import kotlinx.android.synthetic.main.adapter_news.view.*

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setValues(news : News) {
        itemView.lb_title.text = news.title
    }

}