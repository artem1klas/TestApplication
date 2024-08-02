package com.example.testapplication.data.convertors

import androidx.room.util.splitToIntList
import com.example.testapplication.data.db.entities.AlbumEntity
import com.example.testapplication.domain.models.Album

class AlbumDbConvertor {
    fun map(album: Album): AlbumEntity {
        var idsPictures = ""

        if (album.pictures.isNotEmpty()){
            idsPictures = album.pictures.map{it.toString()}.joinToString ( "," )
        }
        return AlbumEntity(
            name = album.name,
            pictures = idsPictures
        )
    }

    fun map(album: AlbumEntity): Album {
        var idsPictures = mutableListOf<Int>()
        if (album.pictures.isNotEmpty()){
            idsPictures.addAll(album.pictures.split(",").map { it.toInt() }.toMutableList())
        }
        return Album(
            name = album.name,
            pictures = idsPictures
        )
    }


}