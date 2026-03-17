package com.example.petcare.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "requests")
data class Request(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val serviceId: Int,
    val ownerId: Int,
    val caretakerId: Int,
    val petId: Int,
    val message: String,
    val status: String = "PENDING" // PENDING, ACCEPTED, REJECTED
)