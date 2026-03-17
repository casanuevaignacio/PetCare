package com.example.petcare.ui.caretaker

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petcare.R
import com.example.petcare.adapter.CommentAdapter
import com.example.petcare.databinding.FragmentCaretakerPublicViewBinding
import com.example.petcare.viewmodel.RatingViewModel

class CaretakerPublicViewFragment : Fragment(R.layout.fragment_caretaker_public_view) {

    private val ratingViewModel: RatingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCaretakerPublicViewBinding.bind(view)

        // 1. RECUPERAR DATOS DEL BUNDLE (ID y Nombre)
        val caretakerId = arguments?.getInt("caretakerId") ?: 0
        val nombreRecibido = arguments?.getString("caretakerName") ?: "Cuidador"

        // 2. SETEAR EL NOMBRE REAL AL INSTANTE
        // Esto sobrescribe "Nombre del Cuidador" antes de que el usuario lo vea
        binding.tvProfileName.text = nombreRecibido

        // 3. Lógica de ROL (Seguridad)
        val prefs = requireActivity().getSharedPreferences("PetCarePrefs", Context.MODE_PRIVATE)
        val userRole = prefs.getString("user_role", "dueño")

        if (userRole == "cuidador") {
            binding.btnRateNow.visibility = View.GONE
        } else {
            binding.btnRateNow.visibility = View.VISIBLE
            binding.btnRateNow.setOnClickListener {
                val bundle = Bundle().apply {
                    putInt("caretakerId", caretakerId)
                }
                findNavController().navigate(R.id.action_caretakerPublicViewFragment_to_ratingFragment, bundle)
            }
        }

        // 4. Configurar RecyclerView para los comentarios
        binding.rvComments.layoutManager = LinearLayoutManager(requireContext())

        // 5. Observar cambios en los comentarios
        ratingViewModel.caretakerRatings.observe(viewLifecycleOwner) { lista ->
            binding.rvComments.adapter = CommentAdapter(lista)

            // Actualizar el RatingBar con el promedio real de los comentarios
            if (lista.isNotEmpty()) {
                val promedio = lista.map { it.score }.average().toFloat()
                binding.profileRatingBar.rating = promedio
            }
        }

        // 6. Cargar los datos desde el ViewModel
        ratingViewModel.getRatingsForCaretaker(caretakerId)
    }
}