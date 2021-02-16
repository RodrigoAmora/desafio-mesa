package br.com.rodrigoamora.desario_mesa.util

import android.content.Context

class PackageInfoUtil {

    companion object {
        fun getVersionName(context: Context): String {
            val packageManager = context.getPackageManager()
            val pInfo = packageManager.getPackageInfo(context.getPackageName(), 0)
            return pInfo.versionName
        }
    }

}