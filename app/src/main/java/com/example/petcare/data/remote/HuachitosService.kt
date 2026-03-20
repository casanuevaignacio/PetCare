package com.example.petcare.data.remote

import com.example.petcare.model.HuachitosResponse // Cambia el import
import retrofit2.http.GET

interface HuachitosService {
    @GET("animales")
    suspend fun getHuachitos(): HuachitosResponse // Ahora devuelve el objeto con la lista dentro
}