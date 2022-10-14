package com.example.noteapp

import androidx.lifecycle.ViewModel
import com.example.noteapp.repository.NoteRepository

class MainViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    fun updateDB() {
        noteRepository.getManagerList()
    }
}
