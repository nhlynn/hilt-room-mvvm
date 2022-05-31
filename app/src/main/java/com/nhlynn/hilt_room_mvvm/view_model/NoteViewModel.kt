package com.nhlynn.hilt_room_mvvm.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhlynn.hilt_room_mvvm.db.NoteEntity
import com.nhlynn.hilt_room_mvvm.respsitory.UserRepository
import dagger.Provides
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    private var noteList: MutableLiveData<List<NoteEntity>> = MutableLiveData()

    fun getAllNote(): MutableLiveData<List<NoteEntity>> {
        loadUser()
        return noteList
    }

    private fun loadUser() =
        viewModelScope.launch {
            val notes = repository.getAllNote()
            noteList.postValue(notes)
        }

    fun createNote(note: NoteEntity) =
        viewModelScope.launch {
            repository.createNote(note)
//            loadUser()  // reload data
        }

    fun updateNote(id: Int, description: String) =
        viewModelScope.launch {
            repository.updateNote(id, description)
//            loadUser() // reload data
        }
}