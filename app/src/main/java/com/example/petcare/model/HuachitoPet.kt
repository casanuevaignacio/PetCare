package com.example.petcare.model

import com.google.gson.annotations.SerializedName

data class HuachitoPet(
    val id: Int,
    val nombre: String,
    val tipo: String,
    val edad: String,
    val estado: String,
    val genero: String,
    val imagen: String, // URL de la foto que usaremos con Glide
    val comuna: String,
    val url: String,
    @SerializedName("desc_personalidad") val personalidad: String
)