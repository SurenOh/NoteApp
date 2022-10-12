package com.example.noteapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.noteapp.database.entity.NoteEntity

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: NoteEntity)

    @Delete
    fun delete(item: NoteEntity)

    @Update
    fun update(item: NoteEntity)

    @Query("SELECT * FROM NoteEntity ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<NoteEntity>>
}