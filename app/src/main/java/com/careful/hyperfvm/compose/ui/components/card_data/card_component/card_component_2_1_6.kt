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
import androidx.compose.runtime.mutableFloatStateOf
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
import com.careful.hyperfvm.compose.card_data_detail.CRYSTONE_ATTACK
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
import top.yukonga.miuix.kmp.extra.SuperSwitch
import top.yukonga.miuix.kmp.extra.WindowBottomSheet
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
    R.string.name_card_data_index_2_1_6_0,
    R.string.name_card_data_index_2_1_6_1,
    R.string.name_card_data_index_2_1_6_2
)

@Stable
private val CARD_IMAGES = listOf(
    R.drawable.card_data_index_2_1_6_0,
    R.drawable.card_data_index_2_1_6_1,
    R.drawable.card_data_index_2_1_6_2
)

@Stable
private val CARD_IMAGES_BIG = listOf(
    R.drawable.card_data_index_2_1_6_0_big,
    R.drawable.card_data_index_2_1_6_1_big,
    R.drawable.card_data_index_2_1_6_2_big
)

@Stable
private val CARD_DESCRIPTION = listOf(
    "能力：向3行范围内任意方向投掷出3发可追踪老鼠的溅射子弹",
    "能力：子弹数量+1，威力提升",
    "能力：子弹数量+1，击杀老鼠变灰烬",
    "50",
    "7",
    "所属分类：投掷类/大型投手/B.追踪投手",
    "耗能：380",
    "作为副卡：好卡\uD83D\uDE00",
    "1️⃣子弹数量：3/4/5\n2️⃣溅射伤害：3×3范围35%\n3️⃣攻击范围：3行",
)

@Stable
private val CARD_DATA_STRING = listOf(
    "不转攻击力", "一转攻击力"
)

@Stable
private val CARD_DATA_1 = listOf(
    50, 60, 70, 80, 100, 120, 140, 170, 200, 230, 260, 300, 380, 480, 580, 680, 800, -1, -1
)

@Stable
private val CARD_DATA_2 = listOf(
    62, 75, 87, 100, 125, 150, 175, 212, 250, 287, 325, 375, 475, 600, 725, 850, 1000, -1, -1
)

@Stable
private val CARD_DATA_SKILL_IMAGES = listOf(
    R.drawable.card_data_index_2_1_6_skill_1,
    R.drawable.card_data_index_2_1_6_skill_2,
    R.drawable.card_data_index_2_1_6_skill_3,
    R.drawable.card_data_index_2_1_6_skill_4
)

@Stable
private val CARD_DATA_SKILL_STRING = listOf(
    "攻击间隔"
)

@Stable
private val CARD_DATA_SKILL_1 = listOf(
    2.5f, 2.45f, 2.4f, 2.35f, 2.3f, 2.2f, 2.1f, 2f, 1.75f
)

@Stable
private val CARD_DECOMPOSE_AND_GET_IMAGES = listOf(
    R.drawable.card_data_index_2_1_6_0,
    R.drawable.card_data_index_2_1_6_1,
    R.drawable.card_data_index_2_1_6_2,
    R.drawable.card_data_index_2_1_6_skill_1,
    R.drawable.card_data_index_2_1_6_skill_2,
    R.drawable.card_data_index_2_1_6_skill_3,
    R.drawable.card_data_index_2_1_6_skill_4,
    R.drawable.card_data_index_2_1_6_transfer_1_a,
    R.drawable.card_data_index_2_1_6_transfer_1_b,
    R.drawable.card_data_index_2_1_6_transfer_2_a,
    R.drawable.card_data_index_2_1_6_transfer_2_b,
    R.drawable.card_data_index_2_1_6_transfer_2_c,
    R.drawable.animal_pearl,
)

@Stable
private val CARD_DECOMPOSE_DATA = listOf(
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
)

@Stable
private val CARD_GET_DATA = listOf(
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
)

