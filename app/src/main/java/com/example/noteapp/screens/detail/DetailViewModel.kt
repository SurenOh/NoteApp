package com.example.noteapp.screens.detail

import androidx.lifecycle.ViewModel
import com.example.noteapp.database.entity.Note
import com.example.noteapp.repository.NoteRepository
import java.util.*

class DetailViewModel(private val repository: NoteRepository): ViewModel() {

    fun updateNote (note: Note, title: String, description: String) {
        if (note.title != title || note.description != description) {
            note.description = description
            note.title = title
            note.changeDate = Date().time
            repository.update(note)
        }

    }

}