package com.engin.yournotes.noteDetail.ui.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.engin.yournotes.SharedResources
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteTitle(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit,
    editEnable: Boolean = false,
) {
    TextField(
        value,
        onValueChanged,
        modifier,
        enabled = editEnable,
        textStyle = TextStyle(
            fontSize = 35.sp,
            fontFamily = fontFamilyResource(SharedResources.fonts.Nunito.regular),
            fontWeight = FontWeight(400),
            color = Color.White,
        ),
        placeholder = {
            Text(
                text = stringResource(SharedResources.strings.title),
                style = TextStyle(
                    fontSize = 35.sp,
                    fontFamily = fontFamilyResource(SharedResources.fonts.Nunito.regular),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF9A9A9A)
                )
            )
        },
        shape = TextFieldDefaults.filledShape,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}