package com.engin.yournotes.noteDetail.ui

import com.engin.yournotes.core.util.Resource
import com.engin.yournotes.noteDetail.domain.usecase.DeleteNoteByIdUseCase
import com.engin.yournotes.noteDetail.domain.usecase.GetNoteByIdUseCase
import com.engin.yournotes.noteDetail.domain.usecase.InsertNoteUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update

class NoteDetailViewModel(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val deleteNoteByIdUseCase: DeleteNoteByIdUseCase,
    private val insertNoteUseCase: InsertNoteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(NoteDetailState())
    val state = _state.asStateFlow()

    private var jobGetNote : Job? = null
    private var jobSaveNote : Job? = null

    fun getNotesById(id : Long){
        jobGetNote?.cancel()
        jobGetNote = getNoteByIdUseCase(id).onEach { result ->
            when(result){
                is Resource.Error -> {}
                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    result.data?.let {
                        _state.update {
                            it.copy(
                                note = result.data,
                                titleText = result.data.title
                            )
                        }
                    }
                }
            }
        } .launchIn(viewModelScope)
    }
}