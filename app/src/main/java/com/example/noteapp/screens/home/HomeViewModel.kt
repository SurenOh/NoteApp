package com.example.noteapp.screens.home

import androidx.lifecycle.ViewModel
import com.example.noteapp.database.entity.Note
import com.example.noteapp.repository.NoteRepository
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(private val noteRepository: NoteRepository): ViewModel() {
    private val DEFAULT_FORMAT = SimpleDateFormat("dd.MM.yyyy")
    private val TODAY_FORMAT = SimpleDateFormat("hh:mm")
    private val DEFAULT_TITLE = "Новая заметка"

    fun getAllNotes () = noteRepository.getAllNotes()

    fun addNewNote() {
        noteRepository.insert(Note(DEFAULT_TITLE, "", Date().time))
    }
}