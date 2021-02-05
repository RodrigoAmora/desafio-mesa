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

        val token = checkToken()
        changeActivity(token)
    }

    private fun checkToken() : String ? {
        val tokenDao = TokenDao();
        return tokenDao.getAccessToken(this)
    }

    private fun changeActivity(token : String?) {
        val intent  = if (token.isNullOrEmpty()) {
            Intent(this, LoginActivity::class.java)
        } else {
            Intent(this, MainActivity::class.java)
        }

        startActivity(intent)
    }

}