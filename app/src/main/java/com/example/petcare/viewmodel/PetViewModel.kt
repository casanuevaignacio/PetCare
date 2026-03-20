package com.example.petcare.viewmodel

import android.app.Application


import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.petcare.data.local.AppDatabase
import com.example.petcare.data.remote.RetrofitClient
import com.example.petcare.data.repository.PetRepository
import com.example.petcare.model.HuachitoPet
import com.example.petcare.model.Pet
import kotlinx.coroutines.launch

class PetViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PetRepository

    // 1. Datos locales (Room)
    val allPetsLocal: LiveData<List<Pet>>

    // 2. Datos remotos (API Huachitos)
    private val _huachitoPets = MutableLiveData<List<HuachitoPet>>()
    val huachitoPets: LiveData<List<HuachitoPet>> = _huachitoPets

    // 3. Estado de carga
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        val petDao = AppDatabase.getDatabase(application).petDao()
        repository = PetRepository(petDao)

        // Inicializamos la lista local de Room
        allPetsLocal = repository.getAllPets()
    }

    // --- FUNCIÓN PARA CONSUMIR LA API ---
    fun fetchPetsFromApi() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Llamamos a Retrofit a través del cliente que creamos
                val response = RetrofitClient.instance.getHuachitos()
                _huachitoPets.value = response.data // Guardamos la lista 'data' del JSON
            } catch (e: Exception) {
                // En caso de error (sin internet, etc), enviamos lista vacía
                _huachitoPets.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Función para guardar mascota localmente (tu código original)
    fun savePet(pet: Pet, onComplete: () -> Unit) {
        viewModelScope.launch {
            repository.insertPet(pet)
            onComplete()
        }
    }
}