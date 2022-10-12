package com.example.noteapp.screens.detail

import androidx.lifecycle.ViewModel
import com.example.noteapp.model.NoteModel
import com.example.noteapp.repository.NoteRepository
import java.util.*

class DetailViewModel(private val repository: NoteRepository): ViewModel() {

    fun updateNote (noteModel: NoteModel, title: String, description: String) {
        if (noteModel.title != title || noteModel.description != description) {
            noteModel.description = description
            noteModel.title = title
            noteModel.changeDate = Date().time
            repository.update(noteModel)
        }

    }

}