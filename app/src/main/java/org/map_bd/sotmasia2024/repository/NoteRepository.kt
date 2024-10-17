package org.map_bd.sotmasia2024.repository

import androidx.room.Query
import org.map_bd.sotmasia2024.database.NoteDatabase
import org.map_bd.sotmasia2024.model.Note

class NoteRepository(private val db: NoteDatabase) {

    suspend fun insertNote(note: Note) = db.getNoteDao().insertNote(note)
    suspend fun deleteNote(note: Note) = db.getNoteDao().deleteNote(note)
    suspend fun updateNote(note: Note) = db.getNoteDao().updateNote(note)

    fun getAllNotes() = db.getNoteDao().getAllNotes()
    fun searchNote(query: String?) =db.getNoteDao().searchNote(query)
}