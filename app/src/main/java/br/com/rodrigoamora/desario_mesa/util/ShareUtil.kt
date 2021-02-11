package br.com.rodrigoamora.desario_mesa.util

import android.content.Context
import android.content.Intent

class ShareUtil {

    companion object {
        fun directShare(context: Context, title: String, text: String) {
            val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(Intent.EXTRA_TEXT, title)
            sharingIntent.putExtra(Intent.EXTRA_TEXT, text)

            val intent = Intent.createChooser(sharingIntent, title)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            context.startActivity(intent)
        }
    }

}