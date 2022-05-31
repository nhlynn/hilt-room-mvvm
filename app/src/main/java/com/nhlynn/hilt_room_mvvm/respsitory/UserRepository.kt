package com.nhlynn.hilt_room_mvvm.respsitory

import com.nhlynn.hilt_room_mvvm.db.AppDao
import com.nhlynn.hilt_room_mvvm.db.NoteEntity
import javax.inject.Inject

class UserRepository
@Inject constructor(private val appDao: AppDao) {

    suspend fun getAllNote() : List<NoteEntity>{
        return appDao.getAllNote()
    }

    suspend fun createNote(note: NoteEntity){
        appDao.createNote(note)
    }

    suspend fun updateNote(id:Int,description:String){
        appDao.updateNote(id,System.currentTimeMillis(),description)
    }
}