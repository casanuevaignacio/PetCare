package com.example.petcare.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.petcare.data.local.AppDatabase
import com.example.petcare.model.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ServiceViewModel(application: Application) : AndroidViewModel(application) {

    private val serviceDao = AppDatabase.getDatabase(application).serviceDao()

    private val _services = MutableLiveData<List<Service>>()
    val services: LiveData<List<Service>> get() = _services

    // Al iniciar el ViewModel, cargamos los servicios automáticamente
    init {
        getAllServices()
    }

    fun insertService(service: Service) {
        viewModelScope.launch(Dispatchers.IO) {
            serviceDao.insertService(service)
            getAllServices() // Recarga la lista tras insertar
        }
    }

    fun getAllServices() {
        viewModelScope.launch(Dispatchers.IO) {
            // CAMBIO: Ahora llamamos a getAllServicesSync() que definimos en el DAO
            val list = serviceDao.getAllServicesSync()
            _services.postValue(list)
        }
    }

    // Si luego agregas filtros en el DAO, los usas aquí
    fun getServicesByCity(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            // val list = serviceDao.getServicesByCitySync(city)
            // _services.postValue(list)
        }
    }
}