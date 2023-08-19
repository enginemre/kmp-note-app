package com.engin.yournotes.core.data.local

import com.engin.yournotes.core.data.mapper.toNote
import com.engin.yournotes.core.domain.Note
import com.engin.yournotes.core.domain.NoteRepository
import com.engin.yournotes.core.util.DateTimeUtil
import com.engin.yournotes.database.NoteDatabase

class NoteRepositoryImpl(db: NoteDatabase): NoteRepository {

    private val queries = db.noteQueries

    override suspend fun insertNote(note: Note) {
        queries.insertNote(
            id = note.id,
            title = note.title,
            content = note.detail,
            colorHex = note.containerColor,
            created = DateTimeUtil.toEpochMillis(note.created)
        )
    }

    override suspend fun getNoteById(id: Long): Note? {
        return queries
            .getNoteById(id)
            .executeAsOneOrNull()
            ?.toNote()
    }

    override suspend fun getAllNotes(): List<Note> {
        return queries
            .getAllNotes()
            .executeAsList()
            .map { it.toNote() }
    }

    override suspend fun deleteNoteById(id: Long) {
        queries.deleteNoteById(id)
    }
}