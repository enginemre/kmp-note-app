package com.engin.yournotes.core.domain

interface NoteRepository {
    suspend fun getNoteById(id : Long) : Note?
    suspend fun getAllNotes() : List<Note>
    suspend fun insertNote(note : Note)
    suspend fun deleteNoteById(id : Long)
}