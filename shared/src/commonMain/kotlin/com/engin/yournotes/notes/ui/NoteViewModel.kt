package com.engin.yournotes.notes.ui

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.engin.yournotes.core.domain.NoteRepository
import com.engin.yournotes.core.util.Resource
import com.engin.yournotes.notes.domain.usecase.GetAllNoteUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class NoteViewModel(
    private val getAllNoteUseCase: GetAllNoteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(NoteState())
    val state = _state.asStateFlow()

    init {
        getAllNoteUseCase().onEach { resource ->
            when(resource){
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    resource.data?.let {
                        _state.update {
                            it.copy(
                                list = resource.data
                            )
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}