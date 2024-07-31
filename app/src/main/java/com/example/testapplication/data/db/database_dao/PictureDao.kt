package com.example.testapplication.data.db.database_dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testapplication.data.db.entities.PictureEntity

@Dao
interface PictureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPicture(pictureEntity: PictureEntity)

    @Query("SELECT id FROM pictures_table")
    suspend fun getIdPictures(): List<Int>
}