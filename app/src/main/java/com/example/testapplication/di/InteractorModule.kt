package com.example.testapplication.di

import com.example.testapplication.domain.api_impl.AlbumInteractorImpl
import org.koin.dsl.module

val interactoreModule = module {
    factory {
        AlbumInteractorImpl(get())
    }
}