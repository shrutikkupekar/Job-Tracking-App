package com.example.jobtracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobtracker.data.model.Note
import com.example.jobtracker.data.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NoteViewModel : ViewModel() {

    private val repository = NoteRepository()

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun loadNotes(jobId: String) {
        viewModelScope.launch {
            repository.getNotesForJob(jobId).collect { noteList ->
                _notes.value = noteList
            }
        }
    }

    fun addNote(jobId: String, text: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val note = Note(
                    id = "",
                    jobId = jobId,
                    text = text
                )
                repository.addNote(note)
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                // ✅ THIS FIXES YOUR ISSUE
                _isLoading.value = false
            }
        }
    }

    fun deleteNote(noteId: String) {
        viewModelScope.launch {
            try {
                repository.deleteNote(noteId)
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }
}
