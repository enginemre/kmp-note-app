package com.engin.yournotes.search.ui

import androidx.compose.runtime.mutableStateListOf
import com.engin.yournotes.core.domain.Note
import com.engin.yournotes.core.util.Resource
import com.engin.yournotes.notes.domain.usecase.GetAllNoteUseCase
import com.engin.yournotes.search.SearchEvent
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class SearchViewModel(
    private val getAllNoteUseCase: GetAllNoteUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    val searchList = mutableStateListOf<Note>()

    init {
        getAllNote()
    }

    fun onEvent(event: SearchEvent) {
        when(event){
            is SearchEvent.Search -> {
                searchQuery(event.query)
            }
            is SearchEvent.OnValueChange -> {
                _state.update {
                    it.copy(
                        searchText = event.changedValue
                    )
                }
                searchQuery(event.changedValue)
            }
        }
    }

    private fun searchQuery(query: String) {
        if(query.isEmpty())
            return
        val resultList = state.value.originalList.filter {
            it.title.contains(query) || it.detail.contains(query)
        }
        searchList.clear()
        searchList.addAll(resultList)
    }

    private fun getAllNote() {
        getAllNoteUseCase().onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let {
                        _state.update {
                            it.copy(
                                originalList = resource.data
                            )
                        }
                    }
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

}