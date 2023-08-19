package com.engin.yournotes.notes.domain.usecase

import com.engin.yournotes.core.domain.Note
import com.engin.yournotes.core.domain.NoteRepository
import com.engin.yournotes.core.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllNoteUseCase (
    private val noteRepository: NoteRepository
) {

    operator fun invoke() : Flow<Resource<List<Note>>>{
        return flow {
            emit(Resource.Loading())
            val list = noteRepository.getAllNotes()
            emit(Resource.Success(list))
        }
    }
}