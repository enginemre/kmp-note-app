package com.engin.yournotes.search.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import com.engin.yournotes.SharedResources
import com.engin.yournotes.core.ui.component.NoteAppBar
import com.engin.yournotes.core.util.AppBarState
import com.engin.yournotes.core.util.BaseScreen
import com.engin.yournotes.core.util.ScreenKeys
import com.engin.yournotes.di.AppModule
import com.engin.yournotes.noteDetail.ui.NoteDetailScreen
import com.engin.yournotes.notes.ui.component.NoteCard
import com.engin.yournotes.search.SearchEvent
import com.engin.yournotes.search.ui.component.EmptySearchView
import com.engin.yournotes.search.ui.component.SearchField
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.icerock.moko.resources.compose.stringResource


class SearchScreen(override val appModule: AppModule) : BaseScreen(appModule) {
    override var onAppBarComposing: ((AppBarState) -> Unit)? = null

    override val key: ScreenKey
        get() = ScreenKeys.Search

    @Composable
    override fun Content() {
        SearchScreenContent()
    }

    @Composable
    private fun ObserveAppBar() {
        val navigator = LocalNavigator.current
        val title = stringResource(SharedResources.strings.search)
        LaunchedEffect(true) {
            onAppBarComposing?.invoke(
                AppBarState(
                    title,
                ))
        }
    }

    @Composable
    private fun SearchScreenContent() {
        val navigator = LocalNavigator.current
        val viewModel = getViewModel(
            key = ScreenKeys.Search,
            factory = viewModelFactory { SearchViewModel(appModule.getAllNoteUseCase) }
        )
        val state by viewModel.state.collectAsState()
        Column {
            SearchField(
                modifier = Modifier.padding(horizontal = 12.dp),
                searchText = state.searchText,
                onValueChange = { viewModel.onEvent(SearchEvent.OnValueChange(it)) },
                onSearch = { viewModel.onEvent(SearchEvent.Search(state.searchText)) },
                onCloseClick = { navigator?.pop() },
            )
            if (viewModel.searchList.isEmpty()) {
                EmptySearchView()
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    state = rememberLazyListState(),
                    contentPadding = PaddingValues(
                        start = 24.dp,
                        end = 24.dp,
                        top = 24.dp,
                        bottom = 12.dp
                    ),
                ) {
                    items(viewModel.searchList) {
                        NoteCard(
                            containerColor = it.containerColor,
                            title = it.title,
                            noteId = it.id,
                            onClick = { id ->
                                navigator?.push(
                                    NoteDetailScreen(id, appModule)
                                )
                            }
                        )
                    }
                }
            }
        }

    }
}

