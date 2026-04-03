package com.careful.hyperfvm.compose.ui.pages.main

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.fontscaling.MathUtils.lerp
import androidx.compose.ui.viewinterop.AndroidView
import com.careful.hyperfvm.compose.R
import com.careful.hyperfvm.compose.LocalAppState
import com.careful.hyperfvm.compose.ui.animation.bgeffect.BgEffectView
import com.careful.hyperfvm.compose.ui.components.about_app.CoContributorCard
import com.careful.hyperfvm.compose.ui.components.about_app.DeveloperCard
import com.careful.hyperfvm.compose.ui.components.about_app.ImportantCard
import com.careful.hyperfvm.compose.ui.components.about_app.MoreCard
import com.careful.hyperfvm.compose.ui.components.about_app.ThanksCard
import com.careful.hyperfvm.compose.ui.theme.getDarkMode
import com.careful.hyperfvm.compose.blur.haze.haze
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.overScrollVertical
import top.yukonga.miuix.kmp.utils.scrollEndHaptic

@SuppressLint("SetTextI18n", "RestrictedApi")
@Composable
fun AboutAppPage(
    outPaddingValues: PaddingValues,
) {
    val hazeConfig = haze()
    val lazyListState = rememberLazyListState()

    val appState = LocalAppState.current
    val colorMode = appState.colorMode
    val darkMode = getDarkMode(colorMode)

    // 实现滑动内容时，自动隐藏和显示背景图标和文字
    val iconAlpha = remember { mutableFloatStateOf(1f) }
    val iconScale = remember { mutableFloatStateOf(1f) }
    val titleAlpha = remember { mutableFloatStateOf(1f) }
    val titleScale = remember { mutableFloatStateOf(1f) }
    val subtitleAlpha = remember { mutableFloatStateOf(1f) }
    val subtitleScale = remember { mutableFloatStateOf(1f) }
    val iconStartPx = 500f
    val iconEndPx = 600f
    val titleStartPx = 300f
    val titleEndPx = 400f
    val subtitleStartPx = 50
    val subtitleEndPx = 150f

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .hazeSource(state = hazeConfig.hazeState)
        ) {
            LaunchedEffect(lazyListState) {
                snapshotFlow {
                    Pair(lazyListState.firstVisibleItemIndex, lazyListState.firstVisibleItemScrollOffset)
                }.collect { (index, offset) ->
                    if (index > 0) {
                        // 已经滚动过占位项，所有标题完全隐藏
                        iconAlpha.floatValue = 0f
                        iconScale.floatValue = 0.9f
                        titleAlpha.floatValue = 0f
                        titleScale.floatValue = 0.9f
                        subtitleAlpha.floatValue = 0f
                        subtitleScale.floatValue = 0.9f
                    } else {
                        val offsetPx = offset.toFloat()

                        // App图标
                        val iconAlphaValue = when {
                            offsetPx <= iconStartPx -> 1f
                            offsetPx >= iconEndPx -> 0f
                            else -> 1f - ((offsetPx - iconStartPx) / (iconEndPx - iconStartPx))
                        }.coerceIn(0f, 1f)
                        iconAlpha.floatValue = iconAlphaValue
                        iconScale.floatValue = lerp(0.9f, 1f, iconAlphaValue)

                        // App名字
                        val titleAlphaValue = when {
                            offsetPx <= titleStartPx -> 1f
                            offsetPx >= titleEndPx -> 0f
                            else -> 1f - ((offsetPx - titleStartPx) / (titleEndPx - titleStartPx))
                        }.coerceIn(0f, 1f)
                        titleAlpha.floatValue = titleAlphaValue
                        titleScale.floatValue = lerp(0.9f, 1f, titleAlphaValue)

                        // 版本号
                        val subtitleAlphaValue = when {
                            offsetPx <= subtitleStartPx -> 1f
                            offsetPx >= subtitleEndPx -> 0f
                            else -> 1f - ((offsetPx - subtitleStartPx) / (subtitleEndPx - subtitleStartPx))
                        }.coerceIn(0f, 1f)
                        subtitleAlpha.floatValue = subtitleAlphaValue
                        subtitleScale.floatValue = lerp(0.9f, 1f, subtitleAlphaValue)
                    }
                }
            }

            // 1. 流光背景
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                AndroidView(
                    modifier = Modifier
                        .fillMaxSize(),
                    factory = { context ->
                        BgEffectView(context, darkMode)
                    }
                ) {
                    it.updateMode(darkMode)
                }
            }

            // 2. 标题文字区域（位于背景上方）
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = paddingValues.calculateTopPadding() + 60.dp)
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // App图标
                Box(
                    modifier = Modifier
                        .hazeSource(state = hazeConfig.hazeState)
                        .background(Color.Transparent)
                        .alpha(iconAlpha.floatValue)
                        .scale(iconScale.floatValue)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.app_icon),
                        contentDescription = null,
                        modifier = Modifier.hazeEffect(hazeConfig.hazeState) {
                            style = hazeConfig.hazeStyle
                            blurRadius = 25.dp
                            noiseFactor = 0f
                        },
                        colorFilter = ColorFilter.tint(
                            color = if (darkMode) MiuixTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                            else MiuixTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                            blendMode = BlendMode.SrcIn
                        )
                    )
                }

                // App名字
                Text(
                    text = "HyperFVM",
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (darkMode) MiuixTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    else MiuixTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier
                        .hazeEffect(hazeConfig.hazeState) {
                            style = hazeConfig.hazeStyle
                            blurRadius = 25.dp
                            noiseFactor = 0f
                        }
                        .alpha(titleAlpha.floatValue)
                        .scale(titleScale.floatValue)
                )

                // 版本号
                val context = LocalContext.current
                val versionName = context.packageManager.getPackageInfo(context.packageName, 0).versionName
                Text(
                    text = versionName.toString(),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .alpha(subtitleAlpha.floatValue)
                        .scale(subtitleScale.floatValue),
                    fontWeight = FontWeight.Medium,
                    color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
                    textAlign = TextAlign.Center
                )
            }

            // 内容列表（顶部预留背景高度）
            LazyColumn(
                modifier = Modifier
                    .scrollEndHaptic()
                    .overScrollVertical()
                    .fillMaxSize(),
                state = lazyListState,
                contentPadding = PaddingValues(
                    top = paddingValues.calculateTopPadding(),
                    bottom = outPaddingValues.calculateBottomPadding() + 12.dp,
                )
            ) {
                item {
                    Box(Modifier.height(300.dp).fillMaxWidth())
                }
                item {
                    DeveloperCard()
                    CoContributorCard()
                    ThanksCard()
                    ImportantCard()
                    MoreCard()
                }
            }
        }
    }
}