package com.example.petcare.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "services")
data class Service(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val caretakerId: Int,
    val title: String,
    val description: String,
    val price: Double,
    val status: String, // "ACTIVE", "INACTIVE"
    // NUEVOS CAMPOS DE CONTACTO DEL CUIDADOR
    val caretakerPhone: String = "",
    val caretakerEmail: String = ""
)