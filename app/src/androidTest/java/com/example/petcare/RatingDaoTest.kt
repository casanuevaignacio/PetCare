package com.example.petcare

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.petcare.data.local.AppDatabase
import com.example.petcare.data.local.RatingDao
import com.example.petcare.model.Rating
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RatingDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var ratingDao: RatingDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        ratingDao = database.ratingDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndGetRatingsForCaretaker() = runBlocking {
        val rating1 = Rating(
            caretakerId = 5,
            score = 5.0f,
            comment = "Excelente servicio"
        )
        val rating2 = Rating(
            caretakerId = 5,
            score = 4.0f,
            comment = "Muy puntual"
        )
        val ratingOther = Rating(
            caretakerId = 10,
            score = 1.0f,
            comment = "Llegó tarde"
        )

        ratingDao.insertRating(rating1)
        ratingDao.insertRating(rating2)
        ratingDao.insertRating(ratingOther)

        val ratingsForCaretaker5 = ratingDao.getRatingsForCaretaker(5)
        assertEquals(2, ratingsForCaretaker5.size)
        assertEquals("Excelente servicio", ratingsForCaretaker5[0].comment)
        assertEquals("Muy puntual", ratingsForCaretaker5[1].comment)
    }
}
