package com.engin.yournotes.noteDetail.ui

import com.engin.yournotes.core.domain.Note
import com.engin.yournotes.core.ui.AppUIEvent
import com.engin.yournotes.core.util.DateTimeUtil
import com.engin.yournotes.core.util.Resource
import com.engin.yournotes.noteDetail.domain.usecase.DeleteNoteByIdUseCase
import com.engin.yournotes.noteDetail.domain.usecase.GetNoteByIdUseCase
import com.engin.yournotes.noteDetail.domain.usecase.InsertNoteUseCase
import com.engin.yournotes.noteDetail.ui.Fields.*
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteDetailViewModel(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val deleteNoteByIdUseCase: DeleteNoteByIdUseCase,
    private val insertNoteUseCase: InsertNoteUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(NoteDetailState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<AppUIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var jobGetNote: Job? = null
    private var jobSaveNote: Job? = null
    private var jobDeleteNote: Job? = null
    private var jobUpdateNote: Job? = null

    fun onEvent(detailEvent: NoteDetailEvent) {
        when (detailEvent) {
            is NoteDetailEvent.ChangeScreenMode -> {
                _state.update {
                    it.copy(
                        detailModes = detailEvent.detailMode
                    )
                }
            }

            is NoteDetailEvent.Idle -> {
                detailEvent.noteId?.let {
                    if (detailEvent.noteId == 0L)
                        _state.update {
                            it.copy(
                                detailModes = DetailModes.ADD
                            )
                        }
                    else
                        getNotesById(detailEvent.noteId)
                }
            }

            is NoteDetailEvent.OnDeleteNote -> {
                deleteNote()
            }

            is NoteDetailEvent.OnSaveNote -> {
                state.value.note?.let { saveNote() } ?: insertNote(
                    state.value.titleText,
                    state.value.descriptionText
                )
            }

            is NoteDetailEvent.OnValueChange -> {
                when (detailEvent.type) {
                    Title -> {
                        _state.update {
                            it.copy(
                                titleText = detailEvent.changedValue
                            )
                        }
                    }

                    Description -> {
                        _state.update {
                            it.copy(
                                descriptionText = detailEvent.changedValue
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getNotesById(id: Long) {
        jobGetNote?.cancel()
        jobGetNote = getNoteByIdUseCase(id).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(isLoading = false)
                    }
                    // TODO show Error
                }

                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }

                is Resource.Success -> {
                    result.data?.let {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                note = result.data,
                                titleText = result.data.title,
                                descriptionText = result.data.detail,
                                detailModes = DetailModes.READ
                            )
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun saveNote() {
        jobSaveNote?.cancel()
        val savedNote = state.value.note?.copy(
            title = state.value.titleText,
            detail = state.value.descriptionText
        )
        jobSaveNote = insertNoteUseCase(savedNote).onEach { resource ->
            when (resource) {
                is Resource.Error -> {
                    val ses = resource.message
                    _state.update {
                        it.copy(isLoading = false)
                    }
                }

                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }

                is Resource.Success -> {
                    resource.data?.let {
                        _state.update {
                            it.copy(
                                isLoading = false
                            )
                        }
                        _uiEvent.send(AppUIEvent.Navigate())
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun deleteNote() {
        jobDeleteNote?.cancel()
        state.value.note?.id?.let {
            jobDeleteNote = viewModelScope.launch {
                deleteNoteByIdUseCase(it).collectLatest {
                    _uiEvent.send(AppUIEvent.Navigate())
                }
            }

        } ?: run {
            // TODO Show Error
        }

    }

    private fun insertNote(title: String, description: String) {
        val note = Note(
            id = null,
            title = title,
            detail = description,
            containerColor = Note.generateRandomColor(),
            created = DateTimeUtil.now()
        )
        jobUpdateNote?.cancel()
        jobUpdateNote = insertNoteUseCase(note).onEach { resource ->
            when (resource) {
                is Resource.Error -> _state.update {
                    it.copy(isLoading = false)
                }

                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }

                is Resource.Success -> {
                    resource.data?.let {
                        _state.update {
                            it.copy(
                                isLoading = false,
                            )
                        }
                        _uiEvent.send(AppUIEvent.Navigate())
                    }
                }
            }
        }.launchIn(viewModelScope)

    }
}