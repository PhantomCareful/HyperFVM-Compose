package com.careful.hyperfvm.compose.ui.components.about_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.careful.hyperfvm.compose.R
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun MoreCard(
    isEnableBlur: Boolean = true
) {
    SmallTitle(text = "更多")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
        colors = if (isEnableBlur) {
            CardDefaults.defaultColors(
                color = MiuixTheme.colorScheme.background.copy(alpha = 0.25f)
            )
        } else {
            CardDefaults.defaultColors()
        },
    ) {
        ArrowPreference(
            title = "项目地址",
            summary = "本项目基于AGPL3.0协议开源",
            startAction = {
                Image(
                    painter = painterResource(id = R.drawable.ic_github),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        color = MiuixTheme.colorScheme.onBackground,
                        blendMode = BlendMode.SrcIn
                    )
                )
            },
            onClick = { /* 处理点击事件 */ },
        )
        ArrowPreference(
            title = "获取历史版本",
            summary = "有需要的同学可以自行下载",
            startAction = {
                Image(
                    painter = painterResource(id = R.drawable.ic_cloud_download),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        color = MiuixTheme.colorScheme.onBackground,
                        blendMode = BlendMode.SrcIn
                    )
                )
            },
            onClick = { /* 处理点击事件 */ },
        )
        ArrowPreference(
            title = "哔哩哔哩",
            summary = "获取最新开发动态",
            startAction = {
                Image(
                    painter = painterResource(id = R.drawable.ic_bilibili),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        color = MiuixTheme.colorScheme.onBackground,
                        blendMode = BlendMode.SrcIn
                    )
                )
            },
            onClick = { /* 处理点击事件 */ },
        )
        ArrowPreference(
            title = "腾讯频道",
            summary = "内测&反馈&建议请戳这里",
            startAction = {
                Image(
                    painter = painterResource(id = R.drawable.ic_qq),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        color = MiuixTheme.colorScheme.onBackground,
                        blendMode = BlendMode.SrcIn
                    )
                )
            },
            onClick = { /* 处理点击事件 */ },
        )
    }
}