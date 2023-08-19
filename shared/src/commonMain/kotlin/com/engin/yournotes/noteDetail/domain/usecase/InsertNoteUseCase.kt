package com.engin.yournotes.noteDetail.domain.usecase

import com.engin.yournotes.core.domain.Note
import com.engin.yournotes.core.domain.NoteRepository
import com.engin.yournotes.core.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InsertNoteUseCase(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(note : Note) : Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        emit(Resource.Success(noteRepository.insertNote(note)))
    }
}