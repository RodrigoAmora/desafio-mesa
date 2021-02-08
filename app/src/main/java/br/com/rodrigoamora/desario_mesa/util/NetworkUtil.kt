package br.com.rodrigoamora.desario_mesa.util

import android.content.Context
import android.net.ConnectivityManager
import android.content.Context.CONNECTIVITY_SERVICE
import androidx.core.content.ContextCompat.getSystemService



class NetworkUtil {

    companion object {
        fun checkConnection(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val mobileIsConnected = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!.isConnected
            val wifiIsConnected = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!.isConnected

            return !(!mobileIsConnected && !wifiIsConnected)
        }
    }

}