package com.mobiai.app.db


class NoteRepository {
    private val dataSource = NoteDataSource()
    suspend fun getListNote() = dataSource.getListNote()
    fun addNote(note: Note) {
        dataSource.addNote(note)
    }
    fun updateNote(note: Note) {
        dataSource.updateNote(note)
    }
}