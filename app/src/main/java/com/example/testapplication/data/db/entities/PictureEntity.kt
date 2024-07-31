package com.example.testapplication.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pictures_table")
data class PictureEntity (
    @PrimaryKey
    val id: Int
)