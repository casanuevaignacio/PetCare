package com.example.petcare.data.local

import androidx.room.*
import com.example.petcare.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: Int): User?

    @Query("UPDATE users SET ratingAverage = :newAverage WHERE id = :userId")
    suspend fun updateRating(userId: Int, newAverage: Double)
}