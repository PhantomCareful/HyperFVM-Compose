package com.careful.hyperfvm.compose.ui.components.card_data.card_component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import top.yukonga.miuix.kmp.preference.SwitchPreference
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.All
import top.yukonga.miuix.kmp.icon.extended.Back
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.PressFeedbackType
import top.yukonga.miuix.kmp.utils.overScrollVertical
import top.yukonga.miuix.kmp.utils.scrollEndHaptic
import kotlin.math.roundToInt

@Stable
private val CARD_NAMES = listOf(
    R.string.name_card_data_index_16_1_4_1,
    R.string.name_card_data_index_16_1_4_2,
    R.string.name_card_data_index_16_1_4_3
)

@Stable
private val CARD_IMAGES =  listOf(
    R.drawable.card_data_index_16_1_4_1,
    R.drawable.card_data_index_16_1_4_2,
    R.drawable.card_data_index_16_1_4_3
)

@Stable
private val CARD_IMAGES_BIG =  listOf(
    R.drawable.card_data_index_16_1_4_1_big,
    R.drawable.card_data_index_16_1_4_2_big,
    R.drawable.card_data_index_16_1_4_3_big
)

@Stable
private val CARD_DESCRIPTION = listOf(
    "能力：伤害提高扩大到3×3范围",
    "能力：间隔一段时间，额外产生一次3×3爆炸",
    "能力：向前方3行投掷栗子子弹",
    "50",
    "7",
    "所属分类：杂项类/浮空三锅",
    "耗能：160",
    "作为副卡：好卡\uD83D\uDE00",
    "1️⃣总攻击力=主卡攻击力+初级附加攻击力\n2️⃣3×3边角伤害与十字保持一致",
    "1️⃣总攻击力=主卡攻击力+初级附加攻击力+融合附加攻击力\n2️⃣向-1、0、1行各投掷一发3×3的子弹，子弹属性独立可叠加"
)

@Stable
private val RELATED_CARD_NAMES = listOf(
    R.string.name_card_data_index_10_1_6_2,
    R.string.name_card_data_index_10_1_2_2,
    R.string.name_card_data_index_12_2_1_2,
    R.string.name_card_data_index_13_1_5_2
)

@Stable
private val RELATED_CARD_IMAGES = listOf(
    R.drawable.card_data_index_10_1_6_2,
    R.drawable.card_data_index_10_1_2_2,
    R.drawable.card_data_index_12_2_1_2,
    R.drawable.card_data_index_13_1_5_2
)

@Stable
private val CARD_DATA_STRING = listOf(
    "攻击力"
)

@Stable
private val CARD_DATA_1 = listOf(
    7, 8, 9, 13, 15, 19, 22, 26, 31, 36, 42, 48, 56, 64, 73, 81, 90, -1, -1
)

@Stable
private val CARD_DATA_FUSION_TITLE = listOf(
    "附加攻击力", "爆炸伤害、爆炸周期", "附加攻击力"
)

@Stable
private val CARD_DATA_FUSION_STRING = listOf(
    "初级融合-附加攻击力", "深度融合-爆炸伤害", "深度融合-爆炸周期", "灵魂融合-附加攻击力"
)

@Stable
private val CARD_DATA_FUSION_1 = listOf(
    -1, 2, 3, 3, 4, 5, 6, 7, 9, 10, 12, 14, 16, 19, 21, 24, 26, -1, -1
)

@Stable
private val CARD_DATA_FUSION_2 = listOf(
    -1, 900, 900, 900, 900, 900, 900, 900, 900, 900, 920, 930, 950, 970, 1000, 1100, 1200, -1, -1
)

@Stable
private val CARD_DATA_FUSION_3 = listOf(
    -1, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 6, 6, 5, 4, 3, -1, -1
)

@Stable
private val CARD_DATA_FUSION_4 = listOf(
    -1, 3, 3, 4, 5, 6, 7, 9, 10, 12, 14, 16, 19, 21, 24, 26, 30, -1, -1
)

@Stable
private val CARD_DATA_SKILL_IMAGES = listOf(
    R.drawable.card_data_index_10_1_6_skill_1,
    R.drawable.card_data_index_10_1_6_skill_2,
    R.drawable.card_data_index_10_1_6_skill_3,
    R.drawable.card_data_index_10_1_6_skill_4
)

