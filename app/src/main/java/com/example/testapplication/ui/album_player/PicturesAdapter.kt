package com.example.testapplication.ui.album_player

import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebView.PictureListener
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.databinding.PictureItemBinding
import com.example.testapplication.domain.models.Picture

class PicturesAdapter(val pictures: ArrayList<Int>, private val listener: PictureListener) : RecyclerView.Adapter<PicturesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicturesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PicturesViewHolder(PictureItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun getItemCount(): Int = pictures.size

    override fun onBindViewHolder(holder: PicturesViewHolder, position: Int) {
        holder.bind(pictures[position])
        holder.itemView.setOnClickListener {
            listener.onClick(pictures[position])
        }
    }

    fun interface PictureListener {
        fun onClick(id: Int)
    }
}