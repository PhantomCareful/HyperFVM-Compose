// App.kt
package com.careful.hyperfvm.compose

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.core.content.edit
import com.careful.hyperfvm.compose.nevigation3.AppNavigation
import com.careful.hyperfvm.compose.ui.theme.AppTheme
import com.careful.hyperfvm.compose.ui.theme.keyColorFor
import kotlinx.coroutines.flow.drop

@Composable
fun App(
    onColorModeChange: ((Int) -> Unit)? = null,
) {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("app_state", Context.MODE_PRIVATE)

    // 从 SharedPreferences 加载初始状态
    val initialAppState = remember {
        AppState(
            // 主题
            colorMode = prefs.getInt("color_mode", 0),
            seedIndex = prefs.getInt("seed_index", 0),
            // 悬浮底栏
            enableFloatingBottomBar = prefs.getBoolean("enable_floating_bottom_bar", false),
            enableFloatingBottomBarBlur = prefs.getBoolean(
                "enable_floating_bottom_bar_blur",
                false
            ),
            // 界面缩放比例
            pageScale = prefs.getFloat("page_scale", 1f),
        )
    }

    var appState by remember { mutableStateOf(initialAppState) }

    // 更新函数：修改状态并持久化
    val updateAppState: (AppState) -> AppState = remember {
        { newState ->
            // 写入 SharedPreferences
            prefs.edit {
                putInt("color_mode", newState.colorMode)
                putInt("seed_index", newState.seedIndex)
                putBoolean("enable_floating_bottom_bar", newState.enableFloatingBottomBar)
                putBoolean("enable_floating_bottom_bar_blur", newState.enableFloatingBottomBarBlur)
                putFloat("page_scale", newState.pageScale)
            }
            // 更新 Compose 状态
            appState = newState
            newState
        }
    }

    // 根据 pageScale 创建新的密度
    val originalDensity = LocalDensity.current
    val scaledDensity = remember(originalDensity, appState.pageScale) {
        Density(
            density = originalDensity.density * appState.pageScale,
            fontScale = originalDensity.fontScale * appState.pageScale
        )
    }

    val currentOnColorModeChange by rememberUpdatedState(onColorModeChange)
    LaunchedEffect(Unit) {
        snapshotFlow { appState.colorMode }
            .drop(1)
            .collect { currentOnColorModeChange?.invoke(it) }
    }
    val keyColor = keyColorFor(appState.seedIndex)

    AppTheme(
        colorMode = appState.colorMode,
        keyColor = keyColor,
    ) {
        CompositionLocalProvider(
            LocalDensity provides scaledDensity,          // 提供缩放后的密度
            LocalAppState provides appState,              // 提供全局状态
            LocalUpdateAppState provides updateAppState   // 提供更新函数
        ) {
            AppNavigation(appState.colorMode)
        }
    }
}