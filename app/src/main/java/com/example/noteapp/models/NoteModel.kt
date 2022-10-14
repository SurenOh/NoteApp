package com.example.noteapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteModel(
    var title: String,
    var description: String,
    var changeDate: Long,
    var id: Int = 0
) : Parcelable
