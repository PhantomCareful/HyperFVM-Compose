package com.careful.hyperfvm.compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import top.yukonga.miuix.kmp.theme.ColorSchemeMode
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.ThemeController

@Composable
fun AppTheme(
    colorMode: Int = 3,
    keyColor: Color? = null,
    content: @Composable () -> Unit
) {
    // 可用模式: System, Light, Dark, MonetSystem, MonetLight, MonetDark
    val controller = remember(colorMode, keyColor) {
        when(colorMode) {
            0 -> ThemeController(ColorSchemeMode.System)
            1 -> ThemeController(ColorSchemeMode.Light)
            2 -> ThemeController(ColorSchemeMode.Dark)
            4 -> ThemeController(ColorSchemeMode.MonetLight, keyColor = keyColor)
            5 -> ThemeController(ColorSchemeMode.MonetDark, keyColor = keyColor)
            else -> ThemeController(ColorSchemeMode.MonetSystem, keyColor = keyColor)
        }
    }
    return MiuixTheme(
        controller = controller,
        content = content
    )
}

val KeyColors: List<Pair<String, Color>> = listOf(
    "糖葫芦炮弹" to Color(0xFFA42F1F),
    "麦芽糖" to Color(0xFFFFD16E),
    "仙人掌刺身" to Color(0xFF6B8C33),
    "瓜皮护罩" to Color(0xFF509164),
    "处女座精灵" to Color(0xFF3BA5B1),
    "苏打气泡" to Color(0xFF2894CB),
    "水瓶座精灵" to Color(0xFF023F75),
    "三线酒架" to Color(0xFF471D58),
    "樱桃反弹布丁" to Color(0xFFFFDAEB),
)

fun keyColorFor(index: Int): Color? = if (index <= 0) null else KeyColors.getOrNull(index - 1)?.second

@Composable
fun getDarkMode(colorMode: Int): Boolean {
    val darkMode = when (colorMode) {
        2, 5 -> true
        0, 3 -> isSystemInDarkTheme()
        else -> false
    }
    return darkMode
}