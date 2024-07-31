package com.example.testapplication.data.db.database_dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testapplication.data.db.entities.AlbumEntity
import com.example.testapplication.data.db.entities.PictureEntity

@Database(version = 1, entities = [AlbumEntity::class, PictureEntity::class])
abstract class AppDatabase: RoomDatabase() {
    abstract fun albumDao(): AlbumDao
    abstract fun pictureDao(): PictureDao
}