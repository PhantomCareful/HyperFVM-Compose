package com.careful.hyperfvm.compose.ui.components.card_data.card_component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.careful.hyperfvm.compose.LocalAppState
import com.careful.hyperfvm.compose.ui.components.card_data.ImagesRow
import com.careful.hyperfvm.compose.ui.theme.getDarkMode
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.Checkbox
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.SwitchDefaults
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.extra.SuperSwitch
import top.yukonga.miuix.kmp.extra.WindowBottomSheet
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.PressFeedbackType
import top.yukonga.miuix.kmp.utils.overScrollVertical
import top.yukonga.miuix.kmp.utils.scrollEndHaptic

@Composable
fun BaseInfoFusionCard(
    cardImagesBig: List<Int>,
    cardImages: List<Int>,
    cardNames: List<Int>,
    cardDescription: List<String>
) {
    // =================================================== 基础信息 ===================================================

    ImagesRow(
        modifier = Modifier.padding(horizontal = 12.dp).fillMaxWidth(),
        imageResIds = cardImagesBig,
        imageWidth = 0,
        imageHeight = 0,
    )
    SmallTitle(text = "基础信息")
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
    ) {
        BasicComponent(
            modifier = Modifier,
            title = "初级融合：" + stringResource(cardNames[0]),
            summary = cardDescription[0],
            endActions = {
                ImagesRow(
                    imageResIds = listOf(cardImages[0]),
                    imageWidth = 40,
                    imageHeight = 50,
                )
            },
            onClick = {  },
        )
        BasicComponent(
            modifier = Modifier,
            title = "深度融合：" + stringResource(cardNames[1]),
            summary = cardDescription[1],
            endActions = {
                ImagesRow(
                    imageResIds = listOf(cardImages[1]),
                    imageWidth = 40,
                    imageHeight = 50,
                )
            },
            onClick = {  },
        )
        BasicComponent(
            modifier = Modifier,
            title = "灵魂融合：" + stringResource(cardNames[2]),
            summary = cardDescription[2],
            endActions = {
                ImagesRow(
                    imageResIds = listOf(cardImages[2]),
                    imageWidth = 40,
                    imageHeight = 50,
                )
            },
            onClick = {  },
        )
    }
}

@Composable
fun BaseInfoWithBigImage(
    cardImagesBig: List<Int>,
    cardImages: List<Int>,
    cardNames: List<Int>,
    cardDescription: List<String>
) {
    // =================================================== 基础信息 ===================================================

    ImagesRow(
        modifier = Modifier.padding(horizontal = 12.dp).fillMaxWidth(),
        imageResIds = cardImagesBig,
        imageWidth = 0,
        imageHeight = 0,
    )
    SmallTitle(text = "基础信息")
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
    ) {
        BasicComponent(
            modifier = Modifier,
            title = "不转：" + stringResource(cardNames[0]),
            summary = cardDescription[0],
            endActions = {
                ImagesRow(
                    imageResIds = listOf(cardImages[0]),
                    imageWidth = 40,
                    imageHeight = 50,
                )
            },
            onClick = {  },
        )
        BasicComponent(
            modifier = Modifier,
            title = "一转：" + stringResource(cardNames[1]),
            summary = cardDescription[1],
            endActions = {
                ImagesRow(
                    imageResIds = listOf(cardImages[1]),
                    imageWidth = 40,
                    imageHeight = 50,
                )
            },
            onClick = {  },
        )
        BasicComponent(
            modifier = Modifier,
            title = "二转：" + stringResource(cardNames[2]),
            summary = cardDescription[2],
            endActions = {
                ImagesRow(
                    imageResIds = listOf(cardImages[2]),
                    imageWidth = 40,
                    imageHeight = 50,
                )
            },
            onClick = {  },
        )
    }
}

