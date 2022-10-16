package com.example.noteapp.repository

import com.example.noteapp.database.Database
import com.example.noteapp.mappers.NoteDtoMapper
import com.example.noteapp.mappers.NoteModelMapper
import com.example.noteapp.models.NoteModel
import com.example.noteapp.server.Server

class NoteRepositoryImpl(
    private val db: Database,
    private val modelMapper: NoteModelMapper,
    private val dtoMapper: NoteDtoMapper
) : NoteRepository {

    override fun getAllNotes() =
        modelMapper.mapFromEntity(db.noteDao().getAllNotes())

    override fun insert(noteEntity: NoteModel) {
        db.noteDao().insert(modelMapper.mapToEntity(noteEntity))
    }

    override fun update(noteEntity: NoteModel) {
        db.noteDao().update(modelMapper.mapToEntity(noteEntity))
    }

    override fun delete(noteEntity: NoteModel) {
        db.noteDao().delete(modelMapper.mapToEntity(noteEntity))
    }

    // update db from server
    override fun getNotesFromServer(): List<NoteModel> {
        db.noteDao().insertList(dtoMapper.mapToEntity(Server.getNotesList()))
        return modelMapper.mapFromEntity(db.noteDao().getAllNotes())
    }
}
