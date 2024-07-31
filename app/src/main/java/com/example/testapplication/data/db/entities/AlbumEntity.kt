package com.example.testapplication.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album_table")
data class AlbumEntity(
    @PrimaryKey
    val name: String,
    val pictures: String
)