package com.example.petcare.ui.login

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.petcare.R
import com.example.petcare.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentLoginBinding.bind(view)

        // Función lógica para Login
        fun loginUser(role: String, actionId: Int) {
            val name = binding.etUserName.text.toString().trim()
            val email = binding.etUserEmail.text.toString().trim()
            val phone = binding.etUserPhone.text.toString().trim()

            if (name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty()) {
                val prefs = requireActivity().getSharedPreferences("PetCarePrefs", Context.MODE_PRIVATE)
                prefs.edit().apply {
                    putString("user_name", name)
                    putString("user_role", role)
                    putString("user_email", email)
                    putString("user_phone", phone)
                    putInt("user_id", (1..1000).random())
                    apply()
                }
                // Navegación usando la ID de la acción del nav_graph
                findNavController().navigate(actionId)
            } else {
                Toast.makeText(requireContext(), "Completa todos los datos", Toast.LENGTH_SHORT).show()
            }
        }

        // 1. Botón Dueño -> Ficha de Mascota
        binding.btnEnterAsOwner.setOnClickListener {
            loginUser("dueño", R.id.action_loginFragment_to_petProfileFragment)
        }

        // 2. Botón Cuidador -> Mis Habilidades (Crear Servicio)
        binding.btnEnterAsCaretaker.setOnClickListener {
            loginUser("cuidador", R.id.action_loginFragment_to_createServiceFragment)
        }

        // 3. Botón Dashboard (Netflix) -> Acceso directo
        binding.btnGoToDashboard.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
        }
    }
}