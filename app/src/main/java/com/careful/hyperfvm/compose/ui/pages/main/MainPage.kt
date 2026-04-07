package com.careful.hyperfvm.compose.ui.pages.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.careful.hyperfvm.compose.LocalAppState
import com.careful.hyperfvm.compose.blur.haze.HazeConfig
import com.careful.hyperfvm.compose.blur.haze.haze
import com.careful.hyperfvm.compose.ui.components.bottombar.BottomBar
import com.careful.hyperfvm.compose.ui.components.bottombar.MainPagerState
import com.careful.hyperfvm.compose.ui.components.bottombar.rememberMainPagerState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import com.kyant.backdrop.backdrops.layerBackdrop as kyant_layerBackdrop
import com.kyant.backdrop.backdrops.LayerBackdrop as kyantLayerBackdrop
import com.kyant.backdrop.backdrops.rememberLayerBackdrop as kyantRememberLayerBackdrop
import top.yukonga.miuix.kmp.basic.Scaffold

val LocalMainPagerState = staticCompositionLocalOf<MainPagerState> { error("LocalMainPagerState not provided") }

@Composable
fun MainPage(
) {
    val hazeConfig = haze()
    val appState = LocalAppState.current

    val enableFloatingBottomBar = appState.enableFloatingBottomBar
    val enableFloatingBottomBarBlur = appState.enableFloatingBottomBarBlur

    // 初始化PagerState，绑定总页数
    val pagerState = rememberPagerState(pageCount = { UIConstants.PAGE_COUNT })
    val mainPagerState = rememberMainPagerState(pagerState)

    // 创建 Backdrop 实例（用于悬浮底栏的背景）
    val kyantBackdrop = kyantRememberLayerBackdrop {
        drawContent()
    }

    LaunchedEffect(mainPagerState.pagerState.currentPage) {
        mainPagerState.syncPage()
    }

    // 提供状态给子组件
    CompositionLocalProvider(
        LocalMainPagerState provides mainPagerState,
    ) {
        // 子组件（NavigationBar、AppPager等）可直接获取上述状态
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .hazeEffect(hazeConfig.hazeState) {
                            style = hazeConfig.hazeStyle
                            blurRadius = 25.dp
                            noiseFactor = 0f
                        }
                ) {
                    BottomBar(
                        backdrop = kyantBackdrop,
                        modifier = Modifier.align(Alignment.BottomCenter),
                    )
                }
            },
        ) { paddingValues ->
            AppPager(
                hazeConfig,
                pagerState,
                paddingValues,
                kyantBackdrop = kyantBackdrop,
                enableFloatingBottomBar = enableFloatingBottomBar,
                enableFloatingBottomBarBlur = enableFloatingBottomBarBlur,
            )
        }
    }
}

private object UIConstants {
    const val DATA_CENTER = 0
    const val CARD_DATA_INDEX = 1
    const val SETTINGS = 2
    const val ABOUT_APP = 3
    const val PAGE_COUNT = 4
}

@Composable
fun AppPager(
    hazeConfig: HazeConfig,
    pagerState: PagerState,
    paddingValues: PaddingValues,
    kyantBackdrop: kyantLayerBackdrop,
    enableFloatingBottomBar: Boolean,
    enableFloatingBottomBarBlur: Boolean,
) {
    HorizontalPager(
        state = pagerState,
        userScrollEnabled = false,
        modifier = Modifier
            .fillMaxSize()
            .then(
                if (enableFloatingBottomBar && enableFloatingBottomBarBlur) {
                    Modifier.kyant_layerBackdrop(kyantBackdrop)
                } else if (!enableFloatingBottomBar) {
                    Modifier.hazeSource(state = hazeConfig.hazeState) // 给haze效果提供源
                } else {
                    Modifier
                }
            )
    ) { pageIndex ->
        // 根据页面索引渲染不同页面
        when (pageIndex) {
            UIConstants.DATA_CENTER -> DataCenterPage(
                paddingValues,
            )

            UIConstants.CARD_DATA_INDEX -> CardDataIndexPage(
                paddingValues,
            )

            UIConstants.SETTINGS -> SettingsPage(
                paddingValues,
            )

            UIConstants.ABOUT_APP -> AboutAppPage(
                paddingValues,
            )
        }
    }
}