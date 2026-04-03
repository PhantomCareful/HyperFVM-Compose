package com.careful.hyperfvm.compose.blur

import androidx.compose.runtime.Composable
import top.yukonga.miuix.kmp.blur.BlendColorEntry
import top.yukonga.miuix.kmp.blur.BlurBlendMode
import top.yukonga.miuix.kmp.blur.BlurColors
import top.yukonga.miuix.kmp.theme.MiuixTheme

data class MiuixBlurConfig(
    val miuixBlurColors: BlurColors
)

@Composable
fun blurConfig(): MiuixBlurConfig {
    val miuixBlurColors = BlurColors(
        blendColors = listOf(
            BlendColorEntry(
                color = MiuixTheme.colorScheme.surface.copy(alpha = 0.3f),
                mode = BlurBlendMode.SrcOver
            )
        ),
        brightness = 0f,  // 范围：[-1, 1]，0 = 无变化
        contrast = 1f,     // 倍数，1 = 无变化
        saturation = 1f    // 倍数，1 = 无变化
    )

    return MiuixBlurConfig(miuixBlurColors)
}