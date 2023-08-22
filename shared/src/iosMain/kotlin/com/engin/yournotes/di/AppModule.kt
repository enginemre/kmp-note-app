package com.engin.yournotes.di

import com.engin.yournotes.core.data.local.DatabaseDriverFactory
import com.engin.yournotes.core.data.local.NoteRepositoryImpl
import com.engin.yournotes.core.domain.NoteRepository
import com.engin.yournotes.database.NoteDatabase
import com.engin.yournotes.noteDetail.domain.usecase.DeleteNoteByIdUseCase
import com.engin.yournotes.noteDetail.domain.usecase.GetNoteByIdUseCase
import com.engin.yournotes.noteDetail.domain.usecase.InsertNoteUseCase
import com.engin.yournotes.notes.domain.usecase.GetAllNoteUseCase

actual class AppModule{
    actual val noteRepository : NoteRepository by lazy{
        NoteRepositoryImpl(
            NoteDatabase(
                DatabaseDriverFactory().createDriver()
            )
        )
    }
    actual val getAllNoteUseCase: GetAllNoteUseCase by lazy {
        GetAllNoteUseCase(noteRepository)
    }
    actual val deleteNoteByIdUseCase: DeleteNoteByIdUseCase by lazy {
        DeleteNoteByIdUseCase(noteRepository)
    }
    actual val insertNoteUseCase: InsertNoteUseCase by lazy {
        InsertNoteUseCase(noteRepository)
    }
    actual val getNoteByIdUseCase: GetNoteByIdUseCase by lazy {
        GetNoteByIdUseCase(noteRepository)
    }
}