@Stable
@Composable
fun CardComponent_2_1_6() {
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

    CardDataDetailWindowBottomSheet_2_1_6(showBottomSheet, cardName)
}

@Composable
fun CardDataDetailWindowBottomSheet_2_1_6(
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
    val showCardDecomposeAndGetCalculatorBottomSheet = rememberSaveable { mutableStateOf(false) }

    val crystoneChecked = rememberSaveable { mutableStateOf(false) }
    var crystoneLevel by rememberSaveable { mutableIntStateOf(0) }
    var crystoneValue by rememberSaveable { mutableFloatStateOf(1f) }

    val handbookChecked = rememberSaveable { mutableStateOf(false) }
    var handbookValue by rememberSaveable { mutableFloatStateOf(1f) }

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
                        summary = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[0] * crystoneValue).toInt() + "\n"
                                + CARD_DATA_STRING[1] + "：" + (CARD_DATA_2[0] * crystoneValue).toInt(),
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
                        title = CARD_DATA_STRING[0] + "：" + ((CARD_DATA_1[1] * crystoneValue).toInt()) + "\n"
                                + CARD_DATA_STRING[1] + "：" + (CARD_DATA_2[1] * crystoneValue).toInt(),
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
                        title = CARD_DATA_STRING[0] + "：" + ((CARD_DATA_1[2] * crystoneValue).toInt()) + "\n"
                                + CARD_DATA_STRING[1] + "：" + (CARD_DATA_2[2] * crystoneValue).toInt(),
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
                        title = CARD_DATA_STRING[0] + "：" + ((CARD_DATA_1[3] * crystoneValue).toInt()) + "\n"
                                + CARD_DATA_STRING[1] + "：" + (CARD_DATA_2[3] * crystoneValue).toInt(),
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
                        title = CARD_DATA_STRING[0] + "：" + ((CARD_DATA_1[4] * crystoneValue).toInt()) + "\n"
                                + CARD_DATA_STRING[1] + "：" + (CARD_DATA_2[4] * crystoneValue).toInt(),
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
                        title = CARD_DATA_STRING[0] + "：" + ((CARD_DATA_1[5] * crystoneValue).toInt()) + "\n"
                                + CARD_DATA_STRING[1] + "：" + (CARD_DATA_2[5] * crystoneValue).toInt(),
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
                        title = CARD_DATA_STRING[0] + "：" + ((CARD_DATA_1[6] * crystoneValue).toInt()) + "\n"
                                + CARD_DATA_STRING[1] + "：" + (CARD_DATA_2[6] * crystoneValue).toInt(),
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
                        title = CARD_DATA_STRING[0] + "：" + ((CARD_DATA_1[7] * crystoneValue).toInt()) + "\n"
                                + CARD_DATA_STRING[1] + "：" + (CARD_DATA_2[7] * crystoneValue).toInt(),
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
                        title = CARD_DATA_STRING[0] + "：" + ((CARD_DATA_1[8] * crystoneValue).toInt()) + "\n"
                                + CARD_DATA_STRING[1] + "：" + (CARD_DATA_2[8] * crystoneValue).toInt(),
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
                        title = CARD_DATA_STRING[0] + "：" + ((CARD_DATA_1[9] * crystoneValue).toInt()) + "\n"
                                + CARD_DATA_STRING[1] + "：" + (CARD_DATA_2[9] * crystoneValue).toInt(),
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
                        title = CARD_DATA_STRING[0] + "：" + ((CARD_DATA_1[10] * crystoneValue).toInt()) + "\n"
                                + CARD_DATA_STRING[1] + "：" + (CARD_DATA_2[10] * crystoneValue).toInt(),
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
                        title = CARD_DATA_STRING[0] + "：" + ((CARD_DATA_1[11] * crystoneValue).toInt()) + "\n"
                                + CARD_DATA_STRING[1] + "：" + (CARD_DATA_2[11] * crystoneValue).toInt(),
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
                        title = CARD_DATA_STRING[0] + "：" + ((CARD_DATA_1[12] * crystoneValue).toInt()) + "\n"
                                + CARD_DATA_STRING[1] + "：" + (CARD_DATA_2[12] * crystoneValue).toInt(),
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
                        title = CARD_DATA_STRING[0] + "：" + ((CARD_DATA_1[13] * crystoneValue).toInt()) + "\n"
                                + CARD_DATA_STRING[1] + "：" + (CARD_DATA_2[13] * crystoneValue).toInt(),
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
                        title = CARD_DATA_STRING[0] + "：" + ((CARD_DATA_1[14] * crystoneValue).toInt()) + "\n"
                                + CARD_DATA_STRING[1] + "：" + (CARD_DATA_2[14] * crystoneValue).toInt(),
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
                        title = CARD_DATA_STRING[0] + "：" + ((CARD_DATA_1[15] * crystoneValue).toInt()) + "\n"
                                + CARD_DATA_STRING[1] + "：" + (CARD_DATA_2[15] * crystoneValue).toInt(),
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
                        title = CARD_DATA_STRING[0] + "：" + ((CARD_DATA_1[16] * crystoneValue).toInt()) + "\n"
                                + CARD_DATA_STRING[1] + "：" + (CARD_DATA_2[16] * crystoneValue).toInt(),
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
                            "%.2f".format(
                                (CARD_DATA_SKILL_1[0] -
                                        (CARD_DATA_SKILL_1[0] * 20 * (1 - handbookValue)).toInt() * 0.05)
                            ),
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
                            "%.2f".format(
                                (CARD_DATA_SKILL_1[1] -
                                        (CARD_DATA_SKILL_1[1] * 20 * (1 - handbookValue)).toInt() * 0.05)
                            ) + "、" +
                            "%.2f".format(
                                (CARD_DATA_SKILL_1[2] -
                                        (CARD_DATA_SKILL_1[2] * 20 * (1 - handbookValue)).toInt() * 0.05)
                            ) + "、" +
                            "%.2f".format(
                                (CARD_DATA_SKILL_1[3] -
                                        (CARD_DATA_SKILL_1[3] * 20 * (1 - handbookValue)).toInt() * 0.05)
                            ),
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
                            "%.2f".format(
                                (CARD_DATA_SKILL_1[4] -
                                        (CARD_DATA_SKILL_1[4] * 20 * (1 - handbookValue)).toInt() * 0.05)
                            ) + "、" +
                            "%.2f".format(
                                (CARD_DATA_SKILL_1[5] -
                                        (CARD_DATA_SKILL_1[5] * 20 * (1 - handbookValue)).toInt() * 0.05)
                            ),
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
                            "%.2f".format(
                                (CARD_DATA_SKILL_1[6] -
                                        (CARD_DATA_SKILL_1[6] * 20 * (1 - handbookValue)).toInt() * 0.05)
                            ) + "、" +
                            "%.2f".format(
                                (CARD_DATA_SKILL_1[7] -
                                        (CARD_DATA_SKILL_1[7] * 20 * (1 - handbookValue)).toInt() * 0.05)
                            ),
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
                            "%.2f".format(
                                (CARD_DATA_SKILL_1[8] -
                                        (CARD_DATA_SKILL_1[8] * 20 * (1 - handbookValue)).toInt() * 0.05)
                            ),
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
            // =================================================== 分解兑换数据 ===================================================

            AnimalCardDecomposeAndGetInfo(
                CARD_DECOMPOSE_AND_GET_IMAGES,
                CARD_DECOMPOSE_DATA,
                CARD_GET_DATA
            )

            Card(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
                pressFeedbackType = PressFeedbackType.Sink,
                showIndication = true,
            ) {
                BasicComponent(
                    modifier = Modifier,
                    title = "打开分解兑换计算器",
                    summary = "顾名思义，自助求和",
                    endActions = {
                        ImagesRow(
                            imageResIds = listOf(
                                CARD_DECOMPOSE_AND_GET_IMAGES[12],
                            ),
                            imageWidth = 48,
                            imageHeight = 40,
                        )
                    },
                    onClick = { showCardDecomposeAndGetCalculatorBottomSheet.value = true },
                )
            }

            AnimalCardDecomposeAndGetCalculator(
                showCardDecomposeAndGetCalculatorBottomSheet,
                CARD_DECOMPOSE_AND_GET_IMAGES,
                CARD_DECOMPOSE_DATA,
                CARD_GET_DATA,
                "生肖宝珠"
            )
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
                    title = "溅射\uD83D\uDCA6",
                    summary = "溅射受投掷增幅类卡加成",
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
                ) {
                    Text(
                        text = "击杀灰烬💣",
                        style = MiuixTheme.textStyles.headline1,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = MiuixTheme.colorScheme.primary, fontWeight = FontWeight.Bold)) {
                                append("二转后")
                            }
                            append("，子弹附带击杀灰烬效果")
                        },
                        style = MiuixTheme.textStyles.body2,
                        color = MiuixTheme.colorScheme.onSurfaceVariantSummary
                    )
                }
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
                    title = "子弹索敌机制说明\uD83E\uDD28",
                    summary = "1️⃣每行分配的子弹数量：1+1+1/2+1+1/2+1+2\n" +
                            "2️⃣索敌每行最靠左的老鼠\n" +
                            "3️⃣如果该行没有老鼠(空行)，则子弹在非空行投出，优先邻行，优先下路",
                )
            }

            DeveloperTips()
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

                    Card(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .padding(bottom = 12.dp),
                    ) {
                        BasicComponent(
                            modifier = Modifier,
                            title = "真爱结晶🌼",
                            summary = "提升攻击力",
                            endActions = {
                                ImagesRow(
                                    /*imageResIds = listOf(R.drawable.card_data_index_2_1_6_crystone),*/
                                    imageResIds = listOf(R.drawable.card_data_x),
                                    imageWidth = 44,
                                    imageHeight = 44,
                                )
                            },
                            onClick = { },
                        )
                        SuperSwitch(
                            title = "启用该结晶",
                            /*summary = "将自动计算出启用后的数据",*/
                            summary = "暂未开放，无法启用",
                            enabled = false,
                            checked = false,
                            onCheckedChange = { checked ->
                                crystoneChecked.value = checked
                                crystoneValue =
                                    if (checked) CRYSTONE_ATTACK[crystoneLevel] else 1.0f
                            }
                        )
                        AnimatedVisibility(visible = crystoneChecked.value) {
                            Column {
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "当前等级：$crystoneLevel",
                                    onClick = { },
                                )
                                Slider(
                                    value = crystoneLevel.toFloat(),
                                    onValueChange = {
                                        crystoneLevel = it.roundToInt()
                                        crystoneValue = CRYSTONE_ATTACK[crystoneLevel]
                                    },
                                    valueRange = 0f..16f,
                                    steps = 15,
                                    hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                                    showKeyPoints = true,
                                    modifier = Modifier
                                        .padding(12.dp),
                                )
                            }
                        }
                    }

                    // ===================================================== 图鉴 =====================================================

                    Card(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .padding(bottom = 12.dp),
                    ) {
                        BasicComponent(
                            modifier = Modifier,
                            title = "图鉴加成📕",
                            summary = "暂未开放",
                            endActions = {
                                ImagesRow(
                                    imageResIds = listOf(R.drawable.illustrated_handbook_image),
                                    imageWidth = 113,
                                    imageHeight = 54,
                                )
                            },
                            onClick = { },
                        )
                        SuperSwitch(
                            enabled = false,
                            title = "启用图鉴加成",
                            //summary = "将自动计算出启用后的数据",
                            summary = "暂未开放，无法启用",
                            checked = handbookChecked.value,
                            onCheckedChange = { checked ->
                                handbookChecked.value = checked
                                handbookValue = if (checked) 0.95f else 1f
                            }
                        )
                    }

                    Spacer(modifier = Modifier.padding(bottom = 48.dp))
                }
            }
        }
    }
}