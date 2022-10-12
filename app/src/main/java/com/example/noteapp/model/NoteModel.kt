package com.example.noteapp.model

import java.io.Serializable

data class NoteModel(
    var title: String,
    var description: String,
    var changeDate: Long,
    var id: Int = 0,
): Serializable