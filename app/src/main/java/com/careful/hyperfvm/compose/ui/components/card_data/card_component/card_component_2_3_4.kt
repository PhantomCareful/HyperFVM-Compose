package com.careful.hyperfvm.compose.ui.components.card_data.card_component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.careful.hyperfvm.compose.LocalAppState
import com.careful.hyperfvm.compose.R
import com.careful.hyperfvm.compose.card_data_detail.DeveloperTips
import com.careful.hyperfvm.compose.card_data_detail.SKILL_POINT_1
import com.careful.hyperfvm.compose.ui.components.card_data.ImagesRow
import com.careful.hyperfvm.compose.ui.theme.getDarkMode
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.RangeSlider
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.SliderDefaults
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.TabRow
import top.yukonga.miuix.kmp.basic.TabRowDefaults
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.window.WindowBottomSheet
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Back
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.PressFeedbackType
import top.yukonga.miuix.kmp.utils.overScrollVertical
import top.yukonga.miuix.kmp.utils.scrollEndHaptic
import kotlin.math.roundToInt

@Stable
private val CARD_NAMES = listOf(
    R.string.name_card_data_index_2_3_4_0,
    R.string.name_card_data_index_2_3_4_1,
    R.string.name_card_data_index_2_3_4_2
)

@Stable
private val CARD_IMAGES = listOf(
    R.drawable.card_data_index_2_3_4_0,
    R.drawable.card_data_index_2_3_4_1,
    R.drawable.card_data_index_2_3_4_2
)

@Stable
private val CARD_IMAGES_BIG = listOf(
    R.drawable.card_data_index_2_3_4_0_big,
    R.drawable.card_data_index_2_3_4_1_big,
    R.drawable.card_data_index_2_3_4_2_big
)

@Stable
private val CARD_DESCRIPTION = listOf(
    "能力：投掷带有伤害的蛋糕子弹，以及有燃烧效果的蜡烛子弹",
    "能力：子弹威力提升50%",
    "能力：几率投掷可乐炸弹",
    "50",
    "7",
    "所属分类：投掷类/小型投手",
    "耗能：150",
    "作为副卡：中卡\uD83D\uDE2D",
    "1️⃣子弹数量：1\n2️⃣第3n次攻击造成3次1×1范围50%攻击力灼烧，间隔1秒",
)

@Stable
private val RELATED_CARD_NAMES = listOf(
    R.string.name_card_data_index_12_1_4_0,
    R.string.name_card_data_index_12_1_4_1,
    R.string.name_card_data_index_12_1_4_2,
)

@Stable
private val RELATED_CARD_IMAGES = listOf(
    R.drawable.card_data_index_12_1_4_0,
    R.drawable.card_data_index_12_1_4_1,
    R.drawable.card_data_index_12_1_4_2,
)

@Stable
private val CARD_DATA_STRING = listOf(
    "不转攻击力", "一转攻击力"
)

@Stable
private val CARD_DATA_1 = listOf(
    45, 55, 65, 70, 90, 180, 140, 170, 200, 230, 280, 360, 450, 550, 660, 780, 900, -1, -1
)

@Stable
private val CARD_DATA_2 = listOf(
    60, 74, 87, 94, 121, 243, 189, 229, 270, 310, 378, 486, 607, 742, 891, 1053, 1215, -1, -1
)

@Stable
private val CARD_DATA_SKILL_IMAGES = listOf(
    R.drawable.card_data_index_2_3_4_skill_1,
    R.drawable.card_data_index_2_3_4_skill_2,
    R.drawable.card_data_index_2_3_4_skill_3,
    R.drawable.card_data_index_2_3_4_skill_4
)

@Stable
private val CARD_DATA_SKILL_STRING = listOf(
    "攻击间隔"
)

@Stable
private val CARD_DATA_SKILL_1 = listOf(
    2f, 1.95f, 1.9f, 1.85f, 1.8f, 1.75f, 1.7f, 1.65f, 1.5f
)

@Stable
@Composable
fun CardComponent_2_3_4() {
    val showBottomSheet = rememberSaveable { mutableStateOf(false) }
    val cardName = stringResource(CARD_NAMES[0])

    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
        pressFeedbackType = PressFeedbackType.Sink,
    ) {
        BasicComponent(
            modifier = Modifier,
            title = cardName,
            summary = stringResource(CARD_NAMES[1]) + "-" + stringResource(CARD_NAMES[2]),
            endActions = {
                ImagesRow(
                    imageResIds = CARD_IMAGES,
                    imageWidth = 40,
                    imageHeight = 50,
                )
            },
            onClick = { showBottomSheet.value = true },
        )
    }

    CardDataDetailWindowBottomSheet_2_3_4(showBottomSheet, cardName)
}

