package com.engin.yournotes.noteDetail.ui

sealed interface NoteDetailEvent {
    object OnSaveNote : NoteDetailEvent
    data class ChangeScreenMode(val detailMode : DetailModes) : NoteDetailEvent
    data class Idle(val noteId : Long?) : NoteDetailEvent
    object OnDeleteNote : NoteDetailEvent
    data class OnValueChange(val changedValue : String ,val type : Fields) : NoteDetailEvent
}

enum class Fields{
    Title,
    Description
}