package com.example.petcare.data.repository

import androidx.lifecycle.LiveData
import com.example.petcare.data.local.PetDao
import com.example.petcare.model.Pet

class PetRepository(private val petDao: PetDao) {

    // Esta función es la que el ViewModel necesita llamar
    fun getAllPets(): LiveData<List<Pet>> {
        return petDao.getAllPets()
    }

    suspend fun insertPet(pet: Pet) {
        petDao.insertPet(pet)
    }

    fun getPetsByOwner(ownerId: Int): LiveData<List<Pet>> {
        return petDao.getPetsByOwner(ownerId)
    }
}