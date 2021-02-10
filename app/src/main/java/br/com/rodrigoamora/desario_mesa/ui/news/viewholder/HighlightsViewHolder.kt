package br.com.rodrigoamora.desario_mesa.ui.news.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.rodrigoamora.desario_mesa.model.News
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_highlights.view.*

class HighlightsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setValues(news : News) {
        Picasso.get().load(news.image_url).into(itemView?.iv_photo_news)

        itemView?.lb_title.text = news.title
    }

}