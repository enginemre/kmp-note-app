package com.engin.yournotes.noteDetail.domain.usecase

import com.engin.yournotes.core.domain.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DeleteNoteByIdUseCase(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(id : Long) : Flow<Unit> = flow{
        emit(noteRepository.deleteNoteById(id))
    }
}