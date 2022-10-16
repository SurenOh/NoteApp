package com.example.noteapp.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.models.NoteModel
import com.example.noteapp.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class DetailViewModel(private val repository: NoteRepository) : ViewModel() {

    fun updateNote(noteModel: NoteModel, title: String, description: String) {
        if (noteModel.title != title || noteModel.description != description) {
            noteModel.description = description
            noteModel.title = title
            noteModel.changeDate = Date().time
            viewModelScope.launch(Dispatchers.IO) {
                repository.update(noteModel)
            }
        }
    }
}
