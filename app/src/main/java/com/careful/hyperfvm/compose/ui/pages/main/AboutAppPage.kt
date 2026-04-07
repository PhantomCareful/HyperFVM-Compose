package com.careful.hyperfvm.compose.ui.pages.main

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.fontscaling.MathUtils.lerp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.BlendMode as ComposeBlendMode
import com.careful.hyperfvm.compose.R
import com.careful.hyperfvm.compose.LocalAppState
import com.careful.hyperfvm.compose.ui.components.about_app.CoContributorCard
import com.careful.hyperfvm.compose.ui.components.about_app.DeveloperCard
import com.careful.hyperfvm.compose.ui.components.about_app.ImportantCard
import com.careful.hyperfvm.compose.ui.components.about_app.MoreCard
import com.careful.hyperfvm.compose.ui.components.about_app.ThanksCard
import com.careful.hyperfvm.compose.ui.theme.getDarkMode
import com.careful.hyperfvm.compose.ui.animation.effect.BgEffectBackground
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.BlendColorEntry
import top.yukonga.miuix.kmp.blur.BlurBlendMode
import top.yukonga.miuix.kmp.blur.BlurColors
import top.yukonga.miuix.kmp.blur.BlurDefaults
import top.yukonga.miuix.kmp.blur.isRenderEffectSupported
import top.yukonga.miuix.kmp.blur.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.blur.textureBlur
import top.yukonga.miuix.kmp.shapes.SmoothRoundedCornerShape
import top.yukonga.miuix.kmp.theme.MiuixTheme.colorScheme
import top.yukonga.miuix.kmp.utils.overScrollVertical
import top.yukonga.miuix.kmp.utils.scrollEndHaptic

@SuppressLint("SetTextI18n", "RestrictedApi")
@Composable
fun AboutAppPage(
    outPaddingValues: PaddingValues,
) {
    val lazyListState = rememberLazyListState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            AboutContentBlur(
                padding = PaddingValues(
                    top = paddingValues.calculateTopPadding(),
                    bottom = outPaddingValues.calculateBottomPadding(),
                ),
                lazyListState = lazyListState,
            )
        } else {
            AboutContent(
                padding = PaddingValues(
                    top = paddingValues.calculateTopPadding(),
                    bottom = outPaddingValues.calculateBottomPadding(),
                ),
                lazyListState = lazyListState,
            )
        }
    }
}

@SuppressLint("RestrictedApi")
@Composable
private fun AboutContent(
    padding: PaddingValues,
    lazyListState: LazyListState,
) {
    val scrollPadding = pageContentPadding(
        padding,
        padding,
        isWideScreen = true,
        extraStart = WindowInsets.displayCutout.asPaddingValues().calculateLeftPadding(LayoutDirection.Ltr),
        extraEnd = WindowInsets.displayCutout.asPaddingValues().calculateRightPadding(LayoutDirection.Ltr),
    )
    val logoPadding = pageContentPadding(
        padding,
        padding,
        isWideScreen = true,
        extraTop = 40.dp,
        extraStart = WindowInsets.displayCutout.asPaddingValues().calculateLeftPadding(LayoutDirection.Ltr),
        extraEnd = WindowInsets.displayCutout.asPaddingValues().calculateRightPadding(LayoutDirection.Ltr),
    )

    val density = LocalDensity.current
    var logoHeightDp by remember { mutableStateOf(300.dp) }
    var iconY by remember { mutableFloatStateOf(0f) }
    var projectNameY by remember { mutableFloatStateOf(0f) }
    var versionCodeY by remember { mutableFloatStateOf(0f) }

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

    // 标题文字区域（位于背景上方）
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = logoPadding.calculateTopPadding() + 52.dp,
                start = logoPadding.calculateLeftPadding(LayoutDirection.Ltr),
                end = logoPadding.calculateRightPadding(LayoutDirection.Ltr),
            )
            .onSizeChanged { size ->
                with(density) { logoHeightDp = size.height.toDp() }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // App图标
        Image(
            modifier = Modifier
                .size(108.dp)
                .graphicsLayer {
                    alpha = iconAlpha.floatValue
                    scaleX = iconScale.floatValue
                    scaleY = iconScale.floatValue
                }
                .onGloballyPositioned { coordinates ->
                    if (iconY != 0f) return@onGloballyPositioned
                    val y = coordinates.positionInWindow().y
                    val size = coordinates.size
                    iconY = y + size.height
                },
            painter = painterResource(R.drawable.app_icon),
            colorFilter = ColorFilter.tint(colorScheme.onBackground),
            contentDescription = null,
        )

        // App名字
        Text(
            modifier = Modifier.padding(top = 12.dp, bottom = 5.dp)
                .onGloballyPositioned { coordinates ->
                    if (projectNameY != 0f) return@onGloballyPositioned
                    val y = coordinates.positionInWindow().y
                    val size = coordinates.size
                    projectNameY = y + size.height
                }
                .graphicsLayer {
                    alpha = titleAlpha.floatValue
                    scaleX = titleScale.floatValue
                    scaleY = titleScale.floatValue
                },
            text = "HyperFVM",
            color = colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            fontSize = 45.sp,
        )

        // 版本号
        val context = LocalContext.current
        val versionName = context.packageManager.getPackageInfo(context.packageName, 0).versionName
        Text(
            modifier = Modifier.padding(top = 12.dp, bottom = 5.dp)
                .graphicsLayer {
                    alpha = subtitleAlpha.floatValue
                    scaleX = subtitleScale.floatValue
                    scaleY = subtitleScale.floatValue
                }
                .onGloballyPositioned { coordinates ->
                    if (versionCodeY != 0f) return@onGloballyPositioned
                    val y = coordinates.positionInWindow().y
                    val size = coordinates.size
                    versionCodeY = y + size.height
                },
            text = versionName.toString(),
            color = colorScheme.onBackground,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
        )
    }

    // 内容列表（顶部预留背景高度）
    LazyColumn(
        state = lazyListState,
        modifier = Modifier
            .fillMaxSize()
            .scrollEndHaptic()
            .overScrollVertical(),
        contentPadding = PaddingValues(
            top = scrollPadding.calculateTopPadding(),
            bottom = scrollPadding.calculateBottomPadding(),
            start = scrollPadding.calculateLeftPadding(LayoutDirection.Ltr),
            end = scrollPadding.calculateRightPadding(LayoutDirection.Ltr),
        ),
    ) {
        item(key = "logoSpacer") {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(
                        logoHeightDp + logoPadding.calculateTopPadding() - scrollPadding.calculateTopPadding() + 126.dp,
                    ),
                contentAlignment = Alignment.TopCenter,
                content = { },
            )
        }
        item {
            DeveloperCard(false)
            CoContributorCard(false)
            ThanksCard(false)
            ImportantCard(false)
            MoreCard(false)
        }
    }
}