@Stable
private val CARD_DATA_SKILL_STRING = listOf(
    "攻击间隔"
)

@Stable
private val CARD_DATA_SKILL_1 = listOf(
    3.8f, 3.7f, 3.5f, 3.3f, 3.1f, 2.9f, 2.7f, 2.5f, 2.2f
)

@Stable
@Composable
fun CardComponent_16_1_4() {
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

    CardDataDetailWindowBottomSheet_16_1_4(showBottomSheet, cardName)
}

@Composable
fun CardDataDetailWindowBottomSheet_16_1_4(
    showBottomSheet: MutableState<Boolean>,
    cardName: String
) {
    val density = LocalDensity.current
    val showCardAuxiliaryBottomSheet = rememberSaveable { mutableStateOf(false) }

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
        endAction = {
            IconButton(
                modifier = Modifier.padding(end = 12.dp),
                onClick = { showCardAuxiliaryBottomSheet.value = true },
            ) {
                Icon(
                    imageVector = MiuixIcons.Heavy.All,
                    contentDescription = "查看与设置卡片增益",
                    tint = MiuixTheme.colorScheme.onBackground,
                )
            }
        }
    ) {
        CompositionLocalProvider(LocalDensity provides density) {
            Info(showCardAuxiliaryBottomSheet)
        }
    }
}

