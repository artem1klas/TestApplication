package com.example.testapplication.data.db.database_dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testapplication.data.db.entities.AlbumEntity

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAlbum(albumEntity: AlbumEntity)

    @Query("SELECT * FROM album_table WHERE name = :name")
    suspend fun getAlbum(name: String): AlbumEntity

    @Query("SELECT name FROM album_table")
    suspend fun getNamesAlbums(): List<String>
}