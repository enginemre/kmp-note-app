package com.engin.yournotes.search.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.engin.yournotes.SharedResources
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun EmptySearchView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(
            modifier = Modifier.wrapContentSize(),
            painter = painterResource(SharedResources.images.search_empty),
            contentDescription = null,
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(SharedResources.strings.empty_search_text),
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = fontFamilyResource(SharedResources.fonts.Nunito.regular),
                fontWeight = FontWeight(300),
                color = Color.White,
            )
        )
    }
}