@Composable
fun BaseInfoGoldenCard(
    cardImagesBig: List<Int>,
    cardImages: List<Int>,
    cardNames: List<Int>,
    cardDescription: List<String>
) {
    // =================================================== 基础信息 ===================================================

    if (cardImagesBig.size == 4) {
        ImagesRow(
            modifier = Modifier.padding(horizontal = 12.dp).fillMaxWidth(),
            imageResIds = listOf(
                cardImagesBig[0],
                cardImagesBig[1]
            ),
            imageWidth = 0,
            imageHeight = 0,
        )
        ImagesRow(
            modifier = Modifier.padding(horizontal = 12.dp).fillMaxWidth(),
            imageResIds = listOf(
                cardImagesBig[2],
                cardImagesBig[3]
            ),
            imageWidth = 0,
            imageHeight = 0,
        )
    } else {
        ImagesRow(
            modifier = Modifier.padding(horizontal = 12.dp).fillMaxWidth(),
            imageResIds = cardImagesBig,
            imageWidth = 0,
            imageHeight = 0,
        )
    }

    SmallTitle(text = "基础信息")
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
    ) {
        BasicComponent(
            modifier = Modifier,
            title = "不转：" + stringResource(cardNames[0]),
            summary = cardDescription[0],
            endActions = {
                ImagesRow(
                    imageResIds = listOf(cardImages[0]),
                    imageWidth = 40,
                    imageHeight = 50,
                )
            },
            onClick = {  },
        )
        BasicComponent(
            modifier = Modifier,
            title = "三转：" + stringResource(cardNames[1]),
            summary = cardDescription[1],
            endActions = {
                ImagesRow(
                    imageResIds = listOf(cardImages[1]),
                    imageWidth = 40,
                    imageHeight = 50,
                )
            },
            onClick = {  },
        )
        BasicComponent(
            modifier = Modifier,
            title = "四转：" + stringResource(cardNames[2]),
            summary = cardDescription[2],
            endActions = {
                ImagesRow(
                    imageResIds = listOf(cardImages[2]),
                    imageWidth = 40,
                    imageHeight = 50,
                )
            },
            onClick = {  },
        )
        BasicComponent(
            modifier = Modifier,
            title = "终转：" + stringResource(cardNames[3]),
            summary = cardDescription[3],
            endActions = {
                ImagesRow(
                    imageResIds = listOf(cardImages[3]),
                    imageWidth = 40,
                    imageHeight = 50,
                )
            },
            onClick = {  },
        )
    }
}

@Composable
fun BaseInfoCommonCard(
    cardDescription: List<String>,
    startIndex: Int
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
    ) {
        BasicComponent(
            modifier = Modifier,
            title = cardDescription[startIndex],
            onClick = {  },
        )
        BasicComponent(
            modifier = Modifier,
            title = cardDescription[startIndex + 1],
            onClick = {  },
        )
        BasicComponent(
            modifier = Modifier,
            title = cardDescription[startIndex + 2],
            onClick = {  },
        )
    }
}

@Composable
fun AnimalCardDecomposeAndGetInfo(
    cardDecomposeAndGetImages: List<Int>,
    cardDecomposeData: List<Int>,
    cardGetData: List<Int>,
) {
    SmallTitle(text = "分解&兑换数据")
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
        pressFeedbackType = PressFeedbackType.Sink,
        showIndication = true,
    ) {
        BasicComponent(
            modifier = Modifier,
            title = "防御卡",
            summary = "分解：" + cardDecomposeData[0].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardDecomposeData[1].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardDecomposeData[2].let { if (it == 0) "\uD83D\uDEAB" else it } + "\n" +
                    "兑换：" + cardGetData[0].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardGetData[1].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardGetData[2].let { if (it == 0) "\uD83D\uDEAB" else it },
            endActions = {
                ImagesRow(
                    imageResIds = listOf(
                        cardDecomposeAndGetImages[0],
                        cardDecomposeAndGetImages[1],
                        cardDecomposeAndGetImages[2],
                    ),
                    imageWidth = 40,
                    imageHeight = 50,
                )
            },
        )
    }
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
        pressFeedbackType = PressFeedbackType.Sink,
        showIndication = true,
    ) {
        BasicComponent(
            modifier = Modifier,
            title = "技能书",
            summary = "分解：" + cardDecomposeData[3].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardDecomposeData[4].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardDecomposeData[5].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardDecomposeData[6].let { if (it == 0) "\uD83D\uDEAB" else it } + "\n" +
                    "兑换：" + cardGetData[3].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardGetData[4].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardGetData[5].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardGetData[6].let { if (it == 0) "\uD83D\uDEAB" else it },
            endActions = {
                ImagesRow(
                    imageResIds = listOf(
                        cardDecomposeAndGetImages[3],
                        cardDecomposeAndGetImages[4],
                        cardDecomposeAndGetImages[5],
                        cardDecomposeAndGetImages[6],
                    ),
                    imageWidth = 33,
                    imageHeight = 33,
                )
            },
        )
    }
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
        pressFeedbackType = PressFeedbackType.Sink,
        showIndication = true,
    ) {
        BasicComponent(
            modifier = Modifier,
            title = "一转凭证",
            summary = "分解：" + cardDecomposeData[7].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardDecomposeData[8].let { if (it == 0) "\uD83D\uDEAB" else it } + "\n" +
                    "兑换：" + cardGetData[7].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardGetData[8].let { if (it == 0) "\uD83D\uDEAB" else it },
            endActions = {
                ImagesRow(
                    imageResIds = listOf(
                        cardDecomposeAndGetImages[7],
                        cardDecomposeAndGetImages[8],
                    ),
                    imageWidth = 44,
                    imageHeight = 44,
                )
            },
        )
    }
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
        pressFeedbackType = PressFeedbackType.Sink,
        showIndication = true,
    ) {
        BasicComponent(
            modifier = Modifier,
            title = "二转凭证",
            summary = "分解：" + cardDecomposeData[9].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardDecomposeData[10].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardDecomposeData[11].let { if (it == 0) "\uD83D\uDEAB" else it } + "\n" +
                    "兑换：" + cardGetData[9].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardGetData[10].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardGetData[11].let { if (it == 0) "\uD83D\uDEAB" else it },
            endActions = {
                ImagesRow(
                    imageResIds = listOf(
                        cardDecomposeAndGetImages[9],
                        cardDecomposeAndGetImages[10],
                        cardDecomposeAndGetImages[11],
                    ),
                    imageWidth = 44,
                    imageHeight = 44,
                )
            },
        )
    }
}

