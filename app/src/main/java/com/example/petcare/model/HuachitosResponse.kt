package com.example.petcare.model

data class HuachitosResponse(
    val data: List<HuachitoPet> // La API envuelve la lista en un campo llamado "data"
)