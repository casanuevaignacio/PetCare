package com.example.petcare.ui.caretaker

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petcare.R
import com.example.petcare.adapter.HuachitoAdapter // Usamos el adaptador de la API
import com.example.petcare.databinding.FragmentAvailablePetsBinding
import com.example.petcare.viewmodel.PetViewModel

class AvailablePetsFragment : Fragment(R.layout.fragment_available_pets) {

    private val viewModel: PetViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAvailablePetsBinding.bind(view)

        // 1. Configuración del RecyclerView
        binding.rvAvailablePets.layoutManager = LinearLayoutManager(requireContext())

        // 2. Llamar a la API al iniciar la vista
        viewModel.fetchPetsFromApi()

        // 3. Observar los datos de la API (HuachitoPet)
        viewModel.huachitoPets.observe(viewLifecycleOwner) { listaMascotas ->
            // Si la lista no es nula, se la pasamos al HuachitoAdapter
            if (listaMascotas != null) {
                // Ya no necesitamos .toMutableList() porque el HuachitoAdapter
                // acepta una List estándar y usa Glide para las fotos
                binding.rvAvailablePets.adapter = HuachitoAdapter(listaMascotas)
            }
        }

        // 4. Observar el estado de carga (Opcional, si tienes un ProgressBar en el XML)
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            // Si tienes un progressBar con id "progressBar" en tu fragment_available_pets.xml
            // binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // 5. Manejo de errores (Opcional)
        // Podrías observar un LiveData de error en el ViewModel para mostrar un Toast
    }
}