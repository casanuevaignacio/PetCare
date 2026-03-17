package com.example.petcare.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ratings")
data class Rating(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val caretakerId: Int,
    val score: Float, // Aquí se guardan las estrellas (ej: 4.5)
    val comment: String,
    val date: Long = System.currentTimeMillis()
)