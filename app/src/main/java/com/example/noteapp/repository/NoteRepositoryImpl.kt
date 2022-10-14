package com.example.noteapp.repository

import com.example.noteapp.database.Database
import com.example.noteapp.mappers.NoteDTOMapper
import com.example.noteapp.mappers.NoteModelMapper
import com.example.noteapp.models.NoteModel
import com.example.noteapp.server.Server

class NoteRepositoryImpl(
    private val db: Database,
    private val modelMapper: NoteModelMapper,
    private val dtoMapper: NoteDTOMapper
) : NoteRepository {

    override fun getAllNotes(): List<NoteModel> =
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
    override fun getManagerList(): List<NoteModel> {
        return modelMapper.mapFromEntity(dtoMapper.mapToEntity(Server.getManagerList()))
    }
}
