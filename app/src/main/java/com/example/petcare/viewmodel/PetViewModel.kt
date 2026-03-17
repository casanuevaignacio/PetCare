package com.example.petcare.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.petcare.data.local.AppDatabase
import com.example.petcare.data.repository.PetRepository
import com.example.petcare.model.Pet
import kotlinx.coroutines.launch

class PetViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PetRepository

    // ESTO ES LO QUE FALTABA:
    // Exponemos la lista de mascotas como un LiveData para que el Fragment la observe
    val allPets: LiveData<List<Pet>>

    init {
        val petDao = AppDatabase.getDatabase(application).petDao()
        repository = PetRepository(petDao)

        // Inicializamos allPets con los datos del repositorio
        allPets = repository.getAllPets()
    }

    fun savePet(pet: Pet, onComplete: () -> Unit) {
        viewModelScope.launch {
            repository.insertPet(pet)
            onComplete()
        }
    }
}