package com.engin.yournotes.search.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import com.engin.yournotes.core.util.AppBarState
import com.engin.yournotes.core.util.BaseScreen
import com.engin.yournotes.core.util.ScreenKeys
import com.engin.yournotes.noteDetail.ui.NoteDetailScreen
import com.engin.yournotes.notes.data.repository.FakeData
import com.engin.yournotes.notes.ui.component.NoteCard
import com.engin.yournotes.search.ui.component.EmptySearchView
import com.engin.yournotes.search.ui.component.SearchField


class SearchScreen : BaseScreen() {
    override var onAppBarComposing: ((AppBarState) -> Unit)? = null

    override val key: ScreenKey
        get() = ScreenKeys.Search

    @Composable
    override fun Content() {
        SearchScreenContent()
    }

    @Composable
    private fun SearchScreenContent() {
        val navigator = LocalNavigator.current
        var searchText by  mutableStateOf("s")
        SearchField(
            modifier = Modifier.padding(horizontal = 12.dp),
            searchText = searchText,
            onValueChange = { searchText = it },
            onSearch = {},
            onCloseClick = {},
        )
        Spacer(Modifier.height(12.dp))
        if (true) {
            EmptySearchView()
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(FakeData.noteList) {
                    NoteCard(
                        containerColor = it.containerColor,
                        title = it.title,
                        noteId = it.id,
                        onClick = { id ->
                            navigator?.push(NoteDetailScreen(id))
                        }
                    )
                }
            }
        }
    }
}

