package com.example.petcare.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String,
    val role: String, // "OWNER" o "CARETAKER"
    val age: Int,
    val city: String,
    // Campos específicos para el Cuidador (pueden ser nulos para el dueño)
    val specialty: String? = null,
    val experience: String? = null,
    val ratingAverage: Double = 0.0
)