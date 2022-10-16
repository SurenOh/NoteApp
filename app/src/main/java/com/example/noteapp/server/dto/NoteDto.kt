package com.example.noteapp.server.dto

data class NoteDto(
    var title: String,
    var description: String,
    var changeDate: Long,
    var id: Int = 0
)
