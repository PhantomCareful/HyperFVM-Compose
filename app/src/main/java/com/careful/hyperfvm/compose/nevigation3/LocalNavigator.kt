package com.careful.hyperfvm.compose.nevigation3

import androidx.compose.runtime.staticCompositionLocalOf

val LocalNavigator = staticCompositionLocalOf<Navigator> { error("No Navigator provided") }