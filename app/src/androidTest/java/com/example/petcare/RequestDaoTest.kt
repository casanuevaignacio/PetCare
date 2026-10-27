package com.example.petcare

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.petcare.data.local.AppDatabase
import com.example.petcare.data.local.RequestDao
import com.example.petcare.model.Request
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RequestDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var requestDao: RequestDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        requestDao = database.requestDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndGetRequestsForCaretaker() = runBlocking {
        val request1 = Request(
            serviceId = 1,
            ownerId = 101,
            caretakerId = 201,
            petId = 1,
            message = "Solicitud para paseo",
            status = "PENDING"
        )
        val request2 = Request(
            serviceId = 2,
            ownerId = 102,
            caretakerId = 201,
            petId = 2,
            message = "Solicitud para hospedaje",
            status = "ACCEPTED"
        )
        val requestOther = Request(
            serviceId = 1,
            ownerId = 101,
            caretakerId = 202,
            petId = 1,
            message = "Otra solicitud",
            status = "PENDING"
        )

        requestDao.insertRequest(request1)
        requestDao.insertRequest(request2)
        requestDao.insertRequest(requestOther)

        val requestsForCaretaker201 = requestDao.getRequestsForCaretaker(201)
        assertEquals(2, requestsForCaretaker201.size)
        assertEquals("Solicitud para paseo", requestsForCaretaker201[0].message)
        assertEquals("ACCEPTED", requestsForCaretaker201[1].status)
    }
}
