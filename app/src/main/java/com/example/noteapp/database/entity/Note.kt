package com.example.noteapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    var title: String,
    var description: String,
    var changeDate: Long,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}