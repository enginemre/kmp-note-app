package com.engin.yournotes.notes.ui

import com.engin.yournotes.core.domain.Note

data class NoteState(
    var list : List<Note> = emptyList(),
)