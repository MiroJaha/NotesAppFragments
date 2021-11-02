package com.example.notesappfragments

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Data(
    @PrimaryKey(autoGenerate = true)
    val pk: Int= 0,
    val note: String
)
