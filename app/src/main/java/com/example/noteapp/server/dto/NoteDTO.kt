package com.example.noteapp.server.dto

data class NoteDTO(
    var title: String,
    var description: String,
    var changeDate: Long,
    var id: Int = 0
)
