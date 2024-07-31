package com.example.testapplication.domain.api_impl

import com.example.testapplication.data.impl.AlbumRepositoryImpl
import com.example.testapplication.domain.models.Album
import com.example.testapplication.domain.models.Picture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AlbumInteractorImpl(val albumRepository: AlbumRepositoryImpl) {
    suspend fun addAlbum(album: Album) {
        albumRepository.addAlbum(album)
    }

    suspend fun getAlbum(name: String): Flow<Album> {
        return albumRepository.getAlbum(name)
    }

//    suspend fun getNamesAlbums() : Flow<List<String>> {
//        return albumRepository.getNamesAlbums()
//    }

    fun addPicture(nameAlbum: String, addToNewAlbum: Boolean): Int? {
        val albumIsExist = albumIsExist(nameAlbum)
        val id = createIdPicture()
        if (addToNewAlbum && !albumIsExist) {
            CoroutineScope(Dispatchers.IO).launch {
                albumRepository.addAlbum(Album(nameAlbum, mutableListOf(id)))
            }

        } else if (!addToNewAlbum && albumIsExist) {
            CoroutineScope(Dispatchers.IO).launch {
                albumRepository.addPictureToAlbum(id, nameAlbum)
            }
        } else {
            return null
        }
        return id
    }




    private fun createIdPicture(): Int {
        var ids = mutableListOf<Int>()
        val idsPicures = CoroutineScope(Dispatchers.IO).async {
            val ids = albumRepository.getIdPictures().toList().flatten()
        }
        runBlocking {
            idsPicures.await()
        }
        while (true) {
            val tempId = (0..1000).random()
            if (!ids.contains(tempId)) {
                return tempId
            }
        }
    }

    fun albumIsExist(name: String): Boolean {
        var result: Boolean
        val albumExist = CoroutineScope(Dispatchers.IO).async {
            val namesAlbumAsync = albumRepository.getNamesAlbums().toList().flatten()
            return@async namesAlbumAsync.contains(name)
        }
        runBlocking {
            result = albumExist.await()
        }
        return result
    }


}