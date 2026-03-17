package com.example.petcare.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.petcare.data.local.AppDatabase
import com.example.petcare.model.Rating
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RatingViewModel(application: Application) : AndroidViewModel(application) {

    private val ratingDao = AppDatabase.getDatabase(application).ratingDao()

    // LiveData para que el fragmento observe los comentarios
    private val _caretakerRatings = MutableLiveData<List<Rating>>()
    val caretakerRatings: LiveData<List<Rating>> get() = _caretakerRatings

    fun insertRating(rating: Rating) {
        viewModelScope.launch(Dispatchers.IO) {
            ratingDao.insertRating(rating)
            // Opcional: recargar después de insertar
            getRatingsForCaretaker(rating.caretakerId)
        }
    }

    // ESTA ES LA FUNCIÓN QUE LE FALTABA AL VIEWMODEL
    fun getRatingsForCaretaker(caretakerId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = ratingDao.getRatingsForCaretaker(caretakerId)
            _caretakerRatings.postValue(list)
        }
    }
}