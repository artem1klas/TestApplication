package com.example.testapplication.di

import androidx.room.Room
import com.example.testapplication.data.db.database_dao.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database.db").build()
    }
}