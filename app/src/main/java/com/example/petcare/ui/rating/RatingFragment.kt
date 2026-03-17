package com.example.petcare.ui.rating

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.petcare.R
import com.example.petcare.data.local.AppDatabase
import com.example.petcare.databinding.RatingLayoutBinding
import com.example.petcare.model.Rating
import kotlinx.coroutines.launch

class RatingFragment : Fragment(R.layout.rating_layout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = RatingLayoutBinding.bind(view)

        val db = AppDatabase.getDatabase(requireContext())

        binding.btnSaveRating.setOnClickListener {
            val stars = binding.rbUserRating.rating // Captura las estrellas
            val comment = binding.etComment.text.toString()

            if (stars > 0) {
                viewLifecycleOwner.lifecycleScope.launch {
                    val nuevaCalificacion = Rating(
                        caretakerId = 1, // Aquí podrías pasar el ID real por bundle
                        score = stars,
                        comment = comment
                    )

                    db.ratingDao().insertRating(nuevaCalificacion)

                    Toast.makeText(requireContext(), "¡Gracias por calificar!", Toast.LENGTH_SHORT).show()

                    // Regresar a la pantalla anterior
                    findNavController().popBackStack()
                }
            } else {
                Toast.makeText(requireContext(), "Por favor selecciona al menos una estrella", Toast.LENGTH_SHORT).show()
            }
        }
    }
}