package com.example.petcare.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.petcare.model.Pet
import com.example.petcare.model.Service
import com.example.petcare.model.Rating // Importamos la nueva entidad

@Database(
    entities = [Pet::class, Service::class, Rating::class], // Agregamos Rating aquí
    version = 2, // Incrementamos la versión porque cambiamos la estructura
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun petDao(): PetDao
    abstract fun serviceDao(): ServiceDao
    abstract fun ratingDao(): RatingDao // Agregamos el acceso al DAO de calificaciones

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "petcare_database"
                )
                    .fallbackToDestructiveMigration() // IMPORTANTE: Esto borrará la DB vieja y creará la nueva con Ratings
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}