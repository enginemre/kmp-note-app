package com.engin.yournotes

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.engin.yournotes.core.ui.component.NoteAppBar
import com.engin.yournotes.core.ui.component.NoteAppFloatButton
import com.engin.yournotes.core.util.AppBarState
import com.engin.yournotes.core.util.BaseScreen
import com.engin.yournotes.core.util.ScreenKeys
import com.engin.yournotes.notes.ui.NoteScreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun App() {
    Navigator(NoteScreen()) { navigator ->
        SlideTransition(navigator) { screen ->
            val baseScreen = screen as BaseScreen
            var appBarState by remember { mutableStateOf(AppBarState()) }
            Scaffold(
                topBar = {
                    NoteAppBar(
                        key = navigator.lastItem.key,
                        title = appBarState.title,
                        actions = appBarState.actions
                    )
                },
                content = { padding ->
                    Box(modifier = Modifier.padding(padding).background(color = Color(0xff252525))) {
                        baseScreen.onAppBarComposing = { appBarState = it }
                        baseScreen.Content()
                    }
                },
                floatingActionButtonPosition = FabPosition.End,
                floatingActionButton = {
                    if (screen.key == ScreenKeys.Notes)
                        NoteAppFloatButton()
                }
            )
        }
    }
}