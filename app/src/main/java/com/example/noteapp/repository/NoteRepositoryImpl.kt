package com.example.noteapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.noteapp.database.Database
import com.example.noteapp.mappers.NoteMapper
import com.example.noteapp.model.NoteModel

class NoteRepositoryImpl(private val db: Database, private val mapper: NoteMapper): NoteRepository {

    override fun getAllNotes(): LiveData<List<NoteModel>> =
        Transformations.map(db.noteDao().getAllNotes()) { mapper.listMapFromEntity(it) }

    override fun insert(noteEntity: NoteModel) {
        db.noteDao().insert(mapper.mapToEntity(noteEntity))
    }

    override fun update(noteEntity: NoteModel) {
        db.noteDao().update(mapper.mapToEntity(noteEntity))
    }

    override fun delete(noteEntity: NoteModel) {
        db.noteDao().delete(mapper.mapToEntity(noteEntity))
    }

}