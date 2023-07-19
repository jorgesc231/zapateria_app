package com.example.la_unica.data.model

import com.google.gson.annotations.SerializedName

data class ProductModel(
    @SerializedName("id") val id : Int = 0,
    @SerializedName("nombre") val name : String,
    @SerializedName("origen") val origen : String,
    @SerializedName("imagenLink") val image : String,
    @SerializedName("marca") val marca : String,
    @SerializedName("numero") val size : Int,

    @SerializedName("precio") val price : Int?,
    @SerializedName("entrega") val delivery : Boolean?,
)
