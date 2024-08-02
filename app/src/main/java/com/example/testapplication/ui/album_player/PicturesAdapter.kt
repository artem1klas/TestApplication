package com.example.testapplication.ui.album_player

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.databinding.PictureItemBinding

class PicturesAdapter(val pictures: ArrayList<Int>) : RecyclerView.Adapter<PicturesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicturesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PicturesViewHolder(PictureItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun getItemCount(): Int = pictures.size

    override fun onBindViewHolder(holder: PicturesViewHolder, position: Int) {
        holder.bind(pictures[position])
    }
}