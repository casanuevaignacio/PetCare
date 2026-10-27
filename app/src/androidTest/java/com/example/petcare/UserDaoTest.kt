package com.example.petcare

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.petcare.data.local.AppDatabase
import com.example.petcare.data.local.UserDao
import com.example.petcare.model.User
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var userDao: UserDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        userDao = database.userDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndGetUser() = runBlocking {
        val user = User(
            id = 1,
            name = "Juan Perez",
            email = "juan@example.com",
            role = "CARETAKER",
            age = 30,
            city = "Santiago"
        )
        userDao.insertUser(user)

        val retrievedUser = userDao.getUserById(1)
        assertEquals("Juan Perez", retrievedUser?.name)
        assertEquals("CARETAKER", retrievedUser?.role)
    }

    @Test
    fun updateRating() = runBlocking {
        val user = User(
            id = 2,
            name = "Maria Lopez",
            email = "maria@example.com",
            role = "CARETAKER",
            age = 25,
            city = "Valparaiso",
            ratingAverage = 0.0
        )
        userDao.insertUser(user)
        userDao.updateRating(2, 4.5)

        val retrievedUser = userDao.getUserById(2)
        assertEquals(4.5, retrievedUser?.ratingAverage ?: 0.0, 0.01)
    }
}
