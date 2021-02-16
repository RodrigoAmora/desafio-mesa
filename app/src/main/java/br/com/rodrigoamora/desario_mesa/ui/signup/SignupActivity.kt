package br.com.rodrigoamora.desario_mesa.ui.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.rodrigoamora.desario_mesa.MainActivity
import br.com.rodrigoamora.desario_mesa.R
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity(), View.OnClickListener, SignupContract.View {

    lateinit var presenter : SignupPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        instantiatePresenter()
        inflateLayoutComponents()
    }

    override fun onClick(view: View?) {
        if (view == bt_signup) {
            signup()
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

    override fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun signup() {
        presenter.signup(
            input_name.text.toString(),
            input_email.text.toString(),
            input_password.text.toString()
        )
    }

    private fun instantiatePresenter() {
        presenter = SignupPresenter(baseContext)
        presenter.view = this
    }

    private fun inflateLayoutComponents() {
        val btLogin = bt_signup
        btLogin.setOnClickListener(this)
    }

}