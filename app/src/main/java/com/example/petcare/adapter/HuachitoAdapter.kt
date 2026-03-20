package com.example.petcare.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petcare.databinding.ItemHuachitoBinding
import com.example.petcare.model.HuachitoPet

class HuachitoAdapter(private val pets: List<HuachitoPet>) :
    RecyclerView.Adapter<HuachitoAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemHuachitoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHuachitoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pet = pets[position]
        holder.binding.apply {
            tvPetName.text = pet.nombre
            tvPetTypeAndAge.text = "${pet.tipo} • ${pet.edad}"
            tvLocation.text = "📍 ${pet.comuna}"

            // Cargar imagen con Glide
            Glide.with(holder.itemView.context)
                .load(pet.imagen)
                .centerCrop()
                .into(ivPetPhoto)
        }
    }

    override fun getItemCount() = pets.size
}