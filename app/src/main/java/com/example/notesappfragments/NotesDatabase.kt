package com.example.notesappfragments

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Data::class],version = 1,exportSchema = false)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun notesDoa(): NotesDoa

    companion object{
        @Volatile
        var instance: NotesDatabase?= null
        fun getInstance(context: Context): NotesDatabase{
            if (instance!=null)
                return instance!!
            synchronized(this) {
                instance = Room.databaseBuilder(context.applicationContext,
                    NotesDatabase::class.java, "Miro")
                    .fallbackToDestructiveMigration().build()
            }
            return instance!!
        }
    }
}