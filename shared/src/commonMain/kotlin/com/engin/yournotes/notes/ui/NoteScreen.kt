package com.engin.yournotes.notes.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import com.engin.yournotes.SharedResources
import com.engin.yournotes.core.util.AppBarState
import com.engin.yournotes.core.util.BaseScreen
import com.engin.yournotes.core.util.ScreenKeys
import com.engin.yournotes.noteDetail.ui.NoteDetailScreen
import com.engin.yournotes.notes.data.repository.FakeData
import com.engin.yournotes.notes.ui.component.EmptyNotesView
import com.engin.yournotes.notes.ui.component.NoteCard
import com.engin.yournotes.search.ui.SearchScreen
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

class NoteScreen : BaseScreen() {
    override var onAppBarComposing: ((AppBarState) -> Unit)? = null

    override val key: ScreenKey
        get() = ScreenKeys.Notes

    @Composable
    override fun Content() {
        ObserveAppBar()
        NoteScreenContent()
    }

    @Composable
    private fun ObserveAppBar() {
        val navigator = LocalNavigator.current
        val title = stringResource(SharedResources.strings.notes)
        LaunchedEffect(true) {
            onAppBarComposing?.invoke(
                AppBarState(
                    title,
                    actions = {
                        NoteAppBar(onSearchActionClick = {
                            navigator?.push(SearchScreen())
                        })
                    }
                ))
        }
    }


    @Composable
    private fun NoteAppBar(
        onSearchActionClick: () -> Unit,
    ) {
        Column(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .background(
                    color = Color(0xFF3B3B3B),
                    shape = RoundedCornerShape(size = 15.dp)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
                    .clickable {
                        onSearchActionClick()
                    },
                painter = painterResource(SharedResources.images.search),
                contentDescription = "image description",
                contentScale = ContentScale.None,
            )
        }
    }

    @Composable
    private fun NoteScreenContent() {
        val navigator = LocalNavigator.current
        // TODO check note List size
        if (false) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize(),
                state = rememberLazyListState(),
                contentPadding = PaddingValues(
                    start = 24.dp,
                    end = 24.dp,
                    top = 24.dp,
                    bottom = 12.dp
                ),
            ) {
                items(FakeData.noteList, key = { it.id }) {
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
        } else {
            EmptyNotesView()
        }
    }
}

