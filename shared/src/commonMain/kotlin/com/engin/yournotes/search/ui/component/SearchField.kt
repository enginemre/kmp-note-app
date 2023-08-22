package com.engin.yournotes.search.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.engin.yournotes.SharedResources
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    searchText : String,
    onValueChange : (String) -> Unit,
    onSearch : (KeyboardActionScope.() -> Unit),
    onCloseClick : () -> Unit
) {
    OutlinedTextField(
        value =searchText ,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 50.dp),
        onValueChange = { onValueChange(it) },

        placeholder = {
            Text(
                text = stringResource(SharedResources.strings.search_hint),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = fontFamilyResource(SharedResources.fonts.Nunito.regular),
                    fontWeight = FontWeight(300),
                    color = Color(0xFFCCCCCC),
                )
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = onSearch
        ),
        textStyle = TextStyle(
            fontSize = 20.sp,
            fontFamily = fontFamilyResource(SharedResources.fonts.Nunito.regular),
            fontWeight = FontWeight(300),
            color = Color(0xFFCCCCCC),
        ),
        shape = RoundedCornerShape(30.dp),
        singleLine = true,
        trailingIcon =  {
            IconButton(onClick = {
                onCloseClick()
            }) {
                Icon(
                    imageVector =  Icons.Default.Close,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xff3B3B3B),
            focusedIndicatorColor = Color.White,
        )
    )

}