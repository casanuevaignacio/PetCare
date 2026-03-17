package com.example.petcare.ui.caretaker

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petcare.R
import com.example.petcare.adapter.AvailablePetsAdapter
import com.example.petcare.databinding.FragmentAvailablePetsBinding
import com.example.petcare.viewmodel.PetViewModel

class AvailablePetsFragment : Fragment(R.layout.fragment_available_pets) {

    private val viewModel: PetViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAvailablePetsBinding.bind(view)

        // Configuración del RecyclerView
        binding.rvAvailablePets.layoutManager = LinearLayoutManager(requireContext())

        // Observar los datos del ViewModel
        viewModel.allPets.observe(viewLifecycleOwner) { listaMascotas ->
            // EL CAMBIO ESTÁ AQUÍ: Agregamos .toMutableList()
            // Esto soluciona el error "List vs MutableList"
            binding.rvAvailablePets.adapter = AvailablePetsAdapter(listaMascotas.toMutableList())
        }
    }
}