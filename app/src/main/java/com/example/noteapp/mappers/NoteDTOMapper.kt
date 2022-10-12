package com.example.noteapp.mappers

import com.example.noteapp.database.entity.NoteEntity
import com.example.noteapp.server.dto.NoteDTO
import com.example.noteapp.util.mapper.Mapper

class NoteDTOMapper: Mapper<NoteEntity, NoteDTO> {

    override fun mapFromEntity(entity: NoteEntity) = NoteDTO (
        title = entity.title,
        description = entity.description,
        changeDate = entity.changeDate,
        id = entity.id)

    override fun mapToEntity(dto: NoteDTO) = NoteEntity (
        title = dto.title,
        description = dto.description,
        changeDate = dto.changeDate,
        id = dto.id)
}