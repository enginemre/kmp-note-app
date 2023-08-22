package com.engin.yournotes.core.util

import cafe.adriel.voyager.core.screen.Screen
import com.engin.yournotes.di.AppModule

abstract class BaseScreen(open val appModule: AppModule) : Screen{
    abstract var onAppBarComposing : ((AppBarState) -> Unit)?

}