package com.careful.hyperfvm.compose.ui.pages.main

import android.os.Build
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.careful.hyperfvm.compose.LocalAppState
import com.careful.hyperfvm.compose.LocalUpdateAppState
import com.careful.hyperfvm.compose.R
import com.careful.hyperfvm.compose.ui.components.settings.ScaleDialog
import com.careful.hyperfvm.compose.ui.theme.KeyColors
import com.careful.hyperfvm.compose.haze.HazeConfig
import com.careful.hyperfvm.compose.haze.haze
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.ScrollBehavior
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.SliderDefaults
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.extra.SuperArrow
import top.yukonga.miuix.kmp.extra.SuperDropdown
import top.yukonga.miuix.kmp.extra.SuperSwitch
import top.yukonga.miuix.kmp.theme.MiuixTheme.colorScheme
import top.yukonga.miuix.kmp.utils.overScrollVertical
import top.yukonga.miuix.kmp.utils.scrollEndHaptic

@Composable
fun SettingsPage(
    outPaddingValues: PaddingValues,
) {
    val hazeConfig = haze()
    val scrollBehavior = MiuixScrollBehavior()

    val appState = LocalAppState.current
    val updateAppState = LocalUpdateAppState.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                color = Color.Transparent,
                title = stringResource(R.string.title_top_bar_settings),
                scrollBehavior = scrollBehavior,
                modifier = Modifier
                    .hazeEffect(hazeConfig.hazeState) {
                        style = hazeConfig.hazeStyle
                        blurRadius = 25.dp
                        noiseFactor = 0f
                    }
            )
        },
    ) { paddingValues ->
        SettingsContent(
            paddingValues = paddingValues,
            outPaddingValues = outPaddingValues,
            hazeConfig = hazeConfig,
            scrollBehavior = scrollBehavior,
            colorMode = appState.colorMode,
            seedIndex = appState.seedIndex,
            enableFloatingBottomBar = appState.enableFloatingBottomBar,
            enableFloatingBottomBarBlur = appState.enableFloatingBottomBarBlur,
            pageScale = appState.pageScale,
            onColorModeChange = { newMode ->
                updateAppState(appState.copy(colorMode = newMode))
            },
            onSeedIndexChange = { newIndex ->
                updateAppState(appState.copy(seedIndex = newIndex))
            },
            onEnableFloatingBottomBarChange = { enabled ->
                updateAppState(appState.copy(enableFloatingBottomBar = enabled))
            },
            onEnableFloatingBottomBarBlurChange = { enabled ->
                updateAppState(appState.copy(enableFloatingBottomBarBlur = enabled))
            },
            onPageScaleChange = { scale ->
                updateAppState(appState.copy(pageScale = scale))
            }
        )
    }
}

@Composable
fun SettingsContent(
    paddingValues: PaddingValues,
    outPaddingValues: PaddingValues,
    hazeConfig: HazeConfig,
    scrollBehavior: ScrollBehavior,
    colorMode: Int,
    seedIndex: Int,
    enableFloatingBottomBar: Boolean,
    enableFloatingBottomBarBlur: Boolean,
    pageScale: Float,
    onColorModeChange: (Int) -> Unit,
    onSeedIndexChange: (Int) -> Unit,
    onEnableFloatingBottomBarChange: (Boolean) -> Unit,
    onEnableFloatingBottomBarBlurChange: (Boolean) -> Unit,
    onPageScaleChange: (Float) -> Unit,
) {
    val colorModeOptions = remember { listOf("Hyper(跟随系统)", "Hyper(浅色)", "Hyper(深色)", "Monet(跟随系统)", "Monet(浅色)", "Monet(深色)") }
    val keyColorOptions = remember { listOf("动态取色") + KeyColors.map { it.first } }
    val showScaleDialog = rememberSaveable { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .scrollEndHaptic()
            .overScrollVertical()
            .fillMaxSize()
            .hazeSource(state = hazeConfig.hazeState) // 给haze效果提供源
            .nestedScroll(scrollBehavior.nestedScrollConnection), // 关联TopAppBar滚动
        overscrollEffect = null,
        contentPadding = PaddingValues(
            top = paddingValues.calculateTopPadding() + 12.dp,
            bottom = outPaddingValues.calculateBottomPadding() + 12.dp,
        )
    ) {
        item {
            SmallTitle("主题")
            Card(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
            ) {
                SuperDropdown(
                    title = "主题",
                    summary = "选择应用的主题模式",
                    items = colorModeOptions,
                    selectedIndex = colorMode,
                    onSelectedIndexChange = onColorModeChange,
                )
                AnimatedVisibility(visible = colorMode in 3..5) {
                    SuperDropdown(
                        title = "强调色",
                        summary = "在使用Monet时自定义种子色",
                        items = keyColorOptions,
                        selectedIndex = seedIndex,
                        onSelectedIndexChange = onSeedIndexChange,
                    )
                }
                SuperSwitch(
                    title = "悬浮底栏",
                    summary = "使用Apple风格的悬浮底栏",
                    checked = enableFloatingBottomBar,
                    onCheckedChange = onEnableFloatingBottomBarChange,
                )
                AnimatedVisibility(visible = enableFloatingBottomBar && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    SuperSwitch(
                        title = "液态玻璃",
                        summary = "启用悬浮底栏的液态玻璃效果",
                        checked = enableFloatingBottomBarBlur,
                        onCheckedChange = onEnableFloatingBottomBarBlurChange,
                    )
                }
                var sliderValue by remember(pageScale) { mutableFloatStateOf(pageScale) }
                SuperArrow(
                    title = "界面缩放",
                    summary = "调整全局显示比例",
                    endActions = {
                        Text(
                            text = "${(sliderValue * 100).toInt()}%",
                            color = colorScheme.onSurfaceVariantActions,
                        )
                    },
                    onClick = { showScaleDialog.value = !showScaleDialog.value },
                    holdDownState = showScaleDialog.value,
                    bottomAction = {
                        Slider(
                            value = sliderValue,
                            onValueChange = { sliderValue = it },
                            onValueChangeFinished = { onPageScaleChange(sliderValue) },
                            valueRange = 0.8f..1.1f,
                            showKeyPoints = true,
                            keyPoints = listOf(0.8f, 0.9f, 1f, 1.1f),
                            magnetThreshold = 0.01f,
                            hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                        )
                    },
                )
                ScaleDialog(
                    showScaleDialog,
                    volumeState = { pageScale },
                    onVolumeChange = onPageScaleChange
                )
            }
        }
    }
}