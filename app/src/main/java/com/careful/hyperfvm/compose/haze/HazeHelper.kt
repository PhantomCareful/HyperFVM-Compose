package com.careful.hyperfvm.compose.haze

import androidx.compose.runtime.Composable
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.rememberHazeState
import top.yukonga.miuix.kmp.theme.MiuixTheme

data class HazeConfig(
    val hazeState: HazeState,
    val hazeStyle: HazeStyle
)

@Composable
fun haze(): HazeConfig {
    val hazeState = rememberHazeState()
    val hazeStyle = HazeStyle(
        backgroundColor = MiuixTheme.colorScheme.surface,
        tint = HazeTint(MiuixTheme.colorScheme.surface.copy(0.4f))
    )

    return HazeConfig(hazeState, hazeStyle)
}