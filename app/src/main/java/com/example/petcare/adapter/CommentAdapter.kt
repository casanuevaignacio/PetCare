package com.example.petcare.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petcare.databinding.ItemCommentBinding
import com.example.petcare.model.Rating

class CommentAdapter(private val ratings: List<Rating>) :
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    class CommentViewHolder(val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val rating = ratings[position]
        holder.binding.itemRatingBar.rating = rating.score.toFloat()
        holder.binding.tvCommentText.text = rating.comment
        holder.binding.tvCommentUser.text = "Dueño ID: ${rating.caretakerId}"
    }

    override fun getItemCount() = ratings.size
}