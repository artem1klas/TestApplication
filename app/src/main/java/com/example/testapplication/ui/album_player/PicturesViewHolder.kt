package com.example.testapplication.ui.album_player

import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.databinding.PictureItemBinding

class PicturesViewHolder(private val binding: PictureItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(picture: Int) {
        binding.pictureTitle.text = picture.toString()



    }
}