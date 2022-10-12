package com.example.noteapp.repository

import androidx.lifecycle.LiveData
import com.example.noteapp.model.NoteModel

interface NoteRepository {

    fun getAllNotes(): LiveData<List<NoteModel>>
    fun insert(noteEntity: NoteModel)
    fun update(noteEntity: NoteModel)
    fun delete(noteEntity: NoteModel)

}