@Composable
fun AnimalCardDecomposeAndGetCalculator(
    showCardDecomposeAndGetCalculatorBottomSheet: MutableState<Boolean>,
    cardDecomposeAndGetImages: List<Int>,
    cardDecomposeData: List<Int>,
    cardGetData: List<Int>,
    itemName: String
) {
    val appState = LocalAppState.current
    val colorMode = appState.colorMode
    val darkMode = getDarkMode(colorMode)

    val density = LocalDensity.current
    val haptic = LocalHapticFeedback.current
    val exchangeChecked = rememberSaveable { mutableStateOf(false) }
    val state = rememberSaveable {
        mutableStateListOf(
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
        )
    }

    WindowBottomSheet(
        show = showCardDecomposeAndGetCalculatorBottomSheet.value,
        backgroundColor = MiuixTheme.colorScheme.surface,
        title = "分解兑换计算器",
        insideMargin = DpSize(width = 0.dp, height = 0.dp),
        enableNestedScroll = false,
        onDismissRequest = { showCardDecomposeAndGetCalculatorBottomSheet.value = false }
    ) {
        CompositionLocalProvider(LocalDensity provides density) {
            Card(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
                colors = CardDefaults.defaultColors(
                    color = when {
                        (colorMode in 3..5) -> MiuixTheme.colorScheme.secondaryContainer
                        (darkMode) -> Color(0xFF1A3825)
                        else -> Color(0xFFDFFAE4)
                    }
                )
            ) {
                SuperSwitch(
                    title = "切换开关",
                    summary = "关闭计算分解数量，打开计算兑换数量\n没显示的物品代表不可分解/兑换",
                    checked = exchangeChecked.value,
                    onCheckedChange = { checked ->
                        exchangeChecked.value = checked
                    },
                    switchColors = SwitchDefaults.switchColors(
                        checkedTrackColor = when { (colorMode in 3..5) -> MiuixTheme.colorScheme.primary else -> Color(0xFF36D167) },
                    )
                )
                AnimatedVisibility(!exchangeChecked.value) {
                    BasicComponent(
                        modifier = Modifier,
                        onClick = {  },
                    ) {
                        Text(
                            text = buildAnnotatedString{
                                append("分解勾选的物品可获得 ")
                                withStyle(
                                    style = SpanStyle(
                                        color = when { (colorMode in 3..5) -> MiuixTheme.colorScheme.primary else -> Color(0xFF36D167) },
                                        fontWeight = FontWeight.Bold,
                                    )
                                ) {
                                    append((
                                            (if (state[0] == ToggleableState.On) cardDecomposeData[0] else 0) +
                                                    (if (state[1] == ToggleableState.On) cardDecomposeData[1] else 0) +
                                                    (if (state[2] == ToggleableState.On) cardDecomposeData[2] else 0) +
                                                    (if (state[3] == ToggleableState.On) cardDecomposeData[3] else 0) +
                                                    (if (state[4] == ToggleableState.On) cardDecomposeData[4] else 0) +
                                                    (if (state[5] == ToggleableState.On) cardDecomposeData[5] else 0) +
                                                    (if (state[6] == ToggleableState.On) cardDecomposeData[6] else 0) +
                                                    (if (state[7] == ToggleableState.On) cardDecomposeData[7] else 0) +
                                                    (if (state[8] == ToggleableState.On) cardDecomposeData[8] else 0) +
                                                    (if (state[9] == ToggleableState.On) cardDecomposeData[9] else 0) +
                                                    (if (state[10] == ToggleableState.On) cardDecomposeData[10] else 0) +
                                                    (if (state[11] == ToggleableState.On) cardDecomposeData[11] else 0)
                                            ).toString()
                                    )
                                }
                                append(" 个$itemName")
                            },
                            style = MiuixTheme.textStyles.headline1,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                AnimatedVisibility(exchangeChecked.value) {
                    BasicComponent(
                        modifier = Modifier,
                        onClick = {  },
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                append("兑换勾选的物品需消耗 ")
                                withStyle(
                                    style = SpanStyle(
                                        color = when { (colorMode in 3..5) -> MiuixTheme.colorScheme.primary else -> Color(0xFF36D167) },
                                        fontWeight = FontWeight.Bold
                                    )
                                ) {
                                    append((
                                            (if (state[0] == ToggleableState.On) cardGetData[0] else 0) +
                                                    (if (state[1] == ToggleableState.On) cardGetData[1] else 0) +
                                                    (if (state[2] == ToggleableState.On) cardGetData[2] else 0) +
                                                    (if (state[3] == ToggleableState.On) cardGetData[3] else 0) +
                                                    (if (state[4] == ToggleableState.On) cardGetData[4] else 0) +
                                                    (if (state[5] == ToggleableState.On) cardGetData[5] else 0) +
                                                    (if (state[6] == ToggleableState.On) cardGetData[6] else 0) +
                                                    (if (state[7] == ToggleableState.On) cardGetData[7] else 0) +
                                                    (if (state[8] == ToggleableState.On) cardGetData[8] else 0) +
                                                    (if (state[9] == ToggleableState.On) cardGetData[9] else 0) +
                                                    (if (state[10] == ToggleableState.On) cardGetData[10] else 0) +
                                                    (if (state[11] == ToggleableState.On) cardGetData[11] else 0)
                                            ).toString()
                                    )
                                }
                                append(" 个$itemName")
                            },
                            style = MiuixTheme.textStyles.headline1,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            LazyColumn(
                modifier = Modifier
                    .scrollEndHaptic()
                    .overScrollVertical()
                    .fillMaxSize(),
                overscrollEffect = null,
            ) {
                item {
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[0] != 0 || exchangeChecked.value && cardGetData[0] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[0] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[0] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[0] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[0],
                                    onClick = {
                                        if (state[0] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[0] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[0] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "卡片本体",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[0]),
                                            imageWidth = 40,
                                            imageHeight = 50,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[1] != 0 || exchangeChecked.value && cardGetData[1] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[1] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[1] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[1] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[1],
                                    onClick = {
                                        if (state[1] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[1] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[1] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "卡片一转",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[1]),
                                            imageWidth = 40,
                                            imageHeight = 50,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[2] != 0 || exchangeChecked.value && cardGetData[2] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[2] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[2] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[2] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[2],
                                    onClick = {
                                        if (state[2] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[2] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[2] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "卡片二转",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[2]),
                                            imageWidth = 40,
                                            imageHeight = 50,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[3] != 0 || exchangeChecked.value && cardGetData[3] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[3] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[3] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[3] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[3],
                                    onClick = {
                                        if (state[3] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[3] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[3] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "初级技能书",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[3]),
                                            imageWidth = 44,
                                            imageHeight = 44,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[4] != 0 || exchangeChecked.value && cardGetData[4] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[4] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[4] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[4] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[4],
                                    onClick = {
                                        if (state[4] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[4] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[4] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "高级技能书",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[4]),
                                            imageWidth = 44,
                                            imageHeight = 44,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[5] != 0 || exchangeChecked.value && cardGetData[5] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[5] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[5] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[5] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[5],
                                    onClick = {
                                        if (state[5] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[5] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[5] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "终极技能书",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[5]),
                                            imageWidth = 44,
                                            imageHeight = 44,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[6] != 0 || exchangeChecked.value && cardGetData[6] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[6] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[6] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[6] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[6],
                                    onClick = {
                                        if (state[6] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[6] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[6] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "究极技能书",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[6]),
                                            imageWidth = 44,
                                            imageHeight = 44,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[7] != 0 || exchangeChecked.value && cardGetData[7] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[7] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[7] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[7] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[7],
                                    onClick = {
                                        if (state[7] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[7] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[7] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "一转凭证A",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[7]),
                                            imageWidth = 44,
                                            imageHeight = 44,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[8] != 0 || exchangeChecked.value && cardGetData[8] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[8] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[8] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[8] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[8],
                                    onClick = {
                                        if (state[8] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[8] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[8] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "一转凭证B",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[8]),
                                            imageWidth = 44,
                                            imageHeight = 44,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[9] != 0 || exchangeChecked.value && cardGetData[9] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[9] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[9] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[9] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[9],
                                    onClick = {
                                        if (state[9] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[9] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[9] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "二转凭证A",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[9]),
                                            imageWidth = 44,
                                            imageHeight = 44,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[10] != 0 || exchangeChecked.value && cardGetData[10] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[10] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[10] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[10] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[10],
                                    onClick = {
                                        if (state[10] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[10] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[10] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "二转凭证B",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[10]),
                                            imageWidth = 44,
                                            imageHeight = 44,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[11] != 0 || exchangeChecked.value && cardGetData[11] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[11] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[11] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[11] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[11],
                                    onClick = {
                                        if (state[11] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[11] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[11] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "二转凭证C",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[11]),
                                            imageWidth = 44,
                                            imageHeight = 44,
                                        )
                                    },
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.padding(bottom = 48.dp))
                }
            }
        }
    }
}

@Composable
fun GoldenCardDecomposeAndGetInfo(
    cardDecomposeAndGetImages: List<Int>,
    cardDecomposeData: List<Int>,
    cardGetData: List<Int>,
) {
    SmallTitle(text = "分解&兑换数据")
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
        pressFeedbackType = PressFeedbackType.Sink,
        showIndication = true,
    ) {
        BasicComponent(
            modifier = Modifier,
            title = "防御卡",
            summary = "分解：" + cardDecomposeData[0].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardDecomposeData[1].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardDecomposeData[2].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardDecomposeData[3].let { if (it == 0) "\uD83D\uDEAB" else it } + "\n" +
                    "兑换：" + cardGetData[0].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardGetData[1].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardGetData[2].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardGetData[3].let { if (it == 0) "\uD83D\uDEAB" else it },
            endActions = {
                ImagesRow(
                    imageResIds = listOf(
                        cardDecomposeAndGetImages[0],
                        cardDecomposeAndGetImages[1],
                        cardDecomposeAndGetImages[2],
                        cardDecomposeAndGetImages[3],
                    ),
                    imageWidth = 32,
                    imageHeight = 40,
                )
            },
        )
    }
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
        pressFeedbackType = PressFeedbackType.Sink,
        showIndication = true,
    ) {
        BasicComponent(
            modifier = Modifier,
            title = "技能书",
            summary = "分解：" + cardDecomposeData[4].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardDecomposeData[5].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardDecomposeData[6].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardDecomposeData[7].let { if (it == 0) "\uD83D\uDEAB" else it } + "\n" +
                    "兑换：" + cardGetData[4].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardGetData[5].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardGetData[6].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardGetData[7].let { if (it == 0) "\uD83D\uDEAB" else it },
            endActions = {
                ImagesRow(
                    imageResIds = listOf(
                        cardDecomposeAndGetImages[4],
                        cardDecomposeAndGetImages[5],
                        cardDecomposeAndGetImages[6],
                        cardDecomposeAndGetImages[7],
                    ),
                    imageWidth = 33,
                    imageHeight = 33,
                )
            },
        )
    }
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
        pressFeedbackType = PressFeedbackType.Sink,
        showIndication = true,
    ) {
        BasicComponent(
            modifier = Modifier,
            title = "三转凭证",
            summary = "分解：" + cardDecomposeData[8].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardDecomposeData[9].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardDecomposeData[10].let { if (it == 0) "\uD83D\uDEAB" else it } + "\n" +
                    "兑换：" + cardGetData[8].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardGetData[9].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardGetData[10].let { if (it == 0) "\uD83D\uDEAB" else it },
            endActions = {
                ImagesRow(
                    imageResIds = listOf(
                        cardDecomposeAndGetImages[8],
                        cardDecomposeAndGetImages[9],
                        cardDecomposeAndGetImages[10],
                    ),
                    imageWidth = 44,
                    imageHeight = 44,
                )
            },
        )
    }
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
        pressFeedbackType = PressFeedbackType.Sink,
        showIndication = true,
    ) {
        BasicComponent(
            modifier = Modifier,
            title = "四转凭证",
            summary = "分解：" + cardDecomposeData[11].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardDecomposeData[12].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardDecomposeData[13].let { if (it == 0) "\uD83D\uDEAB" else it } + "\n" +
                    "兑换：" + cardGetData[11].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardGetData[12].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardGetData[13].let { if (it == 0) "\uD83D\uDEAB" else it },
            endActions = {
                ImagesRow(
                    imageResIds = listOf(
                        cardDecomposeAndGetImages[11],
                        cardDecomposeAndGetImages[12],
                        cardDecomposeAndGetImages[13],
                    ),
                    imageWidth = 44,
                    imageHeight = 44,
                )
            },
        )
    }
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
        pressFeedbackType = PressFeedbackType.Sink,
        showIndication = true,
    ) {
        BasicComponent(
            modifier = Modifier,
            title = "终转凭证",
            summary = "分解：" + cardDecomposeData[14].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardDecomposeData[15].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardDecomposeData[16].let { if (it == 0) "\uD83D\uDEAB" else it } + "\n" +
                    "兑换：" + cardGetData[14].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardGetData[15].let { if (it == 0) "\uD83D\uDEAB" else it } + "、" +
                    cardGetData[16].let { if (it == 0) "\uD83D\uDEAB" else it },
            endActions = {
                ImagesRow(
                    imageResIds = listOf(
                        cardDecomposeAndGetImages[14],
                        cardDecomposeAndGetImages[15],
                        cardDecomposeAndGetImages[16],
                    ),
                    imageWidth = 44,
                    imageHeight = 44,
                )
            },
        )
    }
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
        pressFeedbackType = PressFeedbackType.Sink,
        showIndication = true,
    ) {
        BasicComponent(
            modifier = Modifier,
            title = "进化凭证",
            summary = "分解：" + cardDecomposeData[17].let { if (it == 0) "\uD83D\uDEAB" else it } + "\n" +
                    "兑换：" + cardGetData[17].let { if (it == 0) "\uD83D\uDEAB" else it },
            endActions = {
                ImagesRow(
                    imageResIds = listOf(
                        cardDecomposeAndGetImages[17],
                    ),
                    imageWidth = 44,
                    imageHeight = 44,
                )
            },
        )
    }
}

@Composable
fun GoldenCardDecomposeAndGetCalculator(
    showCardDecomposeAndGetCalculatorBottomSheet: MutableState<Boolean>,
    cardDecomposeAndGetImages: List<Int>,
    cardDecomposeData: List<Int>,
    cardGetData: List<Int>,
    itemName: String
) {
    val appState = LocalAppState.current
    val colorMode = appState.colorMode
    val darkMode = getDarkMode(colorMode)

    val density = LocalDensity.current
    val haptic = LocalHapticFeedback.current
    val exchangeChecked = rememberSaveable { mutableStateOf(false) }
    val state = rememberSaveable {
        mutableStateListOf(
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
            ToggleableState.Off,
        )
    }

    WindowBottomSheet(
        show = showCardDecomposeAndGetCalculatorBottomSheet.value,
        backgroundColor = MiuixTheme.colorScheme.surface,
        title = "分解兑换计算器",
        insideMargin = DpSize(width = 0.dp, height = 0.dp),
        enableNestedScroll = false,
        onDismissRequest = { showCardDecomposeAndGetCalculatorBottomSheet.value = false }
    ) {
        CompositionLocalProvider(LocalDensity provides density) {
            Card(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
                colors = CardDefaults.defaultColors(
                    color = when {
                        (colorMode in 3..5) -> MiuixTheme.colorScheme.secondaryContainer
                        (darkMode) -> Color(0xFF1A3825)
                        else -> Color(0xFFDFFAE4)
                    }
                )
            ) {
                SuperSwitch(
                    title = "切换开关",
                    summary = "关闭计算分解数量，打开计算兑换数量\n没显示的物品代表不可分解/兑换",
                    checked = exchangeChecked.value,
                    onCheckedChange = { checked ->
                        exchangeChecked.value = checked
                    },
                    switchColors = SwitchDefaults.switchColors(
                        checkedTrackColor = when { (colorMode in 3..5) -> MiuixTheme.colorScheme.primary else -> Color(0xFF36D167) },
                    )
                )
                AnimatedVisibility(!exchangeChecked.value) {
                    BasicComponent(
                        modifier = Modifier,
                        onClick = {  },
                    ) {
                        Text(
                            text = buildAnnotatedString{
                                append("分解勾选的物品可获得 ")
                                withStyle(
                                    style = SpanStyle(
                                        color = when { (colorMode in 3..5) -> MiuixTheme.colorScheme.primary else -> Color(0xFF36D167) },
                                        fontWeight = FontWeight.Bold,
                                    )
                                ) {
                                    append((
                                            (if (state[0] == ToggleableState.On) cardDecomposeData[0] else 0) +
                                                    (if (state[1] == ToggleableState.On) cardDecomposeData[1] else 0) +
                                                    (if (state[2] == ToggleableState.On) cardDecomposeData[2] else 0) +
                                                    (if (state[3] == ToggleableState.On) cardDecomposeData[3] else 0) +
                                                    (if (state[4] == ToggleableState.On) cardDecomposeData[4] else 0) +
                                                    (if (state[5] == ToggleableState.On) cardDecomposeData[5] else 0) +
                                                    (if (state[6] == ToggleableState.On) cardDecomposeData[6] else 0) +
                                                    (if (state[7] == ToggleableState.On) cardDecomposeData[7] else 0) +
                                                    (if (state[8] == ToggleableState.On) cardDecomposeData[8] else 0) +
                                                    (if (state[9] == ToggleableState.On) cardDecomposeData[9] else 0) +
                                                    (if (state[10] == ToggleableState.On) cardDecomposeData[10] else 0) +
                                                    (if (state[11] == ToggleableState.On) cardDecomposeData[11] else 0) +
                                                    (if (state[12] == ToggleableState.On) cardDecomposeData[12] else 0) +
                                                    (if (state[13] == ToggleableState.On) cardDecomposeData[13] else 0) +
                                                    (if (state[14] == ToggleableState.On) cardDecomposeData[14] else 0) +
                                                    (if (state[15] == ToggleableState.On) cardDecomposeData[15] else 0) +
                                                    (if (state[16] == ToggleableState.On) cardDecomposeData[16] else 0) +
                                                    (if (state[17] == ToggleableState.On) cardDecomposeData[17] else 0)
                                            ).toString()
                                    )
                                }
                                append(" 个$itemName")
                            },
                            style = MiuixTheme.textStyles.headline1,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                AnimatedVisibility(exchangeChecked.value) {
                    BasicComponent(
                        modifier = Modifier,
                        onClick = {  },
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                append("兑换勾选的物品需消耗 ")
                                withStyle(
                                    style = SpanStyle(
                                        color = when { (colorMode in 3..5) -> MiuixTheme.colorScheme.primary else -> Color(0xFF36D167) },
                                        fontWeight = FontWeight.Bold
                                    )
                                ) {
                                    append((
                                            (if (state[0] == ToggleableState.On) cardGetData[0] else 0) +
                                                    (if (state[1] == ToggleableState.On) cardGetData[1] else 0) +
                                                    (if (state[2] == ToggleableState.On) cardGetData[2] else 0) +
                                                    (if (state[3] == ToggleableState.On) cardGetData[3] else 0) +
                                                    (if (state[4] == ToggleableState.On) cardGetData[4] else 0) +
                                                    (if (state[5] == ToggleableState.On) cardGetData[5] else 0) +
                                                    (if (state[6] == ToggleableState.On) cardGetData[6] else 0) +
                                                    (if (state[7] == ToggleableState.On) cardGetData[7] else 0) +
                                                    (if (state[8] == ToggleableState.On) cardGetData[8] else 0) +
                                                    (if (state[9] == ToggleableState.On) cardGetData[9] else 0) +
                                                    (if (state[10] == ToggleableState.On) cardGetData[10] else 0) +
                                                    (if (state[11] == ToggleableState.On) cardGetData[11] else 0) +
                                                    (if (state[12] == ToggleableState.On) cardGetData[12] else 0) +
                                                    (if (state[13] == ToggleableState.On) cardGetData[13] else 0) +
                                                    (if (state[14] == ToggleableState.On) cardGetData[14] else 0) +
                                                    (if (state[15] == ToggleableState.On) cardGetData[15] else 0) +
                                                    (if (state[16] == ToggleableState.On) cardGetData[16] else 0) +
                                                    (if (state[17] == ToggleableState.On) cardGetData[17] else 0)
                                            ).toString()
                                    )
                                }
                                append(" 个$itemName")
                            },
                            style = MiuixTheme.textStyles.headline1,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            LazyColumn(
                modifier = Modifier
                    .scrollEndHaptic()
                    .overScrollVertical()
                    .fillMaxSize(),
                overscrollEffect = null,
            ) {
                item {
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[0] != 0 || exchangeChecked.value && cardGetData[0] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[0] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[0] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[0] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[0],
                                    onClick = {
                                        if (state[0] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[0] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[0] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "卡片本体",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[0]),
                                            imageWidth = 40,
                                            imageHeight = 50,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[1] != 0 || exchangeChecked.value && cardGetData[1] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[1] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[1] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[1] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[1],
                                    onClick = {
                                        if (state[1] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[1] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[1] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "卡片三转",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[1]),
                                            imageWidth = 40,
                                            imageHeight = 50,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[2] != 0 || exchangeChecked.value && cardGetData[2] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[2] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[2] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[2] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[2],
                                    onClick = {
                                        if (state[2] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[2] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[2] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "卡片四转",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[2]),
                                            imageWidth = 40,
                                            imageHeight = 50,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[3] != 0 || exchangeChecked.value && cardGetData[3] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[3] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[3] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[3] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[3],
                                    onClick = {
                                        if (state[3] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[3] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[3] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "卡片终转",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[3]),
                                            imageWidth = 44,
                                            imageHeight = 44,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[4] != 0 || exchangeChecked.value && cardGetData[4] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[4] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[4] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[4] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[4],
                                    onClick = {
                                        if (state[4] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[4] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[4] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "初级技能书",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[4]),
                                            imageWidth = 44,
                                            imageHeight = 44,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[5] != 0 || exchangeChecked.value && cardGetData[5] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[5] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[5] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[5] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[5],
                                    onClick = {
                                        if (state[5] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[5] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[5] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "高极技能书",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[5]),
                                            imageWidth = 44,
                                            imageHeight = 44,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[6] != 0 || exchangeChecked.value && cardGetData[6] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[6] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[6] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[6] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[6],
                                    onClick = {
                                        if (state[6] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[6] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[6] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "终极技能书",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[6]),
                                            imageWidth = 44,
                                            imageHeight = 44,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[7] != 0 || exchangeChecked.value && cardGetData[7] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[7] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[7] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[7] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[7],
                                    onClick = {
                                        if (state[7] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[7] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[7] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "究极技能书",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[7]),
                                            imageWidth = 44,
                                            imageHeight = 44,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[8] != 0 || exchangeChecked.value && cardGetData[8] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[8] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[8] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[8] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[8],
                                    onClick = {
                                        if (state[8] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[8] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[8] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "三转凭证A",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[8]),
                                            imageWidth = 44,
                                            imageHeight = 44,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[9] != 0 || exchangeChecked.value && cardGetData[9] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[9] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[9] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[9] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[9],
                                    onClick = {
                                        if (state[9] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[9] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[9] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "三转凭证B",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[9]),
                                            imageWidth = 44,
                                            imageHeight = 44,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[10] != 0 || exchangeChecked.value && cardGetData[10] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[10] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[10] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[10] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[10],
                                    onClick = {
                                        if (state[10] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[10] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[10] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "三转凭证C",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[10]),
                                            imageWidth = 44,
                                            imageHeight = 44,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[11] != 0 || exchangeChecked.value && cardGetData[11] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[11] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[11] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[11] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[11],
                                    onClick = {
                                        if (state[11] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[11] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[11] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "四转凭证A",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[11]),
                                            imageWidth = 44,
                                            imageHeight = 44,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[12] != 0 || exchangeChecked.value && cardGetData[12] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[12] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[12] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[12] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[12],
                                    onClick = {
                                        if (state[12] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[12] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[12] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "四转凭证B",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[12]),
                                            imageWidth = 44,
                                            imageHeight = 44,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[13] != 0 || exchangeChecked.value && cardGetData[13] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[13] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[13] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[13] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[13],
                                    onClick = {
                                        if (state[13] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[13] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[13] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "四转凭证C",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[13]),
                                            imageWidth = 44,
                                            imageHeight = 44,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[14] != 0 || exchangeChecked.value && cardGetData[14] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[14] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[14] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[14] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[14],
                                    onClick = {
                                        if (state[14] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[14] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[14] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "终转凭证A",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[14]),
                                            imageWidth = 44,
                                            imageHeight = 44,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[15] != 0 || exchangeChecked.value && cardGetData[15] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[15] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[15] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[15] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[15],
                                    onClick = {
                                        if (state[15] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[15] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[15] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "终转凭证B",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[15]),
                                            imageWidth = 44,
                                            imageHeight = 44,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[16] != 0 || exchangeChecked.value && cardGetData[16] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[16] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[16] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[16] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[16],
                                    onClick = {
                                        if (state[16] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[16] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[16] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "终转凭证C",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[16]),
                                            imageWidth = 44,
                                            imageHeight = 44,
                                        )
                                    },
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        !exchangeChecked.value && cardDecomposeData[17] != 0 || exchangeChecked.value && cardGetData[17] != 0
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                            pressFeedbackType = PressFeedbackType.Sink,
                            showIndication = true,
                            onClick = {
                                if (state[17] == ToggleableState.On) {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                    state[17] = ToggleableState.Off
                                } else {
                                    haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                    state[17] = ToggleableState.On
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    state = state[17],
                                    onClick = {
                                        if (state[17] == ToggleableState.On) {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            state[17] = ToggleableState.Off
                                        } else {
                                            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            state[17] = ToggleableState.On
                                        }
                                    }
                                )
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "进化凭证",
                                    summary = "勾选以加入计算",
                                    endActions = {
                                        ImagesRow(
                                            imageResIds = listOf(cardDecomposeAndGetImages[17]),
                                            imageWidth = 44,
                                            imageHeight = 44,
                                        )
                                    },
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.padding(bottom = 48.dp))
                }
            }
        }
    }
}
