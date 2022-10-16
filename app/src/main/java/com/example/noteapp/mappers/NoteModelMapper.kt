package com.example.noteapp.mappers

import com.example.noteapp.database.entity.NoteEntity
import com.example.noteapp.models.NoteModel
import com.example.noteapp.util.mapper.Mapper

class NoteModelMapper : Mapper<NoteEntity, NoteModel> {

    override fun mapFromEntity(entity: NoteEntity) = NoteModel(
        title = entity.title,
        description = entity.description,
        changeDate = entity.changeDate,
        id = entity.id
    )

    override fun mapToEntity(model: NoteModel) = NoteEntity(
        title = model.title,
        description = model.description,
        changeDate = model.changeDate,
        id = model.id
    )
}
