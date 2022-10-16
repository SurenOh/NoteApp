package com.example.noteapp.screens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.models.NoteModel
import com.example.noteapp.repository.NoteRepository
import com.example.noteapp.util.connection.NetworkHelper
import com.example.noteapp.util.preferences.AppPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(private val noteRepository: NoteRepository) : ViewModel() {
    val notes = MutableLiveData<List<NoteModel>>()
    val progress = MutableLiveData<Boolean>()
    val progressBar = MutableLiveData<Int>()

    fun getNotesFromServer(isFirstAction: Boolean) {
        if (NetworkHelper.checkNetwork()) {
            if (isFirstAction) progress.postValue(true)
            GlobalScope.launch(Dispatchers.IO) {
                getAllNotes()
                delay(5000)
                notes.postValue(noteRepository.getNotesFromServer())
                AppPreferences.setBoolean(false)
                if (isFirstAction) {
                    progress.postValue(false)
                }
            }
        } else {
            getAllNotes()
        }
    }

    private fun getAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            notes.postValue(noteRepository.getAllNotes())
        }
    }

    fun addNewNote(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.insert(NoteModel(title, "", Date().time))
            notes.postValue(noteRepository.getAllNotes())
        }
    }

    fun deleteNote(noteModel: NoteModel) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.delete(noteModel)
            notes.postValue(noteRepository.getAllNotes())
        }
    }

    fun setupProgressBar() {
        GlobalScope.launch {
            while (NetworkHelper.checkNetwork()) {
                delay(50)
                val currentProgress = progressBar.value ?: 0
                if (currentProgress < 100) {
                    progressBar.postValue(currentProgress + 1)
                } else {
                    break
                }
            }
        }
        progressBar.value = 1
    }
}
