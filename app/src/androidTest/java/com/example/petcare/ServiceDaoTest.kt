package com.example.petcare

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.petcare.data.local.AppDatabase
import com.example.petcare.data.local.ServiceDao
import com.example.petcare.model.Service
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ServiceDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var serviceDao: ServiceDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        serviceDao = database.serviceDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndGetAllServices() = runBlocking {
        val service1 = Service(
            id = 1,
            caretakerId = 10,
            title = "Paseo de perros",
            description = "Paseo de 1 hora",
            price = 15.0,
            status = "ACTIVE"
        )
        val service2 = Service(
            id = 2,
            caretakerId = 11,
            title = "Cuidado nocturno",
            description = "Cuidado en casa",
            price = 30.0,
            status = "ACTIVE"
        )

        serviceDao.insertService(service1)
        serviceDao.insertService(service2)

        val allServices = serviceDao.getAllServicesSync()
        assertEquals(2, allServices.size)
        assertEquals("Paseo de perros", allServices[0].title)
        assertEquals("Cuidado nocturno", allServices[1].title)
    }
}
