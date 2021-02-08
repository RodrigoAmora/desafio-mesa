package br.com.rodrigoamora.desario_mesa.ui.login

interface LoginContract {

    interface View {
        fun showProgressBar()
        fun hideProgressBar()
        fun showError(message: String)
        fun goToSignupActivity()
        fun goToMainActivity()
        fun login()
    }

    interface Presenter {
        fun login(login: String, password: String)
        fun goToMainActivity()
    }

}