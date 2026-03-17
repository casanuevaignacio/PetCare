package com.example.petcare.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petcare.R
import com.example.petcare.adapter.AvailablePetsAdapter
import com.example.petcare.adapter.ServiceAdapter
import com.example.petcare.data.local.AppDatabase
import com.example.petcare.databinding.FragmentDashboardBinding
import kotlinx.coroutines.launch

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDashboardBinding.bind(view)

        // Inicializar la base de datos
        val db = AppDatabase.getDatabase(requireContext())

        // 1. Configurar RecyclerView de MASCOTAS (Horizontal)
        // Pasamos una lista mutable vacía al inicio
        val petAdapter = AvailablePetsAdapter(mutableListOf())
        binding.rvPetsNetflix.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = petAdapter
        }

        // 2. Configurar RecyclerView de SERVICIOS (Horizontal)
        val serviceAdapter = ServiceAdapter(mutableListOf())
        binding.rvServicesNetflix.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = serviceAdapter
        }

        // 3. Cargar datos desde Room usando Corrutinas
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                // Obtenemos los datos de la DB
                val petsFromDb = db.petDao().getAllPetsSync()
                val servicesFromDb = db.serviceDao().getAllServicesSync()

                // Actualizamos los adaptadores con los datos reales
                petAdapter.updateList(petsFromDb)
                serviceAdapter.updateList(servicesFromDb)

            } catch (e: Exception) {
                // Si hay error (ej. tabla vacía), lo imprime en consola
                e.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}