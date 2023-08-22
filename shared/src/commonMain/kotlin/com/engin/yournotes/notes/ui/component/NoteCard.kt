package com.engin.yournotes.notes.ui.component

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.engin.yournotes.SharedResources
import dev.icerock.moko.resources.compose.fontFamilyResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    containerColor: Long,
    title : String,
    noteId: Long?,
    onClick: (Long?) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        shape = RoundedCornerShape(10.dp),
        colors =  CardDefaults.cardColors(
            containerColor = Color(containerColor),
            contentColor = Color.Black,
        ),
        onClick = {
            onClick(noteId)
        },
    ){
        Text(
            modifier = Modifier.padding(24.dp),
            text = title,
            style = TextStyle(
                fontSize = 25.sp,
                fontFamily = fontFamilyResource(SharedResources.fonts.Nunito.regular),
                fontWeight = FontWeight(400),
                color = Color(0xFF000000),
            ),
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
        )
    }
}