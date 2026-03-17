package com.example.petcare.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pets")
data class Pet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val ownerId: Int,
    val name: String,
    val age: Int,
    val type: String = "",
    val breed: String = "",
    val specialCare: String = "",
    val diseases: String = "",
    val city: String = "",
    // NUEVOS CAMPOS DE CONTACTO
    val ownerPhone: String = "",
    val ownerEmail: String = ""
)