@Composable
private fun Info(
    showCardAuxiliaryBottomSheet: MutableState<Boolean>
) {
    // =================================================== 所有变量 ===================================================
    val density = LocalDensity.current

    val showBottomSheet1 = rememberSaveable { mutableStateOf(false) }
    val showBottomSheet2 = rememberSaveable { mutableStateOf(false) }
    val showBottomSheet3 = rememberSaveable { mutableStateOf(false) }
    val showBottomSheet4 = rememberSaveable { mutableStateOf(false) }

    val fusionChecked1 = rememberSaveable { mutableStateOf(false) }
    val fusionChecked2 = rememberSaveable { mutableStateOf(false) }
    var fusionLevel by rememberSaveable { mutableIntStateOf(9) }
    var fusionValue1 by rememberSaveable { mutableIntStateOf(0) }
    var fusionValue2 by rememberSaveable { mutableIntStateOf(0) }

    val tabs1 = listOf("单个星级", "一些星级")
    var selectedTabIndex1 by rememberSaveable { mutableIntStateOf(0) }
    var stepsWithKeyPointsValue1 by rememberSaveable { mutableIntStateOf(12) }
    var rangeStepsFirstValue1 by rememberSaveable { mutableIntStateOf(9) }
    var rangeStepsLastValue1 by rememberSaveable { mutableIntStateOf(16) }

    val tabs2 = listOf("单个品阶", "一些品阶")
    var selectedTabIndex2 by rememberSaveable { mutableIntStateOf(0) }
    var stepsWithKeyPointsValue2 by rememberSaveable { mutableIntStateOf(9) }
    var rangeStepsFirstValue2 by rememberSaveable { mutableIntStateOf(9) }
    var rangeStepsLastValue2 by rememberSaveable { mutableIntStateOf(16) }

    LazyColumn(
        modifier = Modifier
            .scrollEndHaptic()
            .overScrollVertical()
            .fillMaxSize(),
        overscrollEffect = null,
    ) {
        item {
            // =================================================== 基础信息 ===================================================

            BaseInfoFusionCard(
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
                    title = "初级融合后",
                    summary = CARD_DESCRIPTION[8],
                    onClick = {  },
                )
                BasicComponent(
                    modifier = Modifier,
                    onClick = {  },
                ) {
                    Text(
                        text = "深度融合后",
                        style = MiuixTheme.textStyles.headline1,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = buildAnnotatedString {
                            append("每 ")
                            withStyle(style = SpanStyle(color = MiuixTheme.colorScheme.primary, fontWeight = FontWeight.Bold)) {
                                append("爆炸周期")
                            }
                            append(" 轮攻击子弹命中产生1次 ")
                            withStyle(style = SpanStyle(color = MiuixTheme.colorScheme.primary, fontWeight = FontWeight.Bold)) {
                                append("爆炸伤害")
                            }
                            append(" 同数值的爆炸(优先900灰烬伤害，溢出值造成物理伤害+击杀灰烬)")
                        },
                        style = MiuixTheme.textStyles.body2,
                        color = MiuixTheme.colorScheme.onSurfaceVariantSummary
                    )
                }
                BasicComponent(
                    modifier = Modifier,
                    title = "灵魂融合后",
                    summary = CARD_DESCRIPTION[9],
                    onClick = {  },
                )
            }
        }
        item {
            // =================================================== 相关卡片 ===================================================

            SmallTitle(text = "相关卡片")
            //CardDataDetailWindowBottomSheet_1_1_1(showBottomSheet3, stringResource(R.string.name_card_data_index_1_1_1_0))

            Card(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
            ) {
                BasicComponent(
                    modifier = Modifier,
                    title = stringResource(RELATED_CARD_NAMES[0]),
                    summary = "本卡片是初级融合此卡片的必要素材",
                    endActions = {
                        ImagesRow(
                            imageResIds = listOf(RELATED_CARD_IMAGES[0]),
                            imageWidth = 40,
                            imageHeight = 50,
                        )
                    },
                    onClick = { showBottomSheet1.value = true },
                )

                BasicComponent(
                    modifier = Modifier,
                    title = stringResource(RELATED_CARD_NAMES[1]),
                    summary = "本卡片是初级融合此卡片的必要素材",
                    endActions = {
                        ImagesRow(
                            imageResIds = listOf(RELATED_CARD_IMAGES[1]),
                            imageWidth = 40,
                            imageHeight = 50,
                        )
                    },
                    onClick = { showBottomSheet2.value = true },
                )

                BasicComponent(
                    modifier = Modifier,
                    title = stringResource(RELATED_CARD_NAMES[2]),
                    summary = "本卡片是深度融合此卡片的必要素材",
                    endActions = {
                        ImagesRow(
                            imageResIds = listOf(RELATED_CARD_IMAGES[2]),
                            imageWidth = 40,
                            imageHeight = 50,
                        )
                    },
                    onClick = { showBottomSheet3.value = true },
                )

                BasicComponent(
                    modifier = Modifier,
                    title = stringResource(RELATED_CARD_NAMES[3]),
                    summary = "本卡片是灵魂融合此卡片的必要素材",
                    endActions = {
                        ImagesRow(
                            imageResIds = listOf(RELATED_CARD_IMAGES[3]),
                            imageWidth = 40,
                            imageHeight = 50,
                        )
                    },
                    onClick = { showBottomSheet4.value = true },
                )
            }
        }
        item {
            // =================================================== 星级数据 ===================================================

            SmallTitle(text = "强化提升：" + CARD_DATA_STRING[0])
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
                        tabs = tabs1,
                        selectedTabIndex = selectedTabIndex1,
                        onTabSelected = { selectedTabIndex1 = it }
                    )
                    AnimatedVisibility(visible = selectedTabIndex1 == 0) {
                        Slider(
                            value = stepsWithKeyPointsValue1.toFloat(),
                            onValueChange = { stepsWithKeyPointsValue1 = it.roundToInt() },
                            valueRange = 0f..16f,
                            steps = 15,
                            hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                            showKeyPoints = true,
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                        )
                    }
                    AnimatedVisibility(visible = selectedTabIndex1 == 1) {
                        RangeSlider(
                            value = rangeStepsFirstValue1.toFloat()..rangeStepsLastValue1.toFloat(),
                            onValueChange = { floatRange ->
                                rangeStepsFirstValue1 = floatRange.start.roundToInt()
                                rangeStepsLastValue1 = floatRange.endInclusive.roundToInt()
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

            AnimatedVisibility(visible =
                selectedTabIndex1 == 0 && stepsWithKeyPointsValue1 == 0 || selectedTabIndex1 == 1 && rangeStepsFirstValue1 <= 0 && rangeStepsLastValue1 >= 0
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
                        summary = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[0] + fusionValue1 + fusionValue2)
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex1 == 0 && stepsWithKeyPointsValue1== 1 || selectedTabIndex1 == 1 && rangeStepsFirstValue1 <= 1 && rangeStepsLastValue1 >= 1
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[1] + fusionValue1 + fusionValue2),
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
                selectedTabIndex1 == 0 && stepsWithKeyPointsValue1== 2 || selectedTabIndex1 == 1 && rangeStepsFirstValue1 <= 2 && rangeStepsLastValue1 >= 2
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[2] + fusionValue1 + fusionValue2),
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
                selectedTabIndex1 == 0 && stepsWithKeyPointsValue1== 3 || selectedTabIndex1 == 1 && rangeStepsFirstValue1 <= 3 && rangeStepsLastValue1 >= 3
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[3] + fusionValue1 + fusionValue2),
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
                selectedTabIndex1 == 0 && stepsWithKeyPointsValue1== 4 || selectedTabIndex1 == 1 && rangeStepsFirstValue1 <= 4 && rangeStepsLastValue1 >= 4
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[4] + fusionValue1 + fusionValue2),
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
                selectedTabIndex1 == 0 && stepsWithKeyPointsValue1== 5 || selectedTabIndex1 == 1 && rangeStepsFirstValue1 <= 5 && rangeStepsLastValue1 >= 5
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[5] + fusionValue1 + fusionValue2),
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
                selectedTabIndex1 == 0 && stepsWithKeyPointsValue1== 6 || selectedTabIndex1 == 1 && rangeStepsFirstValue1 <= 6 && rangeStepsLastValue1 >= 6
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[6] + fusionValue1 + fusionValue2),
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
                selectedTabIndex1 == 0 && stepsWithKeyPointsValue1== 7 || selectedTabIndex1 == 1 && rangeStepsFirstValue1 <= 7 && rangeStepsLastValue1 >= 7
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[7] + fusionValue1 + fusionValue2),
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
                selectedTabIndex1 == 0 && stepsWithKeyPointsValue1== 8 || selectedTabIndex1 == 1 && rangeStepsFirstValue1 <= 8 && rangeStepsLastValue1 >= 8
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[8] + fusionValue1 + fusionValue2),
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
                selectedTabIndex1 == 0 && stepsWithKeyPointsValue1== 9 || selectedTabIndex1 == 1 && rangeStepsFirstValue1 <= 9 && rangeStepsLastValue1 >= 9
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[9] + fusionValue1 + fusionValue2),
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
                selectedTabIndex1 == 0 && stepsWithKeyPointsValue1== 10 || selectedTabIndex1 == 1 && rangeStepsFirstValue1 <= 10 && rangeStepsLastValue1 >= 10
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[10] + fusionValue1 + fusionValue2),
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
                selectedTabIndex1 == 0 && stepsWithKeyPointsValue1== 11 || selectedTabIndex1 == 1 && rangeStepsFirstValue1 <= 11 && rangeStepsLastValue1 >= 11
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[11] + fusionValue1 + fusionValue2),
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
                selectedTabIndex1 == 0 && stepsWithKeyPointsValue1== 12 || selectedTabIndex1 == 1 && rangeStepsFirstValue1 <= 12 && rangeStepsLastValue1 >= 12
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[12] + fusionValue1 + fusionValue2),
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
                selectedTabIndex1 == 0 && stepsWithKeyPointsValue1== 13 || selectedTabIndex1 == 1 && rangeStepsFirstValue1 <= 13 && rangeStepsLastValue1 >= 13
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[13] + fusionValue1 + fusionValue2),
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
                selectedTabIndex1 == 0 && stepsWithKeyPointsValue1== 14 || selectedTabIndex1 == 1 && rangeStepsFirstValue1 <= 14 && rangeStepsLastValue1 >= 14
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[14] + fusionValue1 + fusionValue2),
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
                selectedTabIndex1 == 0 && stepsWithKeyPointsValue1== 15 || selectedTabIndex1 == 1 && rangeStepsFirstValue1 <= 15 && rangeStepsLastValue1 >= 15
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[15] + fusionValue1 + fusionValue2),
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
                selectedTabIndex1 == 0 && stepsWithKeyPointsValue1== 16 || selectedTabIndex1 == 1 && rangeStepsFirstValue1 <= 16 && rangeStepsLastValue1 >= 16
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[16] + fusionValue1 + fusionValue2),
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
                selectedTabIndex1 == 0 && stepsWithKeyPointsValue1== 17 || selectedTabIndex1 == 1 && rangeStepsFirstValue1 <= 17 && rangeStepsLastValue1 >= 17
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
                selectedTabIndex1 == 0 && stepsWithKeyPointsValue1== 18 || selectedTabIndex1 == 1 && rangeStepsFirstValue1 <= 18 && rangeStepsLastValue1 >= 18
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
            // =================================================== 品阶数据 ===================================================

            SmallTitle(text = "品阶提升：\n" +
                    "初级融合：" + CARD_DATA_FUSION_TITLE[0] + "\n" +
                    "深度融合：" + CARD_DATA_FUSION_TITLE[1] + "\n" +
                    "灵魂融合：" + CARD_DATA_FUSION_TITLE[2]
            )

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
                        tabs = tabs2,
                        selectedTabIndex = selectedTabIndex2,
                        onTabSelected = { selectedTabIndex2 = it }
                    )
                    AnimatedVisibility(visible = selectedTabIndex2 == 0) {
                        Slider(
                            value = stepsWithKeyPointsValue2.toFloat(),
                            onValueChange = { stepsWithKeyPointsValue2 = it.roundToInt() },
                            valueRange = 1f..16f,
                            steps = 14,
                            hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                            showKeyPoints = true,
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(bottom = 12.dp),
                        )
                    }
                    AnimatedVisibility(visible = selectedTabIndex2 == 1) {
                        RangeSlider(
                            value = rangeStepsFirstValue2.toFloat()..rangeStepsLastValue2.toFloat(),
                            onValueChange = { floatRange ->
                                rangeStepsFirstValue2 = floatRange.start.roundToInt()
                                rangeStepsLastValue2 = floatRange.endInclusive.roundToInt()
                            },
                            valueRange = 1f..16f,
                            steps = 14,
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
                selectedTabIndex2 == 0 && stepsWithKeyPointsValue2 == 1 || selectedTabIndex2 == 1 && rangeStepsFirstValue2 <= 1 && rangeStepsLastValue2 >= 1
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
                        title = CARD_DATA_FUSION_STRING[0] + "：" + CARD_DATA_FUSION_1[1] + "\n" +
                                CARD_DATA_FUSION_STRING[1] + "：" + CARD_DATA_FUSION_2[1] + "\n" +
                                CARD_DATA_FUSION_STRING[2] + "：" + CARD_DATA_FUSION_3[1] + "\n" +
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[1],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.fusion_image_1),
                                imageWidth = 35,
                                imageHeight = 14,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex2 == 0 && stepsWithKeyPointsValue2 == 2 || selectedTabIndex2 == 1 && rangeStepsFirstValue2 <= 2 && rangeStepsLastValue2 >= 2
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
                        title = CARD_DATA_FUSION_STRING[0] + "：" + CARD_DATA_FUSION_1[2] + "\n" +
                                CARD_DATA_FUSION_STRING[1] + "：" + CARD_DATA_FUSION_2[2] + "\n" +
                                CARD_DATA_FUSION_STRING[2] + "：" + CARD_DATA_FUSION_3[2] + "\n" +
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[2],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.fusion_image_2),
                                imageWidth = 35,
                                imageHeight = 14,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex2 == 0 && stepsWithKeyPointsValue2 == 3 || selectedTabIndex2 == 1 && rangeStepsFirstValue2 <= 3 && rangeStepsLastValue2 >= 3
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
                        title = CARD_DATA_FUSION_STRING[0] + "：" + CARD_DATA_FUSION_1[3] + "\n" +
                                CARD_DATA_FUSION_STRING[1] + "：" + CARD_DATA_FUSION_2[3] + "\n" +
                                CARD_DATA_FUSION_STRING[2] + "：" + CARD_DATA_FUSION_3[3] + "\n" +
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[3],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.fusion_image_3),
                                imageWidth = 35,
                                imageHeight = 14,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex2 == 0 && stepsWithKeyPointsValue2 == 4 || selectedTabIndex2 == 1 && rangeStepsFirstValue2 <= 4 && rangeStepsLastValue2 >= 4
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
                        title = CARD_DATA_FUSION_STRING[0] + "：" + CARD_DATA_FUSION_1[4] + "\n" +
                                CARD_DATA_FUSION_STRING[1] + "：" + CARD_DATA_FUSION_2[4] + "\n" +
                                CARD_DATA_FUSION_STRING[2] + "：" + CARD_DATA_FUSION_3[4] + "\n" +
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[4],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.fusion_image_4),
                                imageWidth = 35,
                                imageHeight = 14,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex2 == 0 && stepsWithKeyPointsValue2 == 5 || selectedTabIndex2 == 1 && rangeStepsFirstValue2 <= 5 && rangeStepsLastValue2 >= 5
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
                        title = CARD_DATA_FUSION_STRING[0] + "：" + CARD_DATA_FUSION_1[5] + "\n" +
                                CARD_DATA_FUSION_STRING[1] + "：" + CARD_DATA_FUSION_2[5] + "\n" +
                                CARD_DATA_FUSION_STRING[2] + "：" + CARD_DATA_FUSION_3[5] + "\n" +
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[5],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.fusion_image_5),
                                imageWidth = 35,
                                imageHeight = 14,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex2 == 0 && stepsWithKeyPointsValue2 == 6 || selectedTabIndex2 == 1 && rangeStepsFirstValue2 <= 6 && rangeStepsLastValue2 >= 6
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
                        title = CARD_DATA_FUSION_STRING[0] + "：" + CARD_DATA_FUSION_1[6] + "\n" +
                                CARD_DATA_FUSION_STRING[1] + "：" + CARD_DATA_FUSION_2[6] + "\n" +
                                CARD_DATA_FUSION_STRING[2] + "：" + CARD_DATA_FUSION_3[6] + "\n" +
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[6],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.fusion_image_6),
                                imageWidth = 35,
                                imageHeight = 14,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex2 == 0 && stepsWithKeyPointsValue2 == 7 || selectedTabIndex2 == 1 && rangeStepsFirstValue2 <= 7 && rangeStepsLastValue2 >= 7
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
                        title = CARD_DATA_FUSION_STRING[0] + "：" + CARD_DATA_FUSION_1[7] + "\n" +
                                CARD_DATA_FUSION_STRING[1] + "：" + CARD_DATA_FUSION_2[7] + "\n" +
                                CARD_DATA_FUSION_STRING[2] + "：" + CARD_DATA_FUSION_3[7] + "\n" +
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[7],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.fusion_image_7),
                                imageWidth = 35,
                                imageHeight = 14,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex2 == 0 && stepsWithKeyPointsValue2 == 8 || selectedTabIndex2 == 1 && rangeStepsFirstValue2 <= 8 && rangeStepsLastValue2 >= 8
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
                        title = CARD_DATA_FUSION_STRING[0] + "：" + CARD_DATA_FUSION_1[8] + "\n" +
                                CARD_DATA_FUSION_STRING[1] + "：" + CARD_DATA_FUSION_2[8] + "\n" +
                                CARD_DATA_FUSION_STRING[2] + "：" + CARD_DATA_FUSION_3[8] + "\n" +
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[8],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.fusion_image_8),
                                imageWidth = 35,
                                imageHeight = 14,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex2 == 0 && stepsWithKeyPointsValue2 == 9 || selectedTabIndex2 == 1 && rangeStepsFirstValue2 <= 9 && rangeStepsLastValue2 >= 9
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
                        title = CARD_DATA_FUSION_STRING[0] + "：" + CARD_DATA_FUSION_1[9] + "\n" +
                                CARD_DATA_FUSION_STRING[1] + "：" + CARD_DATA_FUSION_2[9] + "\n" +
                                CARD_DATA_FUSION_STRING[2] + "：" + CARD_DATA_FUSION_3[9] + "\n" +
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[9],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.fusion_image_9),
                                imageWidth = 37,
                                imageHeight = 14,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex2 == 0 && stepsWithKeyPointsValue2 == 10 || selectedTabIndex2 == 1 && rangeStepsFirstValue2 <= 10 && rangeStepsLastValue2 >= 10
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
                        title = CARD_DATA_FUSION_STRING[0] + "：" + CARD_DATA_FUSION_1[10] + "\n" +
                                CARD_DATA_FUSION_STRING[1] + "：" + CARD_DATA_FUSION_2[10] + "\n" +
                                CARD_DATA_FUSION_STRING[2] + "：" + CARD_DATA_FUSION_3[10] + "\n" +
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[10],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.fusion_image_10),
                                imageWidth = 40,
                                imageHeight = 14,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex2 == 0 && stepsWithKeyPointsValue2 == 11 || selectedTabIndex2 == 1 && rangeStepsFirstValue2 <= 11 && rangeStepsLastValue2 >= 11
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
                        title = CARD_DATA_FUSION_STRING[0] + "：" + CARD_DATA_FUSION_1[11] + "\n" +
                                CARD_DATA_FUSION_STRING[1] + "：" + CARD_DATA_FUSION_2[11] + "\n" +
                                CARD_DATA_FUSION_STRING[2] + "：" + CARD_DATA_FUSION_3[11] + "\n" +
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[11],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.fusion_image_11),
                                imageWidth = 40,
                                imageHeight = 14,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex2 == 0 && stepsWithKeyPointsValue2 == 12 || selectedTabIndex2 == 1 && rangeStepsFirstValue2 <= 12 && rangeStepsLastValue2 >= 12
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
                        title = CARD_DATA_FUSION_STRING[0] + "：" + CARD_DATA_FUSION_1[12] + "\n" +
                                CARD_DATA_FUSION_STRING[1] + "：" + CARD_DATA_FUSION_2[12] + "\n" +
                                CARD_DATA_FUSION_STRING[2] + "：" + CARD_DATA_FUSION_3[12] + "\n" +
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[12],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.fusion_image_12),
                                imageWidth = 40,
                                imageHeight = 14,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex2 == 0 && stepsWithKeyPointsValue2 == 13 || selectedTabIndex2 == 1 && rangeStepsFirstValue2 <= 13 && rangeStepsLastValue2 >= 13
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
                        title = CARD_DATA_FUSION_STRING[0] + "：" + CARD_DATA_FUSION_1[13] + "\n" +
                                CARD_DATA_FUSION_STRING[1] + "：" + CARD_DATA_FUSION_2[13] + "\n" +
                                CARD_DATA_FUSION_STRING[2] + "：" + CARD_DATA_FUSION_3[13] + "\n" +
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[13],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.fusion_image_13),
                                imageWidth = 47,
                                imageHeight = 13,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex2 == 0 && stepsWithKeyPointsValue2 == 14 || selectedTabIndex2 == 1 && rangeStepsFirstValue2 <= 14 && rangeStepsLastValue2 >= 14
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
                        title = CARD_DATA_FUSION_STRING[0] + "：" + CARD_DATA_FUSION_1[14] + "\n" +
                                CARD_DATA_FUSION_STRING[1] + "：" + CARD_DATA_FUSION_2[14] + "\n" +
                                CARD_DATA_FUSION_STRING[2] + "：" + CARD_DATA_FUSION_3[14] + "\n" +
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[14],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.fusion_image_14),
                                imageWidth = 42,
                                imageHeight = 16,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex2 == 0 && stepsWithKeyPointsValue2 == 15 || selectedTabIndex2 == 1 && rangeStepsFirstValue2 <= 15 && rangeStepsLastValue2 >= 15
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
                        title = CARD_DATA_FUSION_STRING[0] + "：" + CARD_DATA_FUSION_1[15] + "\n" +
                                CARD_DATA_FUSION_STRING[1] + "：" + CARD_DATA_FUSION_2[15] + "\n" +
                                CARD_DATA_FUSION_STRING[2] + "：" + CARD_DATA_FUSION_3[15] + "\n" +
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[15],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.fusion_image_15),
                                imageWidth = 47,
                                imageHeight = 16,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex2 == 0 && stepsWithKeyPointsValue2 == 16 || selectedTabIndex2 == 1 && rangeStepsFirstValue2 <= 16 && rangeStepsLastValue2 >= 16
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
                        title = CARD_DATA_FUSION_STRING[0] + "：" + CARD_DATA_FUSION_1[16] + "\n" +
                                CARD_DATA_FUSION_STRING[1] + "：" + CARD_DATA_FUSION_2[16] + "\n" +
                                CARD_DATA_FUSION_STRING[2] + "：" + CARD_DATA_FUSION_3[16] + "\n" +
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[16],
                        endActions = {
                            ImagesRow(
                                imageResIds = listOf(R.drawable.fusion_image_16),
                                imageWidth = 49,
                                imageHeight = 16,
                            )
                        },
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex2 == 0 && stepsWithKeyPointsValue2 == 17 || selectedTabIndex2 == 1 && rangeStepsFirstValue2 <= 17 && rangeStepsLastValue2 >= 17
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
                        summary = CARD_DATA_FUSION_STRING[0] + "：" + CARD_DATA_FUSION_1[17] + "\n" +
                                CARD_DATA_FUSION_STRING[1] + "：" + CARD_DATA_FUSION_2[17] + "\n" +
                                CARD_DATA_FUSION_STRING[2] + "：" + CARD_DATA_FUSION_3[17] + "\n" +
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[17],
                    )
                }
            }
            AnimatedVisibility(
                selectedTabIndex2 == 0 && stepsWithKeyPointsValue2 == 18 || selectedTabIndex2 == 1 && rangeStepsFirstValue2 <= 18 && rangeStepsLastValue2 >= 18
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
                        summary = CARD_DATA_FUSION_STRING[0] + "：" + CARD_DATA_FUSION_1[18] + "\n" +
                                CARD_DATA_FUSION_STRING[1] + "：" + CARD_DATA_FUSION_2[18] + "\n" +
                                CARD_DATA_FUSION_STRING[2] + "：" + CARD_DATA_FUSION_3[18] + "\n" +
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[18],
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
                    title = "LV.0：" + CARD_DATA_SKILL_1[0],
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
                    title = "LV.1-3：" + CARD_DATA_SKILL_1[1] + "、" + CARD_DATA_SKILL_1[2] + "、" + CARD_DATA_SKILL_1[3],
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
                    title = "LV.4-5：" + CARD_DATA_SKILL_1[4] + "、" + CARD_DATA_SKILL_1[5],
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
                    title = "LV.6-7：" + CARD_DATA_SKILL_1[6] + "、" + CARD_DATA_SKILL_1[7],
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
                    title = "LV.8：" + CARD_DATA_SKILL_1[8],
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
                    title = "附加说明\uD83E\uDD28",
                    summary = "每轮攻击造成10次伤害，间隔0.2秒",
                )
            }

            DeveloperTips()
        }
        item {
        }
        item {
        }
    }

    WindowBottomSheet(
        show = showCardAuxiliaryBottomSheet.value,
        backgroundColor = MiuixTheme.colorScheme.surface,
        modifier = Modifier.height(400.dp),
        title = "查看与设置卡片增益",
        insideMargin = DpSize(width = 0.dp, height = 0.dp),
        enableNestedScroll = false,
        onDismissRequest = { showCardAuxiliaryBottomSheet.value = false }
    ) {
        CompositionLocalProvider(LocalDensity provides density) {
            LazyColumn(
                modifier = Modifier
                    .scrollEndHaptic()
                    .overScrollVertical()
                    .fillMaxSize(),
                overscrollEffect = null,
            ) {
                item {
                    // =================================================== 相关增益 ===================================================
                    // =================================================== 食神谱 ===================================================

                    // =================================================== 真爱结晶 ===================================================

                    // ===================================================== 品阶 =====================================================

                    Card(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .padding(bottom = 12.dp),
                    ) {
                        SwitchPreference(
                            title = "将初级附加攻击力加入计算",
                            summary = "将自动计算出启用后的数据",
                            checked = fusionChecked1.value,
                            onCheckedChange = { checked ->
                                fusionChecked1.value = checked
                                fusionValue1 = if (checked) CARD_DATA_FUSION_1[fusionLevel] else 0
                                fusionValue2 = if (checked && fusionChecked2.value) CARD_DATA_FUSION_4[fusionLevel] else 0
                            }
                        )
                        AnimatedVisibility(visible = fusionChecked1.value) {
                            SwitchPreference(
                                title = "将灵魂附加攻击力加入计算",
                                summary = "将自动计算出启用后的数据",
                                checked = fusionChecked2.value,
                                onCheckedChange = { checked ->
                                    fusionChecked2.value = checked
                                    fusionValue2 = if (checked) CARD_DATA_FUSION_4[fusionLevel] else 0
                                }
                            )
                        }
                        AnimatedVisibility(visible = fusionChecked1.value) {
                            Column {
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "当前品阶：$fusionLevel",
                                    onClick = {  },
                                )
                                Slider(
                                    value = fusionLevel.toFloat(),
                                    onValueChange = {
                                        fusionLevel = it.roundToInt()
                                        fusionValue1 = CARD_DATA_FUSION_1[fusionLevel]
                                        fusionValue2 = if (fusionChecked2.value) CARD_DATA_FUSION_4[fusionLevel] else 0
                                    },
                                    valueRange = 1f..16f,
                                    steps = 14,
                                    hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                                    showKeyPoints = true,
                                    modifier = Modifier
                                        .padding(12.dp),
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