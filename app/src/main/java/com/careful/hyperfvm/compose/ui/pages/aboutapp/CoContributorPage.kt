package com.careful.hyperfvm.compose.ui.pages.aboutapp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.careful.hyperfvm.compose.LocalAppState
import com.careful.hyperfvm.compose.R
import com.careful.hyperfvm.compose.nevigation3.LocalNavigator
import com.careful.hyperfvm.compose.ui.components.icon.BackNavigationIcon
import com.careful.hyperfvm.compose.ui.theme.getDarkMode
import com.careful.hyperfvm.compose.blur.haze.HazeConfig
import com.careful.hyperfvm.compose.blur.haze.haze
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.ScrollBehavior
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.PressFeedbackType
import top.yukonga.miuix.kmp.utils.overScrollVertical
import top.yukonga.miuix.kmp.utils.scrollEndHaptic

@Composable
fun CoContributorPage() {
    val hazeConfig = haze()
    val scrollBehavior = MiuixScrollBehavior()
    val navigator = LocalNavigator.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                color = Color.Transparent,
                title = stringResource(R.string.title_top_bar_co_contributor),
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
        CoContributorContent(
            paddingValues = paddingValues,
            hazeConfig = hazeConfig,
            scrollBehavior = scrollBehavior,
        )
    }
}

@Composable
fun CoContributorContent(
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
            CoContributorTop()
            CoContributorList()
            CoContributorTopOld()
            CoContributorListOld()
        }
    }
}

@Composable
fun CoContributorTop() {
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
            summary = "感谢以下同学为 HyperFVM 的开发提供了不同程度的帮助\uD83E\uDD70（排名顺序不分先后）"
        )
    }
}

@Composable
fun CoContributorList() {
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
    ) {
        BasicComponent(
            modifier = Modifier,
            title = "4399 2-6 枋黑",
            summary = "为防御卡数据详情页提供排版思路\n为防御卡增益效果计算提供设计思路",
            onClick = {  }
        )
        BasicComponent(
            modifier = Modifier,
            title = "QQ空间 1-8 darg1",
            summary = "帮助修复部分低质大图",
            onClick = {  }
        )
        BasicComponent(
            modifier = Modifier,
            title = "QQ空间 1-8 司康饼的午后",
            summary = "为防御卡数据详情页提供排版思路\n帮助修复部分低质大图",
            onClick = {  }
        )
    }
}

@Composable
fun CoContributorTopOld() {
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
            summary = "这是 HyperFVM Java版的共建团，旧版虽然已经归档，但他们曾经提供的帮助仍然值得被记住\uD83E\uDD70（排名顺序不分先后）"
        )
    }
}

@Composable
fun CoContributorListOld() {
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
    ) {
        BasicComponent(
            modifier = Modifier,
            title = "QQ空间 1-8 飛飛",
            summary = "感谢提出在主界面添加防御卡数据查询按钮的建议",
            onClick = {  }
        )
        BasicComponent(
            modifier = Modifier,
            title = "QQ空间 1-8 HS灬缄默",
            summary = "感谢提出在图鉴防御卡数据中展示超清大图的建议",
            onClick = {  }
        )
        BasicComponent(
            modifier = Modifier,
            title = "4399 2-6 早睡日氪一元",
            summary = "感谢提出防御卡数据整合的建议\n感谢提出相关防御卡跳转查询的建议\n感谢帮助检查防御卡全能数据库中的错误",
            onClick = {  }
        )
        BasicComponent(
            modifier = Modifier,
            title = "哔哩哔哩 失眠小羊-ovo",
            summary = "感谢提出图片导出功能的建议",
            onClick = {  }
        )
    }
}
