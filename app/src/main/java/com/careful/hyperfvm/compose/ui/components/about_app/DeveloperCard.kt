package com.careful.hyperfvm.compose.ui.components.about_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.careful.hyperfvm.compose.R
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Surface
import top.yukonga.miuix.kmp.extra.SuperArrow
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.PressFeedbackType

@Composable
fun DeveloperCard() {
    SmallTitle(text = "开发者")
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
            title = "Phantom_Careful",
            summary = "QQ空间1-8服·喵帕斯赛高",
            startAction = {
                Surface(
                    Modifier.size(50.dp),
                    CircleShape
                ) {
                    Image(
                        painterResource(id = R.drawable.phantom_careful),
                        contentDescription = null
                    )
                }
            },
            onClick = { /* 处理点击事件 */ },
        )
    }
}