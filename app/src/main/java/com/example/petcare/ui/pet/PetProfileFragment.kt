package com.example.petcare.ui.pet

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.petcare.R
import com.example.petcare.databinding.FragmentPetProfileBinding
import com.example.petcare.model.Pet
import com.example.petcare.viewmodel.PetViewModel

class PetProfileFragment : Fragment(R.layout.fragment_pet_profile) {

    private val viewModel: PetViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPetProfileBinding.bind(view)

        // 1. Recuperamos los datos del dueño desde SharedPreferences
        val prefs = requireActivity().getSharedPreferences("PetCarePrefs", Context.MODE_PRIVATE)
        val currentUserId = prefs.getInt("user_id", 0) // Usamos la llave unificada
        val ownerPhone = prefs.getString("user_phone", "") ?: ""
        val ownerEmail = prefs.getString("user_email", "") ?: ""

        binding.btnSavePet.setOnClickListener {
            // 2. Capturamos los datos básicos
            val name = binding.etPetName.text.toString().trim()
            val breed = binding.etPetBreed.text.toString().trim()
            val ageString = binding.etPetAge.text.toString().trim()
            val age = ageString.toIntOrNull() ?: 0

            // 3. Otros campos (puedes vincularlos a más EditText en tu XML)
            val type = "Mascota"
            val city = "Santiago"

            if (name.isNotEmpty()) {
                // 4. Creamos el objeto Pet con los campos de contacto
                val newPet = Pet(
                    id = 0,
                    ownerId = currentUserId,
                    name = name,
                    age = age,
                    type = type,
                    breed = breed,
                    specialCare = "Ninguno",
                    diseases = "Ninguna",
                    city = city,
                    ownerPhone = ownerPhone, // Inyectamos el teléfono del login
                    ownerEmail = ownerEmail  // Inyectamos el email del login
                )

                // 5. Guardamos en la base de datos
                viewModel.savePet(newPet) {
                    Toast.makeText(requireContext(), "¡$name se ha registrado!", Toast.LENGTH_SHORT).show()

                    // 6. Navegamos a la lista de servicios para buscar cuidadores
                    findNavController().navigate(R.id.action_petProfileFragment_to_serviceListFragment)
                }

            } else {
                Toast.makeText(requireContext(), "El nombre es obligatorio", Toast.LENGTH_SHORT).show()
            }
        }
    }
}