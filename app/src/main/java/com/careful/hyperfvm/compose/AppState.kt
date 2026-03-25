package com.careful.hyperfvm.compose

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf

@Stable
data class AppState(
    // 主题
    val colorMode: Int = 0,
    val seedIndex: Int = 0,

    // 悬浮底栏
    val enableFloatingBottomBar: Boolean = false,
    val enableFloatingBottomBarBlur: Boolean = false,

    // 界面缩放
    val pageScale: Float = 1f,

)

val LocalAppState = compositionLocalOf<AppState> {
    error("No AppState provided!")
}

val LocalUpdateAppState = staticCompositionLocalOf<(AppState) -> AppState> {
    error("No AppState updater provided!")
}