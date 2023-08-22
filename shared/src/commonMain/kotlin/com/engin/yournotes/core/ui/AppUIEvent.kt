package com.engin.yournotes.core.ui

import com.engin.yournotes.core.util.ScreenKeys

sealed interface AppUIEvent {
    data class ShowToastMessage(val message : String) : AppUIEvent
    data class Navigate(val route : ScreenKeys? =null) : AppUIEvent
}