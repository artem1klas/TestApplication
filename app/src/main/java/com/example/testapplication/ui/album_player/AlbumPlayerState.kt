package com.example.testapplication.ui.album_player

import com.example.testapplication.domain.models.Album

sealed interface AlbumPlayerState {
    object Loading : AlbumPlayerState

    data class Content(val album: Album) : AlbumPlayerState
}