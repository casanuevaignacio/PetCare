package com.example.petcare.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.petcare.model.Rating

@Dao
interface RatingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRating(rating: Rating)

    @Query("SELECT * FROM ratings WHERE caretakerId = :caretakerId")
    suspend fun getRatingsForCaretaker(caretakerId: Int): List<Rating>
}