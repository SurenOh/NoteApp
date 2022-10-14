package com.example.noteapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.noteapp.database.Database
import com.example.noteapp.database.entity.NoteEntity
import com.example.noteapp.mappers.NoteDTOMapper
import com.example.noteapp.mappers.NoteModelMapper
import com.example.noteapp.model.NoteModel
import com.example.noteapp.server.Server

class NoteRepositoryImpl(private val db: Database, private val modelMapper: NoteModelMapper, private val dtoMapper: NoteDTOMapper): NoteRepository {

    override fun getAllNotes(): LiveData<List<NoteModel>> =
        Transformations.map(db.noteDao().getAllNotes()) { modelMapper.mapListFromEntity(it) }

    override fun insert(noteEntity: NoteModel) {
        db.noteDao().insert(modelMapper.mapToEntity(noteEntity))
    }

    override fun update(noteEntity: NoteModel) {
        db.noteDao().update(modelMapper.mapToEntity(noteEntity))
    }

    override fun delete(noteEntity: NoteModel) {
        db.noteDao().delete(modelMapper.mapToEntity(noteEntity))
    }

    //update db from server
    override fun getManagerList(): List<NoteModel> {
        return modelMapper.mapListFromEntity(dtoMapper.mapListToEntity(Server.getManagerList()))
    }

}