package com.example.noteapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.noteapp.database.entity.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Note)

    @Delete
    fun delete(item: Note)

    @Update
    fun update(item: Note)

    @Query("SELECT * FROM NOTE ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Note>>
}