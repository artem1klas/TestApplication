package com.example.testapplication.di

import com.example.testapplication.ui.camera.CameraViewModel
import com.example.testapplication.ui.collection.album.AlbumViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        CameraViewModel(get())
    }

    viewModel {
        AlbumViewModel(get())
    }
}