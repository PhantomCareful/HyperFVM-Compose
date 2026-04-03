package com.careful.hyperfvm.compose.ui.pages.aboutapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.PressFeedbackType
import top.yukonga.miuix.kmp.utils.overScrollVertical
import top.yukonga.miuix.kmp.utils.scrollEndHaptic

@Composable
fun ThanksGamePage() {
    val hazeConfig = haze()
    val scrollBehavior = MiuixScrollBehavior()
    val navigator = LocalNavigator.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                color = Color.Transparent,
                title = stringResource(R.string.title_top_bar_thanks_game),
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
        ThanksGamePage(
            paddingValues = paddingValues,
            hazeConfig = hazeConfig,
            scrollBehavior = scrollBehavior,
        )
    }
}

@Composable
fun ThanksGamePage(
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
            ThanksGameTop()
            ThanksGameList()
        }
    }
}

@Composable
fun ThanksGameTop() {
    val appState = LocalAppState.current
    val colorMode = appState.colorMode
    val darkMode = getDarkMode(colorMode)

    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp)
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
            summary = "HyperFVM 在开发过程中使用了这些老师们的作品，在此表示衷心感谢\uD83E\uDD70（排名顺序不分先后）"
        )
    }
}

@Composable
fun ThanksGameList() {
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
    ) {
        ArrowPreference(
            title = "So陌路の",
            summary = "综合数据表作者",
            startAction = {
                Surface(
                    Modifier.size(50.dp),
                    CircleShape
                ) {
                    Image(
                        painterResource(id = R.drawable.about_app_thanks_list_image_fvm_1),
                        contentDescription = null
                    )
                }
            },
            onClick = { /* 处理点击事件 */ },
        )
        ArrowPreference(
            title = "夏夜浅酌",
            summary = "提拉米鼠作者、美食数据站作者",
            startAction = {
                Surface(
                    Modifier.size(50.dp),
                    CircleShape
                ) {
                    Image(
                        painterResource(id = R.drawable.about_app_thanks_list_image_fvm_2),
                        contentDescription = null
                    )
                }
            },
            onClick = { /* 处理点击事件 */ },
        )
        ArrowPreference(
            title = "右撇三叶",
            summary = "部分高清图素材作者",
            startAction = {
                Surface(
                    Modifier.size(50.dp),
                    CircleShape
                ) {
                    Image(
                        painterResource(id = R.drawable.about_app_thanks_list_image_fvm_3),
                        contentDescription = null
                    )
                }
            },
            onClick = { /* 处理点击事件 */ },
        )
        ArrowPreference(
            title = "就喝百事",
            summary = "查黑系统作者",
            startAction = {
                Surface(
                    Modifier.size(50.dp),
                    CircleShape
                ) {
                    Image(
                        painterResource(id = R.drawable.about_app_thanks_list_image_fvm_4),
                        contentDescription = null
                    )
                }
            },
            onClick = { /* 处理点击事件 */ },
        )
        BasicComponent(
            title = "哪片花火",
            summary = "部分数据图作者",
            startAction = {
                Surface(
                    Modifier.size(50.dp),
                    CircleShape
                ) {
                    Image(
                        painterResource(id = R.drawable.about_app_thanks_list_image_fvm_5),
                        contentDescription = null
                    )
                }
            },
            onClick = { /* 处理点击事件 */ },
        )
    }
}
