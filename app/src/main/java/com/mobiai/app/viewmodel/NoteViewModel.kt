package com.mobiai.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobiai.app.db.Note
import com.mobiai.app.db.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel() : ViewModel() {
    private val repository = NoteRepository()
    private val _listNote = MediatorLiveData<List<Note>>()
    val listNote: LiveData<List<Note>>
        get() = _listNote
    fun getListNote() {
        viewModelScope.launch(Dispatchers.Main) {
            val notes = repository.getListNote()
            _listNote.addSource(notes) {
                _listNote.value = it
            }
        }
    }
    private val _listId = MediatorLiveData<List<Long>>()
    val listId: MediatorLiveData<List<Long>>
        get() = _listId
    fun getIdNote() {
        viewModelScope.launch(Dispatchers.Main) {
            val id = repository.getIdNote()
            _listId.addSource(id) {
                _listId.value = it
            }
        }
    }
    fun addNote(note: Note) {
        repository.addNote(note)
    }
    fun updateNote(note: Note) {
        repository.updateNote(note)
    }
}