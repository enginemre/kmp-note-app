package com.engin.yournotes.search.ui

import com.engin.yournotes.core.domain.Note

data class SearchState(
    val isLoading : Boolean = false,
    val searchText : String = "",
    val originalList : List<Note> = emptyList(),
)
