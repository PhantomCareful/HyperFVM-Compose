package com.careful.hyperfvm.compose.backup

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
import kotlinx.coroutines.flow.onEach
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

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@SuppressLint("SetTextI18n", "RestrictedApi")
@Composable
fun AboutAppPage(
    outPaddingValues: PaddingValues,
) {
    val lazyListState = rememberLazyListState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        AboutContent(
            padding = PaddingValues(
                top = paddingValues.calculateTopPadding(),
                bottom = outPaddingValues.calculateBottomPadding(),
            ),
            lazyListState = lazyListState,
        )
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
private fun AboutContent(
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
    var logoAreaY by remember { mutableFloatStateOf(0f) }
    var iconY by remember { mutableFloatStateOf(0f) }
    var projectNameY by remember { mutableFloatStateOf(0f) }
    var versionCodeY by remember { mutableFloatStateOf(0f) }

    var iconProgress by remember { mutableFloatStateOf(0f) }
    var projectNameProgress by remember { mutableFloatStateOf(0f) }
    var versionCodeProgress by remember { mutableFloatStateOf(0f) }
    var initialLogoAreaY by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemScrollOffset }
            .onEach { offset ->
                if (lazyListState.firstVisibleItemIndex > 0) {
                    if (iconProgress != 1f) iconProgress = 1f
                    if (projectNameProgress != 1f) projectNameProgress = 1f
                    return@onEach
                }

                if (initialLogoAreaY == 0f && logoAreaY > 0f) {
                    initialLogoAreaY = logoAreaY
                }
                val refLogoAreaY = if (initialLogoAreaY > 0f) initialLogoAreaY else logoAreaY

                val stage1TotalLength = refLogoAreaY - versionCodeY
                val stage2TotalLength = versionCodeY - projectNameY
                val stage3TotalLength = projectNameY - iconY

                val versionCodeDelay = stage1TotalLength * 0.5f
                versionCodeProgress = ((offset.toFloat() - versionCodeDelay) / (stage1TotalLength - versionCodeDelay).coerceAtLeast(1f))
                    .coerceIn(0f, 1f)
                projectNameProgress = ((offset.toFloat() - stage1TotalLength) / stage2TotalLength.coerceAtLeast(1f))
                    .coerceIn(0f, 1f)
                iconProgress = ((offset.toFloat() - stage1TotalLength - stage2TotalLength) / stage3TotalLength.coerceAtLeast(1f))
                    .coerceIn(0f, 1f)
            }
            .collect { }
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
                        alpha = 1 - iconProgress
                        scaleX = 1 - (iconProgress * 0.05f)
                        scaleY = 1 - (iconProgress * 0.05f)
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
                        alpha = 1 - projectNameProgress
                        scaleX = 1 - (projectNameProgress * 0.05f)
                        scaleY = 1 - (projectNameProgress * 0.05f)
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
                        alpha = 1 - versionCodeProgress
                        scaleX = 1 - (versionCodeProgress * 0.05f)
                        scaleY = 1 - (versionCodeProgress * 0.05f)
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
                        )
                        .onGloballyPositioned { coordinates ->
                            val y = coordinates.positionInWindow().y
                            val size = coordinates.size
                            logoAreaY = y + size.height
                        },
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