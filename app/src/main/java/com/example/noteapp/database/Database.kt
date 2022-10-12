package com.example.noteapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.database.dao.NoteDao
import com.example.noteapp.database.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)

abstract class Database: RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        const val DB_NAME = "Database"
    }
}