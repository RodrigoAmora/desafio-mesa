package br.com.rodrigoamora.desario_mesa.model

import com.google.gson.annotations.SerializedName

data class Token (
    @SerializedName("token")
    var token : String
)