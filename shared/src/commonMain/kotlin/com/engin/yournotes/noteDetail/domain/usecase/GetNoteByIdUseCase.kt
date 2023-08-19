package com.engin.yournotes.noteDetail.domain.usecase

import com.engin.yournotes.core.domain.Note
import com.engin.yournotes.core.domain.NoteRepository
import com.engin.yournotes.core.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetNoteByIdUseCase(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(id : Long) : Flow<Resource<Note?>> = flow {
        emit(Resource.Loading())
        val note = noteRepository.getNoteById(id)
        note?.let {
            emit(Resource.Success(it))
        } ?: run {
            emit(Resource.Error("Note Note Found"))
        }
    }
}