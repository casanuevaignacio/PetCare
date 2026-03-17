package com.example.petcare.ui.service

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController // IMPORTANTE
import com.example.petcare.R
import com.example.petcare.data.local.AppDatabase
import com.example.petcare.databinding.FragmentCreateServiceBinding
import com.example.petcare.model.Service
import kotlinx.coroutines.launch

class CreateServiceFragment : Fragment(R.layout.fragment_create_service) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCreateServiceBinding.bind(view)
        val db = AppDatabase.getDatabase(requireContext())

        binding.btnPublishService.setOnClickListener {
            val title = binding.etServiceTitle.text.toString().trim()
            val description = binding.etServiceDescription.text.toString().trim()
            val priceString = binding.etServicePrice.text.toString().trim()

            if (title.isNotEmpty() && description.isNotEmpty() && priceString.isNotEmpty()) {

                // 1. RECUPERAR EL ID REAL DEL CUIDADOR (Desde SharedPreferences)
                val prefs = requireActivity().getSharedPreferences("PetCarePrefs", Context.MODE_PRIVATE)
                val realCaretakerId = prefs.getInt("user_id", 0)

                // 2. CREAR EL OBJETO SERVICIO
                val newService = Service(
                    id = 0,
                    caretakerId = realCaretakerId,
                    title = title,
                    description = description,
                    price = priceString.toDouble(),
                    status = "ACTIVE"
                )

                // 3. GUARDAR Y NAVEGAR
                viewLifecycleOwner.lifecycleScope.launch {
                    try {
                        db.serviceDao().insertService(newService)
                        Toast.makeText(requireContext(), "¡Servicio publicado!", Toast.LENGTH_SHORT).show()

                        // NAVEGACIÓN HACIA LA LISTA DE MASCOTAS
                        findNavController().navigate(R.id.action_createServiceFragment_to_availablePetsFragment)

                    } catch (e: Exception) {
                        Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}