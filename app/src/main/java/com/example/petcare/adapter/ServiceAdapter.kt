package com.example.petcare.adapter

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.petcare.R
import com.example.petcare.databinding.ItemServiceBinding
import com.example.petcare.model.Service

class ServiceAdapter(private var services: MutableList<Service>) :
    RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {

    fun updateList(newList: List<Service>) {
        services.clear()
        services.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val binding = ItemServiceBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = services[position]
        val context = holder.itemView.context

        holder.binding.apply {
            // 1. Llenamos los textos de la tarjeta
            tvCaretakerName.text = service.title
            tvSpecialty.text = service.description
            tvCity.text = "Precio: $${service.price}"

            // 2. ACCIÓN: Clic en la tarjeta para ir al detalle
            holder.itemView.setOnClickListener {
                val bundle = Bundle().apply {
                    putInt("caretakerId", service.caretakerId)
                    // PASAMOS EL NOMBRE AQUÍ PARA QUE APAREZCA AL INSTANTE
                    putString("caretakerName", service.title)
                }

                try {
                    // Intento navegar desde ServiceList
                    it.findNavController().navigate(
                        R.id.action_serviceListFragment_to_caretakerPublicViewFragment,
                        bundle
                    )
                } catch (e: Exception) {
                    // Si falla, intento desde Dashboard
                    it.findNavController().navigate(
                        R.id.action_dashboardFragment_to_caretakerPublicViewFragment,
                        bundle
                    )
                }
            }

            // 3. ACCIÓN: Botón CONTACTAR
            btnContact.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Datos de contacto")

                val info = """
                    👤 Cuidador: ${service.title}
                    📞 Teléfono: ${service.caretakerPhone}
                    ✉️ Correo: ${service.caretakerEmail}
                """.trimIndent()

                builder.setMessage(info)
                builder.setPositiveButton("Cerrar", null)

                builder.setNeutralButton("Llamar") { _, _ ->
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:${service.caretakerPhone}")
                    context.startActivity(intent)
                }
                builder.show()
            }
        }
    }

    override fun getItemCount() = services.size

    class ServiceViewHolder(val binding: ItemServiceBinding) : RecyclerView.ViewHolder(binding.root)
}