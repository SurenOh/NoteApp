package com.example.noteapp.screens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.models.NoteModel
import com.example.noteapp.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(private val noteRepository: NoteRepository) : ViewModel() {
    private val DEFAULT_TITLE = "Новая заметка"
    val notes = MutableLiveData<List<NoteModel>>()

    fun getAllNotes() { viewModelScope.launch(Dispatchers.IO) { notes.value = noteRepository.getAllNotes() } }

    fun addNewNote() {
        noteRepository.insert(NoteModel(DEFAULT_TITLE, "", Date().time))
    }

    fun deleteNote(noteModel: NoteModel) {
        noteRepository.delete(noteModel)
    }
}
