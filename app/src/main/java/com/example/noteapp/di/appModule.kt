package com.example.noteapp.di

import androidx.room.Room
import com.example.noteapp.App
import com.example.noteapp.database.Database
import com.example.noteapp.repository.NoteRepository
import com.example.noteapp.repository.NoteRepositoryImpl
import com.example.noteapp.screens.detail.DetailViewModel
import com.example.noteapp.screens.home.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    single {androidApplication() as App }

    //Database
    single {
        Room.databaseBuilder(
            androidContext(),
            Database::class.java,
            Database.DB_NAME
        )
            .allowMainThreadQueries()
            .build()
    }

    //Repositories
    single<NoteRepository> { NoteRepositoryImpl(get()) }

    //ViewModels
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}