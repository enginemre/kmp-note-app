package com.engin.yournotes.core.util

import cafe.adriel.voyager.core.screen.Screen

abstract class BaseScreen : Screen{
    abstract var onAppBarComposing : ((AppBarState) -> Unit)?
}