@SuppressLint("RestrictedApi")
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
private fun AboutContentBlur(
    padding: PaddingValues,
    lazyListState: LazyListState,
) {
    val appState = LocalAppState.current
    val colorMode = appState.colorMode
    val darkMode = getDarkMode(colorMode)

    val backdrop = rememberLayerBackdrop()
    var noiseCoefficient by remember { mutableFloatStateOf(BlurDefaults.NoiseCoefficient) }

    val scrollPadding = pageContentPadding(
        padding,
        padding,
        isWideScreen = true,
        extraStart = WindowInsets.displayCutout.asPaddingValues().calculateLeftPadding(LayoutDirection.Ltr),
        extraEnd = WindowInsets.displayCutout.asPaddingValues().calculateRightPadding(LayoutDirection.Ltr),
    )
    val logoPadding = pageContentPadding(
        padding,
        padding,
        isWideScreen = true,
        extraTop = 40.dp,
        extraStart = WindowInsets.displayCutout.asPaddingValues().calculateLeftPadding(LayoutDirection.Ltr),
        extraEnd = WindowInsets.displayCutout.asPaddingValues().calculateRightPadding(LayoutDirection.Ltr),
    )

    var blurEnable by remember { mutableStateOf(isRenderEffectSupported()) }
    val dynamicBackground = remember { mutableStateOf(isRuntimeShaderSupported()) }
    val effectBackground = remember { mutableStateOf(isRuntimeShaderSupported()) }

    val blendColors = remember(darkMode) {
        if (darkMode) {
            listOf(
                BlendColorEntry(Color(0xe6a1a1a1), BlurBlendMode.ColorDodge),
                BlendColorEntry(Color(0x4de6e6e6), BlurBlendMode.LinearLight),
                BlendColorEntry(Color(0xff1af500), BlurBlendMode.Lab),
            )
        } else {
            listOf(
                BlendColorEntry(Color(0xcc4a4a4a), BlurBlendMode.ColorBurn),
                BlendColorEntry(Color(0xff4f4f4f), BlurBlendMode.LinearLight),
                BlendColorEntry(Color(0xff1af200), BlurBlendMode.Lab),
            )
        }
    }

    val density = LocalDensity.current
    var logoHeightDp by remember { mutableStateOf(300.dp) }
    var iconY by remember { mutableFloatStateOf(0f) }
    var projectNameY by remember { mutableFloatStateOf(0f) }
    var versionCodeY by remember { mutableFloatStateOf(0f) }

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

    BgEffectBackground(
        dynamicBackground = dynamicBackground.value,
        modifier = Modifier.fillMaxSize(),
        bgModifier = Modifier.layerBackdrop(backdrop),
        effectBackground = effectBackground.value,
        alpha = { 1f },
    ) {
        // 标题文字区域（位于背景上方）
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = logoPadding.calculateTopPadding() + 52.dp,
                    start = logoPadding.calculateLeftPadding(LayoutDirection.Ltr),
                    end = logoPadding.calculateRightPadding(LayoutDirection.Ltr),
                )
                .onSizeChanged { size ->
                    with(density) { logoHeightDp = size.height.toDp() }
                },
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // App图标
            Image(
                modifier = Modifier
                    .size(108.dp)
                    .graphicsLayer {
                        alpha = iconAlpha.floatValue
                        scaleX = iconScale.floatValue
                        scaleY = iconScale.floatValue
                    }
                    .onGloballyPositioned { coordinates ->
                        if (iconY != 0f) return@onGloballyPositioned
                        val y = coordinates.positionInWindow().y
                        val size = coordinates.size
                        iconY = y + size.height
                    }.textureBlur(
                        backdrop = backdrop,
                        shape = SmoothRoundedCornerShape(16.dp),
                        blurRadius = 200f,
                        noiseCoefficient = noiseCoefficient,
                        colors = BlurColors(
                            blendColors = blendColors,
                        ),
                        contentBlendMode = ComposeBlendMode.DstIn,
                        enabled = blurEnable,
                    ),
                painter = painterResource(R.drawable.app_icon),
                contentDescription = null,
            )

            // App名字
            Text(
                modifier = Modifier.padding(top = 12.dp, bottom = 5.dp)
                    .onGloballyPositioned { coordinates ->
                        if (projectNameY != 0f) return@onGloballyPositioned
                        val y = coordinates.positionInWindow().y
                        val size = coordinates.size
                        projectNameY = y + size.height
                    }
                    .graphicsLayer {
                        alpha = titleAlpha.floatValue
                        scaleX = titleScale.floatValue
                        scaleY = titleScale.floatValue
                    }
                    .textureBlur(
                        backdrop = backdrop,
                        shape = SmoothRoundedCornerShape(16.dp),
                        blurRadius = 200f,
                        noiseCoefficient = noiseCoefficient,
                        colors = BlurColors(
                            blendColors = blendColors,
                        ),
                        contentBlendMode = ComposeBlendMode.DstIn,
                        enabled = blurEnable,
                    ),
                text = "HyperFVM",
                color = colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 45.sp,
            )

            // 版本号
            val context = LocalContext.current
            val versionName = context.packageManager.getPackageInfo(context.packageName, 0).versionName
            Text(
                modifier = Modifier.padding(top = 12.dp, bottom = 5.dp)
                    .graphicsLayer {
                        alpha = subtitleAlpha.floatValue
                        scaleX = subtitleScale.floatValue
                        scaleY = subtitleScale.floatValue
                    }
                    .onGloballyPositioned { coordinates ->
                        if (versionCodeY != 0f) return@onGloballyPositioned
                        val y = coordinates.positionInWindow().y
                        val size = coordinates.size
                        versionCodeY = y + size.height
                    }
                    .textureBlur(
                        backdrop = backdrop,
                        shape = SmoothRoundedCornerShape(16.dp),
                        blurRadius = 200f,
                        noiseCoefficient = noiseCoefficient,
                        colors = BlurColors(
                            blendColors = blendColors,
                        ),
                        contentBlendMode = ComposeBlendMode.DstIn,
                        enabled = blurEnable,
                    ),
                text = versionName.toString(),
                color = colorScheme.onBackground,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
            )
        }

        // 内容列表（顶部预留背景高度）
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .fillMaxSize()
                .scrollEndHaptic()
                .overScrollVertical(),
            contentPadding = PaddingValues(
                top = scrollPadding.calculateTopPadding(),
                bottom = scrollPadding.calculateBottomPadding(),
                start = scrollPadding.calculateLeftPadding(LayoutDirection.Ltr),
                end = scrollPadding.calculateRightPadding(LayoutDirection.Ltr),
            ),
        ) {
            item(key = "logoSpacer") {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(
                            logoHeightDp + logoPadding.calculateTopPadding() - scrollPadding.calculateTopPadding() + 126.dp,
                        ),
                    contentAlignment = Alignment.TopCenter,
                    content = { },
                )
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

@Composable
fun pageContentPadding(
    innerPadding: PaddingValues,
    outerPadding: PaddingValues,
    isWideScreen: Boolean,
    extraTop: Dp = 0.dp,
    extraStart: Dp = 0.dp,
    extraEnd: Dp = 0.dp,
): PaddingValues {
    val topPadding = innerPadding.calculateTopPadding() + extraTop
    val bottomPadding = if (isWideScreen) {
        WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() + outerPadding.calculateBottomPadding()
    } else {
        outerPadding.calculateBottomPadding()
    }
    return remember(topPadding, bottomPadding, extraStart, extraEnd) {
        PaddingValues(
            top = topPadding,
            start = extraStart,
            end = extraEnd,
            bottom = bottomPadding,
        )
    }
}