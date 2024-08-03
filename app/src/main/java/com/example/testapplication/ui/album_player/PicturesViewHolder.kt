package com.example.testapplication.ui.album_player

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.testapplication.R
import com.example.testapplication.databinding.PictureItemBinding
import java.io.File

class PicturesViewHolder(private val binding: PictureItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(picture: Int) {

        val file =
            File(itemView.context.filesDir, "$picture.jpg")

        val bitmap: Bitmap = BitmapFactory.decodeFile(file.absolutePath)

        Glide.with(itemView)
            .load(bitmap)
            .placeholder(R.drawable.close)
            .transform(CenterCrop(), RoundedCorners(8))
            .into(binding.pictureImage)



    }
}