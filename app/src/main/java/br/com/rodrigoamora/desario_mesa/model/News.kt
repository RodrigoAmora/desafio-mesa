package br.com.rodrigoamora.desario_mesa.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class News(

    @SerializedName("title")
    var title : String,

    @SerializedName("description")
    var description : String,

    @SerializedName("content")
    var content : String,

    @SerializedName("author")
    var author : String,

    @SerializedName("published_at")
    var dataPublicacao : String,

    @SerializedName("highlight")
    var highlight : Boolean,

    @SerializedName("url")
    var url : String,

    @SerializedName("image_url")
    var image_url : String

): Serializable