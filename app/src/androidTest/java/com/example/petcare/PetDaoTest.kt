package com.example.petcare

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.petcare.data.local.AppDatabase
import com.example.petcare.data.local.PetDao
import com.example.petcare.model.Pet
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PetDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var petDao: PetDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        petDao = database.petDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndGetPet() = runBlocking {
        val pet = Pet(
            id = 1,
            ownerId = 101,
            name = "Firulais",
            age = 3,
            type = "Perro",
            breed = "Labrador"
        )
        petDao.insertPet(pet)

        val retrievedPet = petDao.getPetById(1)
        assertEquals(pet.name, retrievedPet?.name)
        assertEquals(pet.ownerId, retrievedPet?.ownerId)
    }

    @Test
    fun deletePet() = runBlocking {
        val pet = Pet(
            id = 2,
            ownerId = 102,
            name = "Michi",
            age = 2,
            type = "Gato"
        )
        petDao.insertPet(pet)
        petDao.deletePet(2)

        val retrievedPet = petDao.getPetById(2)
        assertEquals(null, retrievedPet)
    }
}
