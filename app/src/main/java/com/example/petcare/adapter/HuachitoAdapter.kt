package com.example.petcare.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.animation.with
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
// CAMBIO AQUÍ: Importamos el binding horizontal
import com.example.petcare.databinding.ItemHuachitoHorizontalBinding
import com.example.petcare.model.HuachitoPet

class HuachitoAdapter(private val pets: List<HuachitoPet>) :
    RecyclerView.Adapter<HuachitoAdapter.ViewHolder>() {

    // CAMBIO AQUÍ: Usamos el Binding horizontal en el ViewHolder
    class ViewHolder(val binding: ItemHuachitoHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // CAMBIO AQUÍ: Inflamos el layout horizontal
        val binding = ItemHuachitoHorizontalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pet = pets[position]
        holder.binding.apply {
            tvPetName.text = pet.nombre
            // Nota: En el diseño horizontal pusimos tvPetLocation en lugar de tvLocation
            tvPetLocation.text = "📍 ${pet.comuna}"

            // Cargar imagen con Glide
            Glide.with(holder.itemView.context)
                .load(pet.imagen)
                .centerCrop()
                .into(ivPetPhoto)
        }
    }

    override fun getItemCount() = pets.size
}