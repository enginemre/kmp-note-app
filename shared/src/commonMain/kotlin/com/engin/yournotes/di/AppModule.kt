package com.engin.yournotes.di

import com.engin.yournotes.core.domain.NoteRepository
import com.engin.yournotes.noteDetail.domain.usecase.DeleteNoteByIdUseCase
import com.engin.yournotes.noteDetail.domain.usecase.GetNoteByIdUseCase
import com.engin.yournotes.noteDetail.domain.usecase.InsertNoteUseCase
import com.engin.yournotes.notes.domain.usecase.GetAllNoteUseCase

expect class AppModule {
    val noteRepository : NoteRepository
    val getAllNoteUseCase : GetAllNoteUseCase
    val deleteNoteByIdUseCase : DeleteNoteByIdUseCase
    val insertNoteUseCase : InsertNoteUseCase
    val getNoteByIdUseCase : GetNoteByIdUseCase
}