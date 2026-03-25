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
import top.yukonga.miuix.kmp.extra.SuperArrow
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.PressFeedbackType

@Composable
fun ImportantCard() {
    SmallTitle(text = "一些必要的内容")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
        colors = CardDefaults.defaultColors(
            color = MiuixTheme.colorScheme.background.copy(alpha = 0.25f)
        ),
        pressFeedbackType = PressFeedbackType.Sink,
    ) {
        SuperArrow(
            title = "使用说明",
            summary = "快速了解HyperFVM",
            startAction = {
                Image(
                    painter = painterResource(id = R.drawable.ic_notebook),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        color = MiuixTheme.colorScheme.onBackground,
                        blendMode = BlendMode.SrcIn
                    )
                )
            },
            onClick = { /* 处理点击事件 */ },
        )
        SuperArrow(
            title = "更新日志",
            summary = "记录了HyperFVM的成长过程",
            startAction = {
                Image(
                    painter = painterResource(id = R.drawable.ic_book),
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