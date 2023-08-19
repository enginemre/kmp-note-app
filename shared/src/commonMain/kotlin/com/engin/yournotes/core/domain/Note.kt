package com.engin.yournotes.core.domain

import androidx.compose.ui.graphics.Color
import kotlinx.datetime.LocalDateTime

data class Note(
    val id : Long,
    var title : String,
    var detail : String,
    val containerColor : Long,
    val created: LocalDateTime
) {
    companion object {
        private const val RedOrangeHex = 0xffffab91
        private const val RedPinkHex = 0xfff48fb1
        private const val BabyBlueHex = 0xff81deea
        private const val VioletHex = 0xffcf94da
        private const val LightGreenHex = 0xffe7ed9b
        private val colors = listOf(RedOrangeHex, RedPinkHex, LightGreenHex, BabyBlueHex, VioletHex)

        fun generateRandomColor() = colors.random()
    }
}

