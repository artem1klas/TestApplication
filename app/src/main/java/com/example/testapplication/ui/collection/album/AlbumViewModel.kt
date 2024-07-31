package com.example.testapplication.ui.collection.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.domain.api_impl.AlbumInteractorImpl
import com.example.testapplication.domain.models.Album
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

class AlbumViewModel(private val albumInteractorImpl: AlbumInteractorImpl) : ViewModel() {


    fun addAlbum(name: String): Boolean {
        if (!albumInteractorImpl.albumIsExist(name)) {
            viewModelScope.launch {
                albumInteractorImpl.addAlbum(Album(name, mutableListOf<Int>()))
            }
            return true
        } else {
            return false
        }
    }


    fun getAlbum(name: String): Album? {
        var album: Album? = null
        if (albumInteractorImpl.albumIsExist(name)) {
            val async = CoroutineScope(Dispatchers.IO).async {
                return@async albumInteractorImpl.getAlbum(name)
            }
            runBlocking {
                album = async.await().toList()[0]
            }
        }
        return album
    }



}



