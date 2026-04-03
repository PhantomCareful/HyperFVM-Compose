package com.careful.hyperfvm.compose.ui.pages.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.careful.hyperfvm.compose.LocalAppState
import com.careful.hyperfvm.compose.R
import com.careful.hyperfvm.compose.ui.theme.getDarkMode
import com.careful.hyperfvm.compose.blur.haze.haze
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.PressFeedbackType
import top.yukonga.miuix.kmp.utils.overScrollVertical
import top.yukonga.miuix.kmp.utils.scrollEndHaptic

@Composable
fun DataCenterPage(
    outPaddingValues: PaddingValues,
) {
    val appState = LocalAppState.current
    val colorMode = appState.colorMode
    val darkMode = getDarkMode(colorMode)

    val hazeConfig = haze()
    val scrollBehavior = MiuixScrollBehavior()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .hazeEffect(hazeConfig.hazeState) {
                        style = hazeConfig.hazeStyle
                        blurRadius = 25.dp
                        noiseFactor = 0f
                    },
                color = Color.Transparent,
                title = stringResource(R.string.title_top_bar_data_center),
                scrollBehavior = scrollBehavior,
            )
        },
    ) { paddingValues ->
        LazyColumn (
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
                Card(
                    modifier = Modifier.padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                    pressFeedbackType = PressFeedbackType.Tilt,
                    showIndication = true,
                    colors = CardDefaults.defaultColors(
                        color = when {
                            (colorMode in 3..5) -> MiuixTheme.colorScheme.secondaryContainer
                            (darkMode) -> Color(0xFF1A3825)
                            else -> Color(0xFFDFFAE4)
                        }
                    ),
                    onLongPress = {  }
                ) {
                    BasicComponent(
                        modifier = Modifier,
                        title = "这是 HyperFVM Beta版",
                        summary = "功能尚不完全，仅供测试使用。如遇任何问题，请向及时开发者反馈",
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(top = 75.dp)
                        .fillMaxSize()
                        .size(300.dp),
                ) {
                    Image(
                        painterResource(id = R.drawable.unhappy),
                        contentDescription = null
                    )
                }
            }
        }
    }
}