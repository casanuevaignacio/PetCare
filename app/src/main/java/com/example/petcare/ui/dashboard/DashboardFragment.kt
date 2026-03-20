package com.example.petcare.ui.dashboard

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petcare.R
import com.example.petcare.adapter.HuachitoAdapter // Para la API (Mascotas)
import com.example.petcare.adapter.ServiceAdapter  // Para Room (Cuidadores)
import com.example.petcare.data.local.AppDatabase
import com.example.petcare.data.remote.RetrofitClient
import com.example.petcare.databinding.FragmentDashboardBinding
import kotlinx.coroutines.launch

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDashboardBinding.bind(view)

        val db = AppDatabase.getDatabase(requireContext())

        // 1. Configurar RecyclerView de MASCOTAS de la API (Horizontal)
        binding.rvPetsNetflix.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // 2. Configurar RecyclerView de CUIDADORES de la Base de Datos (Horizontal)
        binding.rvServicesNetflix.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // 3. Cargar ambos datos usando Corrutinas
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                // --- PARTE A: TRAER MASCOTAS DE LA API (HUACHITOS) ---
                val huachitosResponse = RetrofitClient.instance.getHuachitos()
                // Usamos HuachitoAdapter porque procesa los datos de internet y usa Glide
                binding.rvPetsNetflix.adapter = HuachitoAdapter(huachitosResponse.data)

                // --- PARTE B: TRAER CUIDADORES DE LA BASE DE DATOS LOCAL ---
                val servicesFromDb = db.serviceDao().getAllServicesSync()
                binding.rvServicesNetflix.adapter = ServiceAdapter(servicesFromDb.toMutableList())

            } catch (e: Exception) {
                // Si no hay internet o la DB está vacía, mostramos un error
                Toast.makeText(requireContext(), "Error al cargar el Dashboard", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}