package com.careful.hyperfvm.compose.ui.pages.aboutapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.careful.hyperfvm.compose.LocalAppState
import com.careful.hyperfvm.compose.R
import com.careful.hyperfvm.compose.nevigation3.LocalNavigator
import com.careful.hyperfvm.compose.ui.components.icon.BackNavigationIcon
import com.careful.hyperfvm.compose.ui.theme.getDarkMode
import com.careful.hyperfvm.compose.haze.HazeConfig
import com.careful.hyperfvm.compose.haze.haze
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.ScrollBehavior
import top.yukonga.miuix.kmp.basic.Surface
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.extra.SuperArrow
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.PressFeedbackType
import top.yukonga.miuix.kmp.utils.overScrollVertical
import top.yukonga.miuix.kmp.utils.scrollEndHaptic

@Composable
fun ThanksAppPage() {
    val hazeConfig = haze()
    val scrollBehavior = MiuixScrollBehavior()
    val navigator = LocalNavigator.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                color = Color.Transparent,
                title = stringResource(R.string.title_top_bar_thanks_app),
                scrollBehavior = scrollBehavior,
                modifier = Modifier
                    .hazeEffect(hazeConfig.hazeState) {
                        style = hazeConfig.hazeStyle
                        blurRadius = 25.dp
                        noiseFactor = 0f
                    },
                navigationIcon = {
                    BackNavigationIcon(
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .size(40.dp),
                        onClick = { navigator.pop() },
                    )
                }
            )
        },
    ) { paddingValues ->
        ThanksAppPage(
            paddingValues = paddingValues,
            hazeConfig = hazeConfig,
            scrollBehavior = scrollBehavior,
        )
    }
}

@Composable
fun ThanksAppPage(
    paddingValues: PaddingValues,
    hazeConfig: HazeConfig,
    scrollBehavior: ScrollBehavior,
) {
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
            bottom = paddingValues.calculateBottomPadding() + 12.dp,
        )
    ) {
        item {
            ThanksAppTop()
            ThanksAppList()
        }
    }
}

@Composable
fun ThanksAppTop() {
    val appState = LocalAppState.current
    val colorMode = appState.colorMode
    val darkMode = getDarkMode(colorMode)

    Card(
        modifier = Modifier.padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
        pressFeedbackType = PressFeedbackType.Sink,
        showIndication = true,
        colors = CardDefaults.defaultColors(
            color = when {
                (colorMode in 3..5) -> MiuixTheme.colorScheme.secondaryContainer
                (darkMode) -> Color(0xFF1A3825)
                else -> Color(0xFFDFFAE4)
            }
        )
    ) {
        BasicComponent(
            modifier = Modifier,
            summary = "谨以此App致敬过去几年来我遇到的这些如此无与伦比的作品。"
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Surface(
            Modifier.size(80.dp)
                .clip(RoundedCornerShape(20.dp)),
        ) {
            Image(
                painterResource(id = R.drawable.about_app_thanks_list_image_app_1),
                contentDescription = null
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(80.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(colorResource(R.color.lsposed)),
        ) {
            Image(
                modifier = Modifier.size(64.dp),
                painter = painterResource(R.drawable.app_icon),
                contentDescription = null,
                colorFilter = ColorFilter.tint(
                    color = Color.White,
                    blendMode = BlendMode.SrcIn
                )
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(80.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White),
        ) {
            Image(
                modifier = Modifier.size(48.dp),
                painter = painterResource(R.drawable.about_app_thanks_list_image_app_3),
                contentDescription = null,
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(80.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White),
        ) {
            Image(
                modifier = Modifier.size(48.dp),
                painter = painterResource(R.drawable.about_app_thanks_list_image_app_4),
                contentDescription = null,
            )
        }
    }

    Card(
        modifier = Modifier.padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
        pressFeedbackType = PressFeedbackType.Sink,
        showIndication = true,
        colors = CardDefaults.defaultColors(
            color = when {
                (colorMode in 3..5) -> MiuixTheme.colorScheme.secondaryContainer
                (darkMode) -> Color(0xFF1A3825)
                else -> Color(0xFFDFFAE4)
            }
        )
    ) {
        BasicComponent(
            modifier = Modifier,
            summary = "HyperFVM 在开发过程中使用了这些优秀的开源项目，在此表示衷心感谢\uD83E\uDD70（排名顺序不分先后）"
        )
    }
}

@Composable
fun ThanksAppList() {
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
    ) {
        SuperArrow(
            title = "YuKongA/miuix",
            summary = "一个适用于Compose项目的UI组件库，提供了一系列精仿小米澎湃OS风格的组件",
            onClick = { /* 处理点击事件 */ },
        )
        SuperArrow(
            title = "chrisbanes/haze",
            summary = "一个适用于Compose项目的效果库，可以为顶栏和底栏添加模糊材质",
            onClick = { /* 处理点击事件 */ },
        )
        SuperArrow(
            title = "Kyant0/AndroidLiquidGlass",
            summary = "一个适用于Compose项目的组件库，可以带来Apple风格的具有液态玻璃效果的底栏",
            onClick = { /* 处理点击事件 */ },
        )
        SuperArrow(
            title = "ReChronoRain/HyperCeiler",
            summary = "借鉴了关于页面动态流光效果的实现方式",
            onClick = { /* 处理点击事件 */ },
        )
        SuperArrow(
            title = "YunZiA/HyperStar",
            summary = "借鉴了关于页面动态流光效果的实现方式",
            onClick = { /* 处理点击事件 */ },
        )
    }
}
