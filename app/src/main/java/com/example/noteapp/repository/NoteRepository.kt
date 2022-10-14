package com.example.noteapp.repository

import androidx.lifecycle.LiveData
import com.example.noteapp.models.NoteModel

interface NoteRepository {

    fun getAllNotes(): List<NoteModel>
    fun insert(noteEntity: NoteModel)
    fun update(noteEntity: NoteModel)
    fun delete(noteEntity: NoteModel)
    fun getManagerList(): List<NoteModel>

}