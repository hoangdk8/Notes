package com.mobiai.app.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mobiai.app.db.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<Note>

    @Insert
    suspend fun insert(note: Note)
    @Update
    suspend fun update(note: Note)
}
