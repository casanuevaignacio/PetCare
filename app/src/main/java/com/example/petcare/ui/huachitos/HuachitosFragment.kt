package com.example.petcare.ui.huachitos

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petcare.R
import com.example.petcare.adapter.HuachitoAdapter
import com.example.petcare.data.remote.RetrofitClient
import com.example.petcare.databinding.FragmentHuachitosBinding
import kotlinx.coroutines.launch

class HuachitosFragment : Fragment(R.layout.fragment_huachitos) {

    private var _binding: FragmentHuachitosBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHuachitosBinding.bind(view)

        // Configurar el RecyclerView
        binding.recyclerHuachitos.layoutManager = LinearLayoutManager(requireContext())

        // Llamada a la API usando Corrutinas
        lifecycleScope.launch {
            try {
                binding.progressBar.visibility = View.VISIBLE

                // 1. Obtener la respuesta de la API
                val response = RetrofitClient.instance.getHuachitos()

                // 2. CORRECCIÓN: Pasar solo la lista (.data) al Adapter
                binding.recyclerHuachitos.adapter = HuachitoAdapter(response.data)

                binding.progressBar.visibility = View.GONE
            } catch (e: Exception) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(
                    requireContext(),
                    "Error al conectar con la API: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}