package com.careful.hyperfvm.compose.card_data_detail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun DeveloperTips() {
    Text(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp)
            .fillMaxWidth(),
        text = "数据来自：So陌路の\n数据为人工录入，若有问题可向开发者反馈\uD83E\uDEF0",
        style = MiuixTheme.textStyles.subtitle,
        color = MiuixTheme.colorScheme.primary,
        textAlign = TextAlign.Center,
    )
    Text(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 60.dp)
            .fillMaxWidth(),
        text = "HyperFVM\nMade By Phantom Careful",
        style = MiuixTheme.textStyles.body2,
        textAlign = TextAlign.Center,
    )
}