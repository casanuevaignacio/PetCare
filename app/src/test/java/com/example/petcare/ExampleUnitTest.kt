package com.example.petcare

import com.example.petcare.model.Pet
import com.example.petcare.model.Service
import com.example.petcare.model.User
import com.example.petcare.model.Rating
import com.example.petcare.model.Request
import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun pet_model_initialization_isCorrect() {
        val pet = Pet(
            id = 1,
            ownerId = 10,
            name = "Luna",
            age = 5,
            type = "Perro",
            breed = "Golden Retriever"
        )
        assertEquals("Luna", pet.name)
        assertEquals(5, pet.age)
        assertEquals("Perro", pet.type)
    }

    @Test
    fun service_model_defaultValues_areCorrect() {
        val service = Service(
            caretakerId = 1,
            title = "Paseo",
            description = "Paseo diario",
            price = 10.0,
            status = "ACTIVE"
        )
        assertEquals("", service.caretakerPhone)
        assertEquals("", service.caretakerEmail)
    }

    @Test
    fun user_model_initialization_isCorrect() {
        val user = User(
            id = 1,
            name = "Juan",
            email = "juan@test.com",
            role = "CARETAKER",
            age = 28,
            city = "Santiago"
        )
        assertEquals("Juan", user.name)
        assertEquals("CARETAKER", user.role)
        assertEquals(0.0, user.ratingAverage, 0.0)
    }

    @Test
    fun rating_model_initialization_isCorrect() {
        val rating = Rating(
            caretakerId = 2,
            score = 4.5f,
            comment = "Muy buen servicio"
        )
        assertEquals(4.5f, rating.score)
        assertEquals("Muy buen servicio", rating.comment)
    }

    @Test
    fun request_model_defaultStatus_isPending() {
        val request = Request(
            serviceId = 1,
            ownerId = 10,
            caretakerId = 2,
            petId = 5,
            message = "Hola, me interesa el servicio"
        )
        assertEquals("PENDING", request.status)
    }
}
