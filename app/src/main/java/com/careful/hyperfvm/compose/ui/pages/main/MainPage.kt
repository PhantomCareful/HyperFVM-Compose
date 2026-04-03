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
import com.careful.hyperfvm.compose.blur.blurConfig
import com.careful.hyperfvm.compose.ui.components.bottombar.BottomBar
import com.careful.hyperfvm.compose.ui.components.bottombar.MainPagerState
import com.careful.hyperfvm.compose.ui.components.bottombar.rememberMainPagerState
import com.kyant.backdrop.backdrops.layerBackdrop as kyant_layerBackdrop
import com.kyant.backdrop.backdrops.LayerBackdrop as kyantLayerBackdrop
import com.kyant.backdrop.backdrops.rememberLayerBackdrop as kyantRememberLayerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop as miuixRememberLayerBackdrop
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.blur.textureBlur
import top.yukonga.miuix.kmp.theme.miuixShape
import top.yukonga.miuix.kmp.blur.LayerBackdrop as miuixLayerBackdrop
import top.yukonga.miuix.kmp.blur.layerBackdrop as miuix_layerBackdrop

val LocalMainPagerState = staticCompositionLocalOf<MainPagerState> { error("LocalMainPagerState not provided") }

@Composable
fun MainPage(
) {

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

    val miuixBackdrop = miuixRememberLayerBackdrop()

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
                        .then(
                            if (!enableFloatingBottomBar) {
                                Modifier.textureBlur(
                                    backdrop = miuixBackdrop,
                                    shape = miuixShape(0.dp),
                                    colors = blurConfig().miuixBlurColors
                                )
                            } else {
                                Modifier
                            }
                        )
                ) {
                    BottomBar(
                        backdrop = kyantBackdrop,
                        modifier = Modifier.align(Alignment.BottomCenter),
                    )
                }
            },
        ) { paddingValues ->
            AppPager(
                pagerState,
                paddingValues,
                kyantBackdrop = kyantBackdrop,
                miuixBackdrop = miuixBackdrop,
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
    pagerState: PagerState,
    paddingValues: PaddingValues,
    kyantBackdrop: kyantLayerBackdrop,
    miuixBackdrop: miuixLayerBackdrop,
    enableLayerBackdrop: Boolean,
) {
    if (enableLayerBackdrop) {
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            modifier = Modifier
                .fillMaxSize()
                .kyant_layerBackdrop(kyantBackdrop)  // 应用液态玻璃底栏的 backdrop 捕获
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
    } else {
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            modifier = Modifier
                .fillMaxSize()
                .miuix_layerBackdrop(miuixBackdrop)
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
}