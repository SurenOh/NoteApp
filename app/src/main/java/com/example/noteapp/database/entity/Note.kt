package com.example.noteapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Note(
    var title: String,
    var description: String,
    var changeDate: Long,
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}