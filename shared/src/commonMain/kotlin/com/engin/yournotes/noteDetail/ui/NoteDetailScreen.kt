package com.engin.yournotes.noteDetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import com.engin.yournotes.SharedResources
import com.engin.yournotes.core.ui.AppUIEvent
import com.engin.yournotes.core.util.AppBarState
import com.engin.yournotes.core.util.BaseScreen
import com.engin.yournotes.core.util.ScreenKeys
import com.engin.yournotes.di.AppModule
import com.engin.yournotes.noteDetail.ui.DetailModes.*
import com.engin.yournotes.noteDetail.ui.component.NoteDescription
import com.engin.yournotes.noteDetail.ui.component.NoteTitle
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.icerock.moko.resources.compose.painterResource
import kotlinx.coroutines.flow.collectLatest


data class NoteDetailScreen(
    val id: Long?,
    override val appModule: AppModule,
) : BaseScreen(appModule) {
    override var onAppBarComposing: ((AppBarState) -> Unit)? = null

    override val key: ScreenKey
        get() = ScreenKeys.NoteDetail

    @Composable
    override fun Content() {
        val viewModel = getViewModel(
            key = ScreenKeys.NoteDetail,
            factory = viewModelFactory {
                NoteDetailViewModel(
                    getNoteByIdUseCase = appModule.getNoteByIdUseCase,
                    deleteNoteByIdUseCase = appModule.deleteNoteByIdUseCase,
                    insertNoteUseCase = appModule.insertNoteUseCase
                )
            })
        val state by viewModel.state.collectAsState()
        val navigator = LocalNavigator.current
        LaunchedEffect(Unit){
            viewModel.uiEvent.collectLatest {
                when(it){
                    is AppUIEvent.Navigate -> {
                        if (it.route == null){
                            navigator?.pop()
                        }
                    }
                    is AppUIEvent.ShowToastMessage -> {}
                }
            }
        }
        ObserveAppBar(
            detailMode = state.detailModes,
            changeMode = { viewModel.onEvent(NoteDetailEvent.ChangeScreenMode(it)) },
            saveNote = { viewModel.onEvent(NoteDetailEvent.OnSaveNote) },
            deleteNote = { viewModel.onEvent(NoteDetailEvent.OnDeleteNote) }
        )
        NoteDetailScreenContent(
            id = id,
            titleText = state.titleText,
            descriptionText = state.descriptionText,
            isEditEnable = state.detailModes != READ,
            idle = { viewModel.onEvent(NoteDetailEvent.Idle(id)) },
            onTitleChange = { viewModel.onEvent(NoteDetailEvent.OnValueChange(it, Fields.Title)) },
            onDescriptionChange = { viewModel.onEvent(NoteDetailEvent.OnValueChange(it, Fields.Description)) }
        )
    }

    @Composable
    private fun ObserveAppBar(
        detailMode: DetailModes,
        changeMode: (DetailModes) -> Unit,
        saveNote: () -> Unit,
        deleteNote: () -> Unit,
    ) {
        LaunchedEffect(detailMode) {
            onAppBarComposing?.invoke(
                AppBarState(
                    title = "",
                    actions = {
                        when(detailMode){
                            ADD -> {
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
                                    IconButton(
                                        modifier = Modifier
                                            .width(24.dp)
                                            .height(24.dp),
                                        onClick = { saveNote() }
                                    ) {
                                        Icon(
                                            painterResource(SharedResources.images.save),
                                            contentDescription = null
                                        )
                                    }
                                }
                            }
                            EDIT -> {
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
                                    IconButton(
                                        modifier = Modifier
                                            .width(24.dp)
                                            .height(24.dp),
                                        onClick = { saveNote() }
                                    ) {
                                        Icon(
                                            painterResource(SharedResources.images.save),
                                            contentDescription = null
                                        )
                                    }
                                }
                                Spacer(Modifier.width(24.dp))
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
                                    IconButton(
                                        modifier = Modifier
                                            .width(24.dp)
                                            .height(24.dp),
                                        onClick = { deleteNote() }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = null
                                        )
                                    }
                                }
                            }
                            READ -> {
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
                                    IconButton(
                                        modifier = Modifier
                                            .width(24.dp)
                                            .height(24.dp),
                                        onClick = { changeMode(EDIT) }
                                    ) {
                                        Icon(
                                            painterResource(SharedResources.images.edit_mode),
                                            contentDescription = null
                                        )
                                    }
                                }
                            }
                        }
                    }
                ))
        }
    }

    @Composable
    private fun NoteDetailScreenContent(
        id: Long?,
        titleText: String,
        descriptionText: String,
        isEditEnable : Boolean = false,
        idle: (Long?) -> Unit,
        onTitleChange: (String) -> Unit,
        onDescriptionChange: (String) -> Unit,
    ) {
        LaunchedEffect(id) {
            idle(id)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            NoteTitle(
                modifier = Modifier.padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 28.dp,
                ),
                value = titleText,
                onValueChanged = onTitleChange,
                editEnable = isEditEnable
            )
            NoteDescription(
                modifier = Modifier.wrapContentHeight()
                    .padding(horizontal = 24.dp),
                value = descriptionText,
                onValueChanged = onDescriptionChange,
                editEnable = isEditEnable
            )
        }
    }
}

