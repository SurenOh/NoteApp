package com.example.noteapp.repository

import com.example.noteapp.models.NoteModel

interface NoteRepository {

    fun getAllNotes(): List<NoteModel>
    fun insert(noteEntity: NoteModel)
    fun update(noteEntity: NoteModel)
    fun delete(noteEntity: NoteModel)
    fun getNotesFromServer(): List<NoteModel>
}