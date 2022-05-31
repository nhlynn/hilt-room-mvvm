package com.nhlynn.hilt_room_mvvm.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AppDao {
    @Query("SELECT * FROM note ORDER BY id DESC")
    suspend fun getAllNote(): List<NoteEntity>

    @Insert
    suspend fun createNote(note: NoteEntity)

    @Query("UPDATE note SET updated_at = :updatedAt, description = :description WHERE note.id = :id")
    suspend fun updateNote(id: Int, updatedAt: Long, description: String)
}