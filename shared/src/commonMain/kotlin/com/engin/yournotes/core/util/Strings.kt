package com.engin.yournotes.core.util

import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.StringResource

expect class Strings {
    fun get(id : StringResource , args : List<Any>) : String
}