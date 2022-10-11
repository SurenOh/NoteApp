package com.example.noteapp.repository

import com.example.noteapp.database.Database
import com.example.noteapp.database.entity.Note

class NoteRepositoryImpl(private val db: Database): NoteRepository {

    override fun getAllNotes() = db.noteDao().getAllNotes()

    override fun insert(note: Note) {
        db.noteDao().insert(note)
    }

}