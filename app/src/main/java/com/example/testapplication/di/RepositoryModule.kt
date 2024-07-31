package com.example.testapplication.di

import com.example.testapplication.data.convertors.AlbumDbConvertor
import com.example.testapplication.data.convertors.PictureDbConvertor
import com.example.testapplication.data.impl.AlbumRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    factory {
        PictureDbConvertor()
    }

    factory {
        AlbumDbConvertor()
    }


    single {
        AlbumRepositoryImpl(get(), get(), get())
    }
}