package br.com.rodrigoamora.desario_mesa.model

import com.google.gson.annotations.SerializedName

data class Usuario (
    @SerializedName("name")
    var name : String,

    @SerializedName("email")
    var email : String,

    @SerializedName("password")
    var password : String
)