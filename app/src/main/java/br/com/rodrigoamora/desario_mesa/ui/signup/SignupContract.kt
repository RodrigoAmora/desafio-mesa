package br.com.rodrigoamora.desario_mesa.ui.signup

interface SignupContract {

    interface View {
        fun showProgressBar()
        fun hideProgressBar()
        fun showError(message: String)
        fun goToMainActivity()
        fun signup()
    }

    interface Presenter {
        fun goToMainActivity()
        fun signup(name: String, email: String, password: String)
        fun showError()
    }

}