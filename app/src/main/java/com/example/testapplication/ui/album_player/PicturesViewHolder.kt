package com.example.testapplication.ui.album_player

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapplication.R
import com.example.testapplication.databinding.PictureItemBinding
import java.io.File

class PicturesViewHolder(private val binding: PictureItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(picture: Int) {
        binding.pictureTitle.text = picture.toString()

        val file =
            File(itemView.context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "$picture.jpg")

        val bitmap: Bitmap = BitmapFactory.decodeFile(file.absolutePath)

        Glide.with(itemView)
            .load(bitmap)
            .placeholder(R.drawable.close)
            .into(binding.pictureImage)



    }
}