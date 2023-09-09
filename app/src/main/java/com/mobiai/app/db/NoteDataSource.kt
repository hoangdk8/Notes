package com.mobiai.app.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class NoteDataSource: CoroutineScope {
    suspend fun getListNote(): LiveData<List<Note>> {
        val listNote = withContext(Dispatchers.IO) {
            AppDataBase.getDatabaseClient().noteDao().getAllNotes()
        }
        val result = MutableLiveData<List<Note>>()
        result.value = listNote
        return result
    }

    suspend fun getIdNote(): LiveData<List<Long>> {
        val listNote = withContext(Dispatchers.IO) {
            AppDataBase.getDatabaseClient().noteDao().getIdNote()
        }
        val result = MutableLiveData<List<Long>>()
        result.value = listNote
        return result
    }
    fun addNote(note: Note) {
        launch(Dispatchers.IO) {
            AppDataBase.getDatabaseClient().noteDao().insert(note)
        }
    }
    fun updateNote(note: Note){
        launch(Dispatchers.IO){
            AppDataBase.getDatabaseClient().noteDao().update(note)
        }
    }
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}
