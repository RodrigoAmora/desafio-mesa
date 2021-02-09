package br.com.rodrigoamora.desario_mesa.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.rodrigoamora.desario_mesa.MainActivity
import br.com.rodrigoamora.desario_mesa.R
import br.com.rodrigoamora.desario_mesa.ui.signup.SignupActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), View.OnClickListener, LoginContract.View {

    lateinit var presenter : LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        instantiatePresenter()
        inflateLayoutComponents()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        moveTaskToBack(true)
    }

    override fun onClick(view: View?) {
        if (view == bt_signin) {
            login()
        }
        if (view == lb_signup) {
            goToSignupActivity()
        }
    }

    override fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun goToSignupActivity() {
        startActivity(Intent(this, SignupActivity::class.java))
    }

    override fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun login() {
        presenter.login(input_email.text.toString(), input_password.text.toString())
    }

    private fun instantiatePresenter() {
        presenter = LoginPresenter(baseContext)
        presenter.view = this
    }

    private fun inflateLayoutComponents() {
        val btLogin = bt_signin
        btLogin.setOnClickListener(this)

        val lbSignup = lb_signup
        lbSignup.setOnClickListener(this)
    }

}