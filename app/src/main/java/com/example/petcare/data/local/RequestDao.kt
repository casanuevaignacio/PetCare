package com.example.petcare.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.petcare.model.Request

@Dao
interface RequestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRequest(request: Request)

    @Query("SELECT * FROM requests WHERE caretakerId = :caretakerId")
    suspend fun getRequestsForCaretaker(caretakerId: Int): List<Request>
}