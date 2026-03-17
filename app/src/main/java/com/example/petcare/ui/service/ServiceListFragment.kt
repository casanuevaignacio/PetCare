package com.example.petcare.ui.service

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petcare.R
import com.example.petcare.adapter.ServiceAdapter
import com.example.petcare.databinding.FragmentServiceListBinding
import com.example.petcare.viewmodel.ServiceViewModel

class ServiceListFragment : Fragment(R.layout.fragment_service_list) {

    private val viewModel: ServiceViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentServiceListBinding.bind(view)

        // 1. Configurar el RecyclerView
        binding.rvCaretakers.layoutManager = LinearLayoutManager(requireContext())

        // 2. Observar los datos del ViewModel
        viewModel.services.observe(viewLifecycleOwner) { listaServicios ->

            // IMPORTANTE: Convertimos a toMutableList() para que coincida con el Adapter
            val adapter = ServiceAdapter(listaServicios.toMutableList())

            // Si tu adaptador tiene un listener para clics, se configura aquí
            // (Asegúrate de que el constructor de ServiceAdapter acepte el click listener)

            binding.rvCaretakers.adapter = adapter
        }
    } // Cierre de onViewCreated
} // Cierre de la Clase