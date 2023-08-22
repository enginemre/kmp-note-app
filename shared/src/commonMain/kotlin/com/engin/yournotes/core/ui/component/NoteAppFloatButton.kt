package com.engin.yournotes.core.ui.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.LocalNavigator
import com.engin.yournotes.SharedResources
import com.engin.yournotes.di.AppModule
import com.engin.yournotes.noteDetail.ui.NoteDetailScreen
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun NoteAppFloatButton(
    onFloatClick : () -> Unit
) {
    FloatingActionButton(
        onClick = {
            onFloatClick()
        },
        shape = CircleShape,
        containerColor = Color(0xff252525),
        contentColor = Color.White
    ) {
        Icon(
            painter = painterResource(SharedResources.images.add),
            contentDescription = null
        )
    }
}