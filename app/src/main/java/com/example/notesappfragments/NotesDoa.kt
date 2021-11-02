package com.example.notesappfragments

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDoa {

    @Query("SELECT * FROM Notes")
    fun gettingData(): LiveData<List<Data>>

    @Insert
    fun addNewNote(note: Data)

    @Delete
    fun deleteNote(note: Data)

    @Update
    fun updateNote(note: Data)
}