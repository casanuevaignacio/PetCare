package com.example.petcare.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.petcare.model.Pet

@Dao
interface PetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(pet: Pet)

    // Esta sirve para ViewModels (con LiveData)
    @Query("SELECT * FROM pets")
    fun getAllPets(): LiveData<List<Pet>>

    // ESTA ES LA QUE FALTA: Para el DashboardFragment
    @Query("SELECT * FROM pets")
    suspend fun getAllPetsSync(): List<Pet>

    @Query("SELECT * FROM pets WHERE ownerId = :ownerId")
    fun getPetsByOwner(ownerId: Int): LiveData<List<Pet>>

    @Query("SELECT * FROM pets WHERE id = :petId")
    suspend fun getPetById(petId: Int): Pet?

    @Query("DELETE FROM pets WHERE id = :petId")
    suspend fun deletePet(petId: Int)
}