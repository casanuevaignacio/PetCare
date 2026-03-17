package com.example.petcare.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.petcare.model.Service

@Dao
interface ServiceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertService(service: Service)

    // CAMBIAMOS EL NOMBRE AQUÍ PARA QUE COINCIDA CON EL DASHBOARD
    @Query("SELECT * FROM services")
    suspend fun getAllServicesSync(): List<Service>

    // Opcional: Si necesitas observar cambios en tiempo real en otras pantallas
    // @Query("SELECT * FROM services")
    // fun getAllServicesLiveData(): LiveData<List<Service>>
}