@Composable
fun CardDataDetailWindowBottomSheet_2_3_4(
    showBottomSheet: MutableState<Boolean>,
    cardName: String
) {
    val density = LocalDensity.current

    WindowBottomSheet(
        show = showBottomSheet.value,
        backgroundColor = MiuixTheme.colorScheme.surface,
        title = cardName,
        insideMargin = DpSize(width = 0.dp, height = 0.dp),
        enableNestedScroll = false,
        onDismissRequest = { showBottomSheet.value = false },
        startAction = {
            IconButton(
                modifier = Modifier.padding(start = 12.dp),
                onClick = { showBottomSheet.value = false },
            ) {
                Icon(
                    imageVector = MiuixIcons.Heavy.Back,
                    contentDescription = "关闭详情页",
                    tint = MiuixTheme.colorScheme.onBackground,
                )
            }
        },
    ) {
        CompositionLocalProvider(LocalDensity provides density) {
            Info()
        }
    }
}

@Composable
private fun Info() {
    // =================================================== 所有变量 ===================================================

    val showBottomSheet1 = rememberSaveable { mutableStateOf(false) }

    val tabs = listOf("单个星级", "一些星级")
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    var stepsWithKeyPointsValue by rememberSaveable { mutableIntStateOf(12) }
    var rangeStepsFirstValue by rememberSaveable { mutableIntStateOf(9) }
    var rangeStepsLastValue by rememberSaveable { mutableIntStateOf(16) }

    LazyColumn(
        modifier = Modifier
            .scrollEndHaptic()
            .overScrollVertical()
            .fillMaxSize(),
        overscrollEffect = null,
    ) {
        item {
            // =================================================== 基础信息 ===================================================

            BaseInfoWithBigImage(
                cardImagesBig = CARD_IMAGES_BIG,
                cardImages = CARD_IMAGES,
                cardNames = CARD_NAMES,
                cardDescription = CARD_DESCRIPTION,
            )

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val appState = LocalAppState.current
                val colorMode = appState.colorMode
                val darkMode = getDarkMode(colorMode)

                Card(
                    modifier = Modifier.weight(1f),
                    pressFeedbackType = PressFeedbackType.Tilt,
                    showIndication = true,
                    colors = CardDefaults.defaultColors(
                        color = if (CARD_DESCRIPTION[3] != "50") {
                            when {
                                (colorMode in 3..5) -> MiuixTheme.colorScheme.secondaryContainer
                                (darkMode) -> Color(0xFF1A3825)
                                else -> Color(0xFFDFFAE4)
                            }
                        } else {
                            MiuixTheme.colorScheme.surfaceContainer
                        }
                    ),
                    onLongPress = {  }
                ) {
                    BasicComponent(
                        modifier = Modifier,
                        title = "体力：" + CARD_DESCRIPTION[3],
                    )
                }
                Card(
                    modifier = Modifier.weight(1f),
                    pressFeedbackType = PressFeedbackType.Tilt,
                    showIndication = true,
                    colors = CardDefaults.defaultColors(
                        color = if (CARD_DESCRIPTION[4] != "7") {
                            when {
                                (colorMode in 3..5) -> MiuixTheme.colorScheme.secondaryContainer
                                (darkMode) -> Color(0xFF1A3825)
                                else -> Color(0xFFDFFAE4)
                            }
                        } else {
                            MiuixTheme.colorScheme.surfaceContainer
                        }
                    ),
                    onLongPress = {  }
                ) {
                    BasicComponent(
                        modifier = Modifier,
                        title = "冷却：" + CARD_DESCRIPTION[4] + "秒",
                    )
                }
            }

            BaseInfoCommonCard(CARD_DESCRIPTION, 5)

            Card(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
            ) {
                BasicComponent(
                    modifier = Modifier,
                    endActions = {
                        ImagesRow(
                            imageResIds = listOf(R.drawable.card_data_index_2_3_4_transfer_box),
                            imageWidth = 40,
                            imageHeight = 50,
                        )
                    },
                    onClick = {  },
                ) {
                    Text(
                        text = "转职凭证",
                        style = MiuixTheme.textStyles.headline1,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = buildAnnotatedString {
                            append("可在商城限时抢购中购买")
                            withStyle(style = SpanStyle(color = MiuixTheme.colorScheme.primary, fontWeight = FontWeight.Bold)) {
                                append("8周年蛋糕转职箱")
                            }
                            append("，有机会获得其转职凭证")
                        },
                        style = MiuixTheme.textStyles.body2,
                        color = MiuixTheme.colorScheme.onSurfaceVariantSummary
                    )
                }
            }
        }
        item {
            // =================================================== 人话解释 ===================================================

            SmallTitle(text = "人话解释")
            Card(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
            ) {
                BasicComponent(
                    modifier = Modifier,
                    title = CARD_DESCRIPTION[8],
                    onClick = {  },
                )
            }
        }
        item {
            // =================================================== 相关卡片 ===================================================

            //CardDataDetailWindowBottomSheet_12_1_4(showBottomSheet1, stringResource(R.string.name_card_data_index_12_1_4_0))

            SmallTitle(text = "相关卡片")
            Card(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
            ) {
                BasicComponent(
                    modifier = Modifier,
                    title = stringResource(RELATED_CARD_NAMES[0]),
                    summary = "可作为此卡片的底座",
                    endActions = {
                        ImagesRow(
                            imageResIds = listOf(
                                RELATED_CARD_IMAGES[0],
                                RELATED_CARD_IMAGES[1],
                                RELATED_CARD_IMAGES[2],
                            ),
                            imageWidth = 40,
                            imageHeight = 50,
                        )
                    },
                    onClick = { showBottomSheet1.value = true },
                )
            }
        }
        item {
            // =================================================== 星级数据 ===================================================

            SmallTitle(text = "强化提升：" + CARD_DATA_STRING[0] + "、" + CARD_DATA_STRING[1])
            Card(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
            ) {
                Column {
                    TabRow(
                        modifier = Modifier.padding(12.dp),
                        colors = TabRowDefaults.tabRowColors(
                            backgroundColor = MiuixTheme.colorScheme.surfaceContainer,
                            selectedBackgroundColor = MiuixTheme.colorScheme.surface,
                        ),
                        tabs = tabs,
                        selectedTabIndex = selectedTabIndex,
                        onTabSelected = { selectedTabIndex = it }
                    )
                    AnimatedVisibility(visible = selectedTabIndex == 0) {
                        Slider(
                            value = stepsWithKeyPointsValue.toFloat(),
                            onValueChange = { stepsWithKeyPointsValue = it.roundToInt() },
                            valueRange = 0f..16f,
                            steps = 15,
                            hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                            showKeyPoints = true,
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                        )
                    }
                    AnimatedVisibility(visible = selectedTabIndex == 1) {
                        RangeSlider(
                            value = rangeStepsFirstValue.toFloat()..rangeStepsLastValue.toFloat(),
                            onValueChange = { floatRange ->
                                rangeStepsFirstValue = floatRange.start.roundToInt()
                                rangeStepsLastValue = floatRange.endInclusive.roundToInt()
                            },
                            valueRange = 0f..16f,
                            steps = 15,
                            hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                            showKeyPoints = true,
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                        )
                    }
                }
            }

            AnimatedVisibility(
                visible =
                    selectedTabIndex == 0 && stepsWithKeyPointsValue == 0 || selectedTabIndex == 1 && rangeStepsFirstValue <= 0 && rangeStepsLastValue >= 0
            ) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                    pressFeedbackType = PressFeedbackType.Sink,
                    showIndication = true,
                ) {
                    BasicComponent(
                        modifier = Modifier,
                        title = "LV.0",
                        summary = CARD_DATA_STRING[0] + "：" + CARD_DATA_1[0] + "\n"
                                + CARD_DATA_STRING[1] + "：" + CARD_DATA_2[0],
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex == 0 && stepsWithKeyPointsValue == 1 || selectedTabIndex == 1 && rangeStepsFirstValue <= 1 && rangeStepsLastValue >= 1
            ) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                    pressFeedbackType = PressFeedbackType.Sink,
                    showIndication = true,
                ) {
                    BasicComponent(
                        modifier = Modifier,
                        title = CARD_DATA_STRING[0] + "：" + CARD_DATA_1[1] + "\n"
                                + CARD_DATA_STRING[1] + "：" + CARD_DATA_2[1],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.star_image_1),
                                imageWidth = 40,
                                imageHeight = 50,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex == 0 && stepsWithKeyPointsValue == 2 || selectedTabIndex == 1 && rangeStepsFirstValue <= 2 && rangeStepsLastValue >= 2
            ) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                    pressFeedbackType = PressFeedbackType.Sink,
                    showIndication = true,
                ) {
                    BasicComponent(
                        modifier = Modifier,
                        title = CARD_DATA_STRING[0] + "：" + CARD_DATA_1[2] + "\n"
                                + CARD_DATA_STRING[1] + "：" + CARD_DATA_2[2],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.star_image_2),
                                imageWidth = 40,
                                imageHeight = 50,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex == 0 && stepsWithKeyPointsValue == 3 || selectedTabIndex == 1 && rangeStepsFirstValue <= 3 && rangeStepsLastValue >= 3
            ) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                    pressFeedbackType = PressFeedbackType.Sink,
                    showIndication = true,
                ) {
                    BasicComponent(
                        modifier = Modifier,
                        title = CARD_DATA_STRING[0] + "：" + CARD_DATA_1[3] + "\n"
                                + CARD_DATA_STRING[1] + "：" + CARD_DATA_2[3],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.star_image_3),
                                imageWidth = 40,
                                imageHeight = 50,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex == 0 && stepsWithKeyPointsValue == 4 || selectedTabIndex == 1 && rangeStepsFirstValue <= 4 && rangeStepsLastValue >= 4
            ) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                    pressFeedbackType = PressFeedbackType.Sink,
                    showIndication = true,
                ) {
                    BasicComponent(
                        modifier = Modifier,
                        title = CARD_DATA_STRING[0] + "：" + CARD_DATA_1[4] + "\n"
                                + CARD_DATA_STRING[1] + "：" + CARD_DATA_2[4],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.star_image_4),
                                imageWidth = 40,
                                imageHeight = 50,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex == 0 && stepsWithKeyPointsValue == 5 || selectedTabIndex == 1 && rangeStepsFirstValue <= 5 && rangeStepsLastValue >= 5
            ) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                    pressFeedbackType = PressFeedbackType.Sink,
                    showIndication = true,
                ) {
                    BasicComponent(
                        modifier = Modifier,
                        title = CARD_DATA_STRING[0] + "：" + CARD_DATA_1[5] + "\n"
                                + CARD_DATA_STRING[1] + "：" + CARD_DATA_2[5],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.star_image_5),
                                imageWidth = 40,
                                imageHeight = 50,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex == 0 && stepsWithKeyPointsValue == 6 || selectedTabIndex == 1 && rangeStepsFirstValue <= 6 && rangeStepsLastValue >= 6
            ) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                    pressFeedbackType = PressFeedbackType.Sink,
                    showIndication = true,
                ) {
                    BasicComponent(
                        modifier = Modifier,
                        title = CARD_DATA_STRING[0] + "：" + CARD_DATA_1[6] + "\n"
                                + CARD_DATA_STRING[1] + "：" + CARD_DATA_2[6],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.star_image_6),
                                imageWidth = 40,
                                imageHeight = 50,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex == 0 && stepsWithKeyPointsValue == 7 || selectedTabIndex == 1 && rangeStepsFirstValue <= 7 && rangeStepsLastValue >= 7
            ) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                    pressFeedbackType = PressFeedbackType.Sink,
                    showIndication = true,
                ) {
                    BasicComponent(
                        modifier = Modifier,
                        title = CARD_DATA_STRING[0] + "：" + CARD_DATA_1[7] + "\n"
                                + CARD_DATA_STRING[1] + "：" + CARD_DATA_2[7],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.star_image_7),
                                imageWidth = 40,
                                imageHeight = 50,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex == 0 && stepsWithKeyPointsValue == 8 || selectedTabIndex == 1 && rangeStepsFirstValue <= 8 && rangeStepsLastValue >= 8
            ) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                    pressFeedbackType = PressFeedbackType.Sink,
                    showIndication = true,
                ) {
                    BasicComponent(
                        modifier = Modifier,
                        title = CARD_DATA_STRING[0] + "：" + CARD_DATA_1[8] + "\n"
                                + CARD_DATA_STRING[1] + "：" + CARD_DATA_2[8],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.star_image_8),
                                imageWidth = 40,
                                imageHeight = 50,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex == 0 && stepsWithKeyPointsValue == 9 || selectedTabIndex == 1 && rangeStepsFirstValue <= 9 && rangeStepsLastValue >= 9
            ) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                    pressFeedbackType = PressFeedbackType.Sink,
                    showIndication = true,
                ) {
                    BasicComponent(
                        modifier = Modifier,
                        title = CARD_DATA_STRING[0] + "：" + CARD_DATA_1[9] + "\n"
                                + CARD_DATA_STRING[1] + "：" + CARD_DATA_2[9],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.star_image_9),
                                imageWidth = 40,
                                imageHeight = 50,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex == 0 && stepsWithKeyPointsValue == 10 || selectedTabIndex == 1 && rangeStepsFirstValue <= 10 && rangeStepsLastValue >= 10
            ) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                    pressFeedbackType = PressFeedbackType.Sink,
                    showIndication = true,
                ) {
                    BasicComponent(
                        modifier = Modifier,
                        title = CARD_DATA_STRING[0] + "：" + CARD_DATA_1[10] + "\n"
                                + CARD_DATA_STRING[1] + "：" + CARD_DATA_2[10],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.star_image_10),
                                imageWidth = 40,
                                imageHeight = 50,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex == 0 && stepsWithKeyPointsValue == 11 || selectedTabIndex == 1 && rangeStepsFirstValue <= 11 && rangeStepsLastValue >= 11
            ) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                    pressFeedbackType = PressFeedbackType.Sink,
                    showIndication = true,
                ) {
                    BasicComponent(
                        modifier = Modifier,
                        title = CARD_DATA_STRING[0] + "：" + CARD_DATA_1[11] + "\n"
                                + CARD_DATA_STRING[1] + "：" + CARD_DATA_2[11],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.star_image_11),
                                imageWidth = 40,
                                imageHeight = 50,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex == 0 && stepsWithKeyPointsValue == 12 || selectedTabIndex == 1 && rangeStepsFirstValue <= 12 && rangeStepsLastValue >= 12
            ) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                    pressFeedbackType = PressFeedbackType.Sink,
                    showIndication = true,
                ) {
                    BasicComponent(
                        modifier = Modifier,
                        title = CARD_DATA_STRING[0] + "：" + CARD_DATA_1[12] + "\n"
                                + CARD_DATA_STRING[1] + "：" + CARD_DATA_2[12],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.star_image_12),
                                imageWidth = 40,
                                imageHeight = 50,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex == 0 && stepsWithKeyPointsValue == 13 || selectedTabIndex == 1 && rangeStepsFirstValue <= 13 && rangeStepsLastValue >= 13
            ) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                    pressFeedbackType = PressFeedbackType.Sink,
                    showIndication = true,
                ) {
                    BasicComponent(
                        modifier = Modifier,
                        title = CARD_DATA_STRING[0] + "：" + CARD_DATA_1[13] + "\n"
                                + CARD_DATA_STRING[1] + "：" + CARD_DATA_2[13],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.star_image_13),
                                imageWidth = 40,
                                imageHeight = 50,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex == 0 && stepsWithKeyPointsValue == 14 || selectedTabIndex == 1 && rangeStepsFirstValue <= 14 && rangeStepsLastValue >= 14
            ) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                    pressFeedbackType = PressFeedbackType.Sink,
                    showIndication = true,
                ) {
                    BasicComponent(
                        modifier = Modifier,
                        title = CARD_DATA_STRING[0] + "：" + CARD_DATA_1[14] + "\n"
                                + CARD_DATA_STRING[1] + "：" + CARD_DATA_2[14],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.star_image_14),
                                imageWidth = 40,
                                imageHeight = 50,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex == 0 && stepsWithKeyPointsValue == 15 || selectedTabIndex == 1 && rangeStepsFirstValue <= 15 && rangeStepsLastValue >= 15
            ) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                    pressFeedbackType = PressFeedbackType.Sink,
                    showIndication = true,
                ) {
                    BasicComponent(
                        modifier = Modifier,
                        title = CARD_DATA_STRING[0] + "：" + CARD_DATA_1[15] + "\n"
                                + CARD_DATA_STRING[1] + "：" + CARD_DATA_2[15],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.star_image_15),
                                imageWidth = 40,
                                imageHeight = 50,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex == 0 && stepsWithKeyPointsValue == 16 || selectedTabIndex == 1 && rangeStepsFirstValue <= 16 && rangeStepsLastValue >= 16
            ) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                    pressFeedbackType = PressFeedbackType.Sink,
                    showIndication = true,
                ) {
                    BasicComponent(
                        modifier = Modifier,
                        title = CARD_DATA_STRING[0] + "：" + CARD_DATA_1[16] + "\n"
                                + CARD_DATA_STRING[1] + "：" + CARD_DATA_2[16],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.star_image_16),
                                imageWidth = 40,
                                imageHeight = 50,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex == 0 && stepsWithKeyPointsValue == 17 || selectedTabIndex == 1 && rangeStepsFirstValue <= 17 && rangeStepsLastValue >= 17
            ) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                    pressFeedbackType = PressFeedbackType.Sink,
                    showIndication = true,
                ) {
                    BasicComponent(
                        modifier = Modifier,
                        title = "LV.MAX",
                        summary = CARD_DATA_STRING[0] + "：" + "NULL"
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex == 0 && stepsWithKeyPointsValue == 18 || selectedTabIndex == 1 && rangeStepsFirstValue <= 18 && rangeStepsLastValue >= 18
            ) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                    pressFeedbackType = PressFeedbackType.Sink,
                    showIndication = true,
                ) {
                    BasicComponent(
                        modifier = Modifier,
                        title = "LV.ULTRA",
                        summary = CARD_DATA_STRING[0] + "：" + "NULL"
                    )
                }
            }
        }
        item {
            // =================================================== 技能数据 ===================================================

            SmallTitle(text = "技能提升：" + CARD_DATA_SKILL_STRING[0])
            Card(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
                pressFeedbackType = PressFeedbackType.Sink,
                showIndication = true,
            ) {
                BasicComponent(
                    modifier = Modifier,
                    title = "LV.0：" +
                            "%.2f".format(CARD_DATA_SKILL_1[0]),
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
                    title = "LV.1-3：" +
                            "%.2f".format(CARD_DATA_SKILL_1[1]) + "、" +
                            "%.2f".format(CARD_DATA_SKILL_1[2]) + "、" +
                            "%.2f".format(CARD_DATA_SKILL_1[3]),
                    summary = "初级一共所需技能点：" + (SKILL_POINT_1[1] + SKILL_POINT_1[2] + SKILL_POINT_1[3]),
                    endActions = {
                        ImagesRow(
                            imageResIds = listOf(CARD_DATA_SKILL_IMAGES[0]),
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
                    title = "LV.4-5：" +
                            "%.2f".format(CARD_DATA_SKILL_1[4]) + "、" +
                            "%.2f".format(CARD_DATA_SKILL_1[5]),
                    summary = "高级一共所需技能点：" + (SKILL_POINT_1[4] + SKILL_POINT_1[5]),
                    endActions = {
                        ImagesRow(
                            imageResIds = listOf(CARD_DATA_SKILL_IMAGES[1]),
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
                    title = "LV.6-7：" +
                            "%.2f".format(CARD_DATA_SKILL_1[6]) + "、" +
                            "%.2f".format(CARD_DATA_SKILL_1[7]),
                    summary = "终级一共所需技能点：" + (SKILL_POINT_1[6] + SKILL_POINT_1[7]),
                    endActions = {
                        ImagesRow(
                            imageResIds = listOf(CARD_DATA_SKILL_IMAGES[2]),
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
                    title = "LV.8：" +
                            "%.2f".format(CARD_DATA_SKILL_1[8]),
                    summary = "究极一共所需技能点：" + (SKILL_POINT_1[8]),
                    endActions = {
                        ImagesRow(
                            imageResIds = listOf(CARD_DATA_SKILL_IMAGES[3]),
                            imageWidth = 40,
                            imageHeight = 50,
                        )
                    },
                )
            }
        }
        item {
            // =================================================== 其他信息 ===================================================

            SmallTitle("其他信息")

            Card(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
                pressFeedbackType = PressFeedbackType.Sink,
                showIndication = true,
            ) {
                BasicComponent(
                    modifier = Modifier,
                    onClick = {  },
                ) {
                    Text(
                        text = "附加说明\uD83E\uDD14",
                        style = MiuixTheme.textStyles.headline1,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = buildAnnotatedString {
                            append("1️⃣不吃增幅，附带击杀灰烬\n2️⃣")
                            withStyle(style = SpanStyle(color = MiuixTheme.colorScheme.primary, fontWeight = FontWeight.Bold)) {
                                append("二转后")
                            }
                            append("，第3、6、9次攻击造成同上方的灼烧，")
                            withStyle(style = SpanStyle(color = MiuixTheme.colorScheme.primary, fontWeight = FontWeight.Bold)) {
                                append("第10次攻击")
                            }
                            append("造成3×3范围范围900灰烬爆炸，以此循环")
                        },
                        style = MiuixTheme.textStyles.body2,
                        color = MiuixTheme.colorScheme.onSurfaceVariantSummary
                    )
                }
            }

            DeveloperTips()
        }
    }
}