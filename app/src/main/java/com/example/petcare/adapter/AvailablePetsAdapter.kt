package com.example.petcare.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petcare.databinding.ItemAvailablePetBinding
import com.example.petcare.model.Pet

class AvailablePetsAdapter(private var pets: MutableList<Pet>) :
    RecyclerView.Adapter<AvailablePetsAdapter.PetViewHolder>() {

    fun updateList(newList: List<Pet>) {
        pets.clear()
        pets.addAll(newList)
        notifyDataSetChanged()
    }

    inner class PetViewHolder(val binding: ItemAvailablePetBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val binding = ItemAvailablePetBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val pet = pets[position]
        holder.binding.apply {
            tvPetName.text = pet.name
            tvPetInfo.text = "${pet.breed} • ${pet.age} años"
            tvPetCity.text = "📍 ${pet.city}"

            btnCallOwner.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${pet.ownerPhone}"))
                root.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount() = pets.size
}