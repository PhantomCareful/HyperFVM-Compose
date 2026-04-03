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
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.PressFeedbackType

@Composable
fun CoContributorCard() {
    val navigator = LocalNavigator.current

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
        ArrowPreference(
            title = "共建团",
            summary = "HyperFVM也是这些同学们的作品",
            startAction = {
                Image(
                    painter = painterResource(id = R.drawable.ic_account_group),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        color = MiuixTheme.colorScheme.onBackground,
                        blendMode = BlendMode.SrcIn
                    )
                )
            },
            onClick = { navigator.push(Route.CoContributorPage) },
        )
    }
}