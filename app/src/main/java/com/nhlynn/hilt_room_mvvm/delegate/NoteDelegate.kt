package com.nhlynn.hilt_room_mvvm.delegate

import com.nhlynn.hilt_room_mvvm.db.NoteEntity

interface NoteDelegate {
    fun onViewNote(note: NoteEntity)
}