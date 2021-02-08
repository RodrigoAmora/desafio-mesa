package br.com.rodrigoamora.desario_mesa.util

import android.content.Context
import android.content.Intent

class ShareUtil {

    companion object {
        fun directShare(context: Context, title: String, text: String) {
            val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, text)
            context.startActivity(Intent.createChooser(sharingIntent, title))
        }
    }

}