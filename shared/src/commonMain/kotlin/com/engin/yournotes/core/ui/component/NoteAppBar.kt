package com.engin.yournotes.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.engin.yournotes.SharedResources
import com.engin.yournotes.core.util.ScreenKeys
import com.engin.yournotes.search.ui.SearchScreen
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteAppBar(
    modifier: Modifier = Modifier.fillMaxWidth(),
    key: String,
    title : String,
    actions :  (@Composable RowScope.() -> Unit)?
) {
    TopAppBar(
        modifier = modifier,
        title = { generateTitle(key,title) },
        navigationIcon = { generateNavigationIcon(key) },
        actions = { generateActions(key,actions) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color(0xff252525),
            titleContentColor = Color.White,
            actionIconContentColor = Color.White,
            navigationIconContentColor = Color.White
        )
    )
}

@Composable
fun generateActions(key: String,actions: (@Composable RowScope.() -> Unit)?) {
    when (key) {
        ScreenKeys.NoteDetail -> {
            Row(modifier = Modifier.padding(end = 12.dp)){
                actions?.invoke(this)
               /* Column(
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

                            },
                        painter = painterResource(SharedResources.images.edit_mode),
                        contentDescription = "image description",
                        contentScale = ContentScale.None,
                    )
                }*/
            }
        }
        ScreenKeys.Notes -> {
            val navigator  = LocalNavigator.current
            Row(modifier = Modifier.padding(end = 12.dp)){
                actions?.invoke(this)
               /* Column(
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
                               navigator?.push(SearchScreen())
                            },
                        painter = painterResource(SharedResources.images.search),
                        contentDescription = "image description",
                        contentScale = ContentScale.None,
                    )
                }*/
            }
        }
        else -> {}
    }
}

@Composable
fun generateNavigationIcon(key: String) {
    when (key) {
        ScreenKeys.NoteDetail -> {
            val navigator = LocalNavigator.current
            Image(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .width(48.dp)
                    .height(48.dp)
                    .background(
                        color = Color(0xFF3B3B3B),
                        shape = RoundedCornerShape(size = 15.dp)
                    )
                    .clickable {
                        navigator?.pop()
                    },
                painter = painterResource(SharedResources.images.left_arrow),
                contentDescription = "ses",
                contentScale = ContentScale.None,
            )
        }
        else -> {}
    }
}

@Composable
fun generateTitle(key: String,title: String) {
    when (key) {
        ScreenKeys.Notes -> {
            val text = stringResource(SharedResources.strings.notes)
            Text(
                text = text, style = TextStyle(
                    fontSize = 43.sp,
                    fontFamily = fontFamilyResource(SharedResources.fonts.Nunito.regular),
                    fontWeight = FontWeight(600),
                    color = Color.White,
                )
            )
        }
        else -> {}
    }
}