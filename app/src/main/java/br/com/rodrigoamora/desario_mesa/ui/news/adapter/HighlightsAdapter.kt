package br.com.rodrigoamora.desario_mesa.ui.news.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rodrigoamora.desario_mesa.R
import br.com.rodrigoamora.desario_mesa.model.News
import br.com.rodrigoamora.desario_mesa.ui.news.listener.OnItemListClickListener
import br.com.rodrigoamora.desario_mesa.ui.news.viewholder.HighlightsViewHolder
import kotlinx.android.synthetic.main.adapter_news.view.*

class HighlightsAdapter(context: Context, newsList: List<News>?) : RecyclerView.Adapter<HighlightsViewHolder>() {

    val context: Context = context
    var newsList: List<News>? = newsList
    var listener: OnItemListClickListener<News>? = null

    override fun getItemCount(): Int = newsList!!.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightsViewHolder {
        val view = LayoutInflater.from(context)
                            .inflate(R.layout.adapter_highlights, parent, false)
        return HighlightsViewHolder(view)
    }

    override fun onBindViewHolder(holder: HighlightsViewHolder, position: Int) {
        holder.setValues(newsList!!.get(position))
        holder.itemView.lb_title.setOnClickListener ( object : View.OnClickListener {
            override fun onClick(v: View) {
                listener?.onItemClick(newsList?.get(position)!!)
            }
        })
    }

}