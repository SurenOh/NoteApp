package com.example.noteapp.repository

import androidx.lifecycle.LiveData
import com.example.noteapp.database.entity.Note

interface NoteRepository {

    fun getAllNotes(): LiveData<List<Note>>
    fun insert(note: Note)
    fun update(note: Note)
    fun delete(note: Note)

}