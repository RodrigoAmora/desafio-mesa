package br.com.rodrigoamora.desario_mesa.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.rodrigoamora.desario_mesa.MainActivity
import br.com.rodrigoamora.desario_mesa.R
import br.com.rodrigoamora.desario_mesa.dao.TokenDao
import br.com.rodrigoamora.desario_mesa.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        changeActivity()
    }

    private fun checkToken(): String? {
        return TokenDao.getAccessToken(this)
    }

    private fun changeActivity() {
        val token = checkToken()

        val intent  = if (token.isNullOrEmpty()) {
            Intent(this, LoginActivity::class.java)
        } else {
            Intent(this, MainActivity::class.java)
        }

        startActivity(intent)
    }

}