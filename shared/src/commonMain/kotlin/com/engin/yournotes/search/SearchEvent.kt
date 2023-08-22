package com.engin.yournotes.search

sealed interface SearchEvent {
    data class Search(val query : String) : SearchEvent

    data class OnValueChange(val changedValue : String) : SearchEvent

}