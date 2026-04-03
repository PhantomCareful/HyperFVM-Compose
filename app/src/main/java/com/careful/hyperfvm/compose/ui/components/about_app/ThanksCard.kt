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
import com.careful.hyperfvm.compose.nevigation3.LocalNavigator
import com.careful.hyperfvm.compose.nevigation3.Route
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun ThanksCard() {
    val navigator = LocalNavigator.current

    SmallTitle(text = "致谢")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
        colors = CardDefaults.defaultColors(
            color = MiuixTheme.colorScheme.background.copy(alpha = 0.25f)
        ),
    ) {
        ArrowPreference(
            title = "游戏相关",
            summary = "HyperFVM使用了这些老师们的作品",
            startAction = {
                Image(
                    painter = painterResource(id = R.drawable.ic_account_multiple),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        color = MiuixTheme.colorScheme.onBackground,
                        blendMode = BlendMode.SrcIn
                    )
                )
            },
            onClick = { navigator.push(Route.ThanksGamePage) },
        )
        ArrowPreference(
            title = "App相关",
            summary = "HyperFVM使用了这些开源项目",
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
            onClick = { navigator.push(Route.ThanksAppPage) },
        )
    }
}