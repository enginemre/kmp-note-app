package com.engin.yournotes.noteDetail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import com.engin.yournotes.SharedResources
import com.engin.yournotes.core.util.AppBarState
import com.engin.yournotes.core.util.BaseScreen
import com.engin.yournotes.core.util.ScreenKeys
import com.engin.yournotes.noteDetail.ui.component.NoteDescription
import com.engin.yournotes.noteDetail.ui.component.NoteTitle
import dev.icerock.moko.resources.compose.painterResource


data class NoteDetailScreen(
    val id: Int,
) : BaseScreen() {
    override var onAppBarComposing: ((AppBarState) -> Unit)? = null

    override val key: ScreenKey
        get() = ScreenKeys.NoteDetail

    @Composable
    override fun Content() {
        ObserveAppBar()
        NoteDetailScreenContent(id)
    }

    @Composable
    private fun ObserveAppBar() {
        var isEditMode by mutableStateOf(false)
        LaunchedEffect(isEditMode) {
            onAppBarComposing?.invoke(
                AppBarState(
                    title = "",
                    actions = {
                        if (!isEditMode) {
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
                                            isEditMode = true
                                        },
                                    painter = painterResource(SharedResources.images.edit_mode),
                                    contentDescription = "image description",
                                    contentScale = ContentScale.None,
                                )
                            }
                        }else{
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
                                    onClick = {}
                                ){
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
                                    onClick = {}
                                ){
                                    Icon(
                                        imageVector = Icons.Default.Delete,
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
                                    onClick = {}
                                ){
                                    Icon(
                                        painterResource(SharedResources.images.visibility),
                                        contentDescription = null
                                    )
                                }
                            }
                        }

                    }
                ))
        }
    }

    @Composable
    private fun NoteDetailScreenContent(id: Int) {
        var fieldValueTitle by remember{ mutableStateOf("")}
        var fieldValueDescription by remember{ mutableStateOf("")}
        var isEditMode by remember { mutableStateOf(true) }
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
                value = fieldValueTitle,
                onValueChanged = {fieldValueTitle = it},
                readOnly = true
            )
            NoteDescription(
                modifier = Modifier.wrapContentHeight()
                    .padding(horizontal = 24.dp, vertical = 24.dp),
                value = fieldValueDescription,
                onValueChanged = {fieldValueDescription = it},
                readOnly = true
            )
        }
    }
}

