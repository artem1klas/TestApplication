package com.example.testapplication.data.impl

import com.example.testapplication.data.convertors.AlbumDbConvertor
import com.example.testapplication.data.convertors.PictureDbConvertor
import com.example.testapplication.data.db.database_dao.AppDatabase
import com.example.testapplication.data.db.entities.PictureEntity
import com.example.testapplication.domain.models.Album
import com.example.testapplication.domain.models.Picture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

class AlbumRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val albumDbConvertor: AlbumDbConvertor,
    private val pictureDbConvertor: PictureDbConvertor
) {

    suspend fun addAlbum(album: Album) {
        appDatabase.albumDao().addAlbum(albumDbConvertor.map(album))
    }

    suspend fun getAlbum(name: String) : Flow<Album> = flow {
        val album = appDatabase.albumDao().getAlbum(name)
        emit(albumDbConvertor.map(album))
    }

    suspend fun getNamesAlbums(): Flow<List<String>> = flow {
        val namesAlbums = appDatabase.albumDao().getNamesAlbums()
        emit(namesAlbums)
    }

//    private suspend fun addPicture(picture: Picture) {
//        appDatabase.pictureDao().addPicture(pictureDbConvertor.map(picture))
//    }

    suspend fun getIdPictures(): Flow<List<Int>> = flow {
        val pictures = appDatabase.pictureDao().getIdPictures()
        emit(pictures)
    }

    suspend fun addPictureToAlbum(idPicture: Int, nameAlbum: String){
        var album : Album? = null
        val async = CoroutineScope(Dispatchers.IO).async {
           album = albumDbConvertor.map(appDatabase.albumDao().getAlbum(nameAlbum))
        }
        runBlocking {
            async.await()
        }
        album?.pictures?.add(idPicture)
        appDatabase.albumDao().addAlbum(albumDbConvertor.map(album!!))
        appDatabase.pictureDao().addPicture(PictureEntity(idPicture))
    }


}