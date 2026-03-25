package com.careful.hyperfvm.compose.nevigation3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.NavDisplayTransitionEffects
import com.careful.hyperfvm.compose.ui.pages.aboutapp.CoContributorPage
import com.careful.hyperfvm.compose.ui.pages.aboutapp.ThanksAppPage
import com.careful.hyperfvm.compose.ui.pages.aboutapp.ThanksGamePage
import com.careful.hyperfvm.compose.ui.pages.main.MainPage

@Composable
fun AppNavigation(
    colorMode: Int,
) {
    val navigationViewModel: NavigationViewModel = viewModel()
    // 创建回退栈，初始包含首页
    val backStack = navigationViewModel.backStack
    val navigator = remember(backStack) { Navigator(backStack) }

    // 定义每个路由对应的可组合项
    val entryProvider = remember(backStack, colorMode) {
        entryProvider<NavKey> {
            entry<Route.MainPage> {
                MainPage()
            }
            entry<Route.CoContributorPage> {
                CoContributorPage()
            }
            entry<Route.ThanksGamePage> {
                ThanksGamePage()
            }
            entry<Route.ThanksAppPage> {
                ThanksAppPage()
            }
        }
    }

    // 将 backStack 转换为装饰后的条目
    val entries = rememberDecoratedNavEntries(
        backStack = backStack,
        entryDecorators = listOf(rememberSaveableStateHolderNavEntryDecorator()),
        entryProvider = entryProvider,
    )

    // 提供 Navigator 给所有子组件
    CompositionLocalProvider(
        LocalNavigator provides navigator,
    ) {
        val transitionEffects = NavDisplayTransitionEffects(
            enableCornerClip = true,
            dimAmount = 0.5f,
            blockInputDuringTransition = true,
            popDirectionFollowsSwipeEdge = false,
        )

        NavDisplay(
            entries = entries,
            onBack = { navigator.pop() }, // 返回按钮处理
            transitionEffects = transitionEffects,
        )
    }
}