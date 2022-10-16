package com.example.noteapp.mappers

import com.example.noteapp.database.entity.NoteEntity
import com.example.noteapp.server.dto.NoteDto
import com.example.noteapp.util.mapper.Mapper

class NoteDtoMapper : Mapper<NoteEntity, NoteDto> {

    override fun mapFromEntity(entity: NoteEntity) = NoteDto(
        title = entity.title,
        description = entity.description,
        changeDate = entity.changeDate,
        id = entity.id
    )

    override fun mapToEntity(dto: NoteDto) = NoteEntity(
        title = dto.title,
        description = dto.description,
        changeDate = dto.changeDate,
        id = dto.id
    )
}
