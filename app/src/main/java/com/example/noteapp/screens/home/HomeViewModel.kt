package com.example.noteapp.screens.home

import androidx.lifecycle.ViewModel
import com.example.noteapp.model.NoteModel
import com.example.noteapp.repository.NoteRepository
import java.util.*

class HomeViewModel(private val noteRepository: NoteRepository) : ViewModel() {
    private val DEFAULT_TITLE = "Новая заметка"

    fun getAllNotes() = noteRepository.getAllNotes()

    fun addNewNote() {
        noteRepository.insert(NoteModel(DEFAULT_TITLE, "", Date().time))
    }

    fun deleteNote(noteModel: NoteModel) {
        noteRepository.delete(noteModel)
    }

}