package com.example.testapplication.ui.album_player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.domain.api_impl.AlbumInteractorImpl
import com.example.testapplication.domain.models.Album
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumPlayerViewModel(val albumInteractorImpl: AlbumInteractorImpl): ViewModel() {

    private val stateLiveData = MutableLiveData<AlbumPlayerState>(AlbumPlayerState.Loading)
    fun observeState(): LiveData<AlbumPlayerState> = stateLiveData


    fun fillData(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            albumInteractorImpl
                .getAlbum(name)
                .collect{
                    album ->
                    processResult(album)
                }
        }
    }

    private fun processResult(album: Album) {
        renderState(AlbumPlayerState.Content(album))
    }

    private fun renderState(state: AlbumPlayerState) {
        stateLiveData.postValue(state)
    }



}