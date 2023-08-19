package com.engin.yournotes.noteDetail.ui

import com.engin.yournotes.core.domain.Note

data class NoteDetailState(
    var titleText : String = "",
    var descriptionText : String = "",
    var note: Note? = null,
    var isLoading : Boolean = false,
    var isEditMode : Boolean = false,
)