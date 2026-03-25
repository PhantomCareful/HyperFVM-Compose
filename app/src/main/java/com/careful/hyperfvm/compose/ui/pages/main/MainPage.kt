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
import com.careful.hyperfvm.compose.LocalAppState
import com.careful.hyperfvm.compose.ui.components.bottombar.BottomBar
import com.careful.hyperfvm.compose.ui.components.bottombar.MainPagerState
import com.careful.hyperfvm.compose.ui.components.bottombar.rememberMainPagerState
import com.careful.hyperfvm.compose.haze.HazeConfig
import com.careful.hyperfvm.compose.haze.haze
import com.kyant.backdrop.Backdrop
import com.kyant.backdrop.backdrops.LayerBackdrop
import com.kyant.backdrop.backdrops.layerBackdrop
import com.kyant.backdrop.backdrops.rememberLayerBackdrop
import dev.chrisbanes.haze.hazeSource
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
    val backdrop = rememberLayerBackdrop {
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
                    modifier = Modifier.fillMaxWidth()
                ) {
                    BottomBar(
                        hazeState = hazeConfig.hazeState,
                        hazeStyle = hazeConfig.hazeStyle,
                        backdrop = backdrop,
                        modifier = Modifier.align(Alignment.BottomCenter),
                    )
                }
            },
        ) { paddingValues ->
            AppPager(
                hazeConfig,
                pagerState,
                paddingValues,
                backdrop = backdrop,
                enableLayerBackdrop = enableFloatingBottomBar && enableFloatingBottomBarBlur,
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
    backdrop: Backdrop,
    enableLayerBackdrop: Boolean,
) {
    HorizontalPager(
        state = pagerState,
        userScrollEnabled = false,
        modifier = Modifier
            .fillMaxSize()
            .hazeSource(state = hazeConfig.hazeState) // 给haze效果提供源
            .then(
                if (enableLayerBackdrop) {
                    Modifier.layerBackdrop(backdrop as LayerBackdrop)  // 应用 backdrop 捕获
                } else Modifier
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