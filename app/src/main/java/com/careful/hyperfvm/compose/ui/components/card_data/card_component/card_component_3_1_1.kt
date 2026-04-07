package com.careful.hyperfvm.compose.ui.components.card_data.card_component

import android.widget.Toast
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.careful.hyperfvm.compose.card_data_detail.SKILL_POINT_3
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
import kotlin.random.Random

@Stable
private val CARD_NAMES = listOf(
    R.string.name_card_data_index_3_1_1_0,
    R.string.name_card_data_index_3_1_1_1,
    R.string.name_card_data_index_3_1_1_2
)

@Stable
private val CARD_NAMES_FUNNY = listOf(
    R.string.name_card_data_index_3_1_1_0,
    R.string.name_card_data_index_3_1_1_1,
    R.string.name_card_data_index_3_1_1_2_funny
)

@Stable
private val CARD_IMAGES = listOf(
    R.drawable.card_data_index_3_1_1_0,
    R.drawable.card_data_index_3_1_1_1,
    R.drawable.card_data_index_3_1_1_2
)

@Stable
private val CARD_IMAGES_BIG = listOf(
    R.drawable.card_data_index_3_1_1_0_big,
    R.drawable.card_data_index_3_1_1_1_big,
    R.drawable.card_data_index_3_1_1_2_big
)

@Stable
private val CARD_IMAGES_BIG_FUNNY = listOf(
    R.drawable.card_data_index_3_1_1_0_big,
    R.drawable.card_data_index_3_1_1_1_big,
    R.drawable.card_data_index_3_1_1_2_big_funny
)

@Stable
private val CARD_IMAGES_TRANSFER_1 = listOf(
    R.drawable.card_data_index_3_1_1_transfer_1_a,
    R.drawable.card_data_index_3_1_1_transfer_1_b
)

@Stable
private val CARD_IMAGES_TRANSFER_2 = listOf(
    R.drawable.card_data_index_3_1_1_transfer_2_a,
    R.drawable.card_data_index_3_1_1_transfer_2_b,
    R.drawable.card_data_index_3_1_1_transfer_2_c
)

@Stable
private val CARD_DESCRIPTION = mutableStateListOf(
    "能力：同时向5个方向射出星星子弹。可直接在海底生存",
    "能力：可在陆地使用",
    "能力：每路增加1发子弹",
    "50",
    "7",
    "所属分类：海星类",
    "耗能：175",
    "作为副卡：好卡\uD83D\uDE00",
    "价格：1800×2=" + 1800 * 2 + "威望\n解锁层数：20、25",
    "价格：3500×3=" + 3500 * 3 + "威望\n解锁层数：60、65、70",
    "1️⃣子弹数量(单方向)：1/1/2\n2️⃣一转后可在非海底携带\n3️⃣二转后子弹可被反弹",
)

@Stable
private val RELATED_CARD_NAMES = listOf(
    R.string.name_card_data_index_3_1_4_0,
)

@Stable
private val RELATED_CARD_IMAGES = listOf(
    R.drawable.card_data_index_3_1_4_0,
)

@Stable
private val CARD_DATA_STRING = listOf(
    "攻击力"
)

@Stable
private val CARD_DATA_1 = listOf(
    50, 60, 70, 80, 100, 120, 140, 170, 200, 230, 280, 360, 460, 570, 690, 820, 970, -1, -1
)

@Stable
private val CARD_DATA_SKILL_IMAGES = listOf(
    R.drawable.card_data_index_3_1_1_skill_1,
    R.drawable.card_data_index_3_1_1_skill_2,
    R.drawable.card_data_index_3_1_1_skill_3,
    R.drawable.card_data_index_3_1_1_skill_4
)

@Stable
private val CARD_DATA_SKILL_STRING = listOf(
    "攻击间隔"
)

@Stable
private val CARD_DATA_SKILL_1 = listOf(
    1.3f, 1.25f, 1.2f, 1.15f, 1.1f, 1.05f, 1f, 0.9f, 0.8f
)

@Stable
@Composable
fun CardComponent_3_1_1() {
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

    CardDataDetailWindowBottomSheet_3_1_1(showBottomSheet, cardName)
}

@Composable
fun CardDataDetailWindowBottomSheet_3_1_1(
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

    val cookeryChecked = rememberSaveable { mutableStateOf(false) }
    var cookeryValue by rememberSaveable { mutableFloatStateOf(1f) }

    val crystoneChecked = rememberSaveable { mutableStateOf(false) }
    var crystoneLevel by rememberSaveable { mutableIntStateOf(0) }
    var crystoneValue by rememberSaveable { mutableFloatStateOf(1f) }

    val tabs1 = listOf("单个星级", "一些星级")
    var selectedTabIndex1 by rememberSaveable { mutableIntStateOf(0) }
    var stepsWithKeyPointsValue1 by rememberSaveable { mutableIntStateOf(12) }
    var rangeStepsFirstValue1 by rememberSaveable { mutableIntStateOf(9) }
    var rangeStepsLastValue1 by rememberSaveable { mutableIntStateOf(16) }

    LazyColumn(
        modifier = Modifier
            .scrollEndHaptic()
            .overScrollVertical()
            .fillMaxSize(),
        overscrollEffect = null,
    ) {
        item {
            // =================================================== 基础信息 ===================================================

            val boundedInt = Random.nextInt(1, 101)
            if (boundedInt == 16 || boundedInt == 32 || boundedInt == 48 || boundedInt == 64 || boundedInt == 80 || boundedInt == 96) {
                Toast.makeText(LocalContext.current, "哇，是" + stringResource(CARD_NAMES_FUNNY[2]) + "🤪", Toast.LENGTH_SHORT).show()
                BaseInfoWithBigImage(
                    cardImagesBig = CARD_IMAGES_BIG_FUNNY,
                    cardImages = CARD_IMAGES,
                    cardNames = CARD_NAMES_FUNNY,
                    cardDescription = CARD_DESCRIPTION,
                )
            } else {
                BaseInfoWithBigImage(
                    cardImagesBig = CARD_IMAGES_BIG,
                    cardImages = CARD_IMAGES,
                    cardNames = CARD_NAMES,
                    cardDescription = CARD_DESCRIPTION,
                )
            }

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
                    title = "一转凭证",
                    summary = CARD_DESCRIPTION[8],
                    endActions = {
                        ImagesRow(
                            imageResIds = CARD_IMAGES_TRANSFER_1,
                            imageWidth = 40,
                            imageHeight = 50,
                        )
                    },
                    onClick = {  },
                )
                BasicComponent(
                    modifier = Modifier,
                    title = "二转凭证",
                    summary = CARD_DESCRIPTION[9],
                    endActions = {
                        ImagesRow(
                            imageResIds = CARD_IMAGES_TRANSFER_2,
                            imageWidth = 40,
                            imageHeight = 50,
                        )
                    },
                    onClick = {  },
                )
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
                    title = CARD_DESCRIPTION[10],
                    onClick = {  },
                )
            }
        }
        item {
            // =================================================== 相关卡片 ===================================================

            SmallTitle(text = "相关卡片")
            //CardDataDetailWindowBottomSheet_3_1_4(showBottomSheet1, stringResource(R.string.name_card_data_index_3_1_4_0))

            Card(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
            ) {
                BasicComponent(
                    modifier = Modifier,
                    title = stringResource(RELATED_CARD_NAMES[0]),
                    summary = "本卡片是合成此金卡的必要素材",
                    endActions = {
                        ImagesRow(
                            imageResIds = listOf(RELATED_CARD_IMAGES[0]),
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
                        summary = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[0] * cookeryValue).toInt() * crystoneValue).toInt())
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
                        title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[1] * cookeryValue).toInt() * crystoneValue).toInt()),
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
                        title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[2] * cookeryValue).toInt() * crystoneValue).toInt()),
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
                        title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[3] * cookeryValue).toInt() * crystoneValue).toInt()),
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
                        title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[4] * cookeryValue).toInt() * crystoneValue).toInt()),
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
                        title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[5] * cookeryValue).toInt() * crystoneValue).toInt()),
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
                        title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[6] * cookeryValue).toInt() * crystoneValue).toInt()),
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
                        title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[7] * cookeryValue).toInt() * crystoneValue).toInt()),
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
                        title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[8] * cookeryValue).toInt() * crystoneValue).toInt()),
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
                        title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[9] * cookeryValue).toInt() * crystoneValue).toInt()),
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
                        title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[10] * cookeryValue).toInt() * crystoneValue).toInt()),
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
                        title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[11] * cookeryValue).toInt() * crystoneValue).toInt()),
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
                        title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[12] * cookeryValue).toInt() * crystoneValue).toInt()),
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
                        title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[13] * cookeryValue).toInt() * crystoneValue).toInt()),
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
                        title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[14] * cookeryValue).toInt() * crystoneValue).toInt()),
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
                        title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[15] * cookeryValue).toInt() * crystoneValue).toInt()),
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
                        title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[16] * cookeryValue).toInt() * crystoneValue).toInt()),
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
                    summary = "初级一共所需技能点：" + (SKILL_POINT_3[1] + SKILL_POINT_3[2] + SKILL_POINT_3[3]),
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
                    summary = "高级一共所需技能点：" + (SKILL_POINT_3[4] + SKILL_POINT_3[5]),
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
                    summary = "终级一共所需技能点：" + (SKILL_POINT_3[6] + SKILL_POINT_3[7]),
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
                    summary = "究极一共所需技能点：" + (SKILL_POINT_3[8]),
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
                    title = "无需气泡\uD83E\uDEE7",
                    summary = "卡片可直接在海底生存",
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
                    onClick = {  },
                ) {
                    Text(
                        text = "反弹🍮",
                        style = MiuixTheme.textStyles.headline1,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = MiuixTheme.colorScheme.primary, fontWeight = FontWeight.Bold)) {
                                append("二转后")
                            }
                            append("，子弹可被反弹")
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
                    onClick = {  },
                ) {
                    Text(
                        text = "讲故事💡",
                        style = MiuixTheme.textStyles.headline1,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = buildAnnotatedString {
                            append("1️⃣2018年初版的炭烧海星子弹单发攻击力同小笼包，任何转职形态均不可反弹也不可过火，且基本打不到老鼠\n2️⃣一次更新后，")
                            withStyle(style = SpanStyle(color = MiuixTheme.colorScheme.primary, fontWeight = FontWeight.Bold)) {
                                append("攻击力同双鱼，且二转可被反弹并且子弹可以过火(至少2019年二月还是这个状态），当时海星威力高于四转金卡")
                            }
                            append("\n3️⃣二次更新后移除了子弹过火的特性")
                            withStyle(style = SpanStyle(color = MiuixTheme.colorScheme.primary, fontWeight = FontWeight.Bold)) {
                                append("（也就是现在的海星）")
                            }
                            append("\n4️⃣以前")
                            withStyle(style = SpanStyle(color = MiuixTheme.colorScheme.primary, fontWeight = FontWeight.Bold)) {
                                append("炭烧海星配方")
                            }
                            append("在")
                            withStyle(style = SpanStyle(color = MiuixTheme.colorScheme.primary, fontWeight = FontWeight.Bold)) {
                                append("北极贝湍流")
                            }
                            append("即可掉落，后被移除🤬\n5️⃣以前")
                            withStyle(style = SpanStyle(color = MiuixTheme.colorScheme.primary, fontWeight = FontWeight.Bold)) {
                                append("炭烧海星-究极技能书")
                            }
                            append("在")
                            withStyle(style = SpanStyle(color = MiuixTheme.colorScheme.primary, fontWeight = FontWeight.Bold)) {
                                append("大富翁宝箱")
                            }
                            append("中可以开出，后被移除🤬")
                        },
                        style = MiuixTheme.textStyles.body2,
                        color = MiuixTheme.colorScheme.onSurfaceVariantSummary
                    )
                }
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

                    Card(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .padding(bottom = 12.dp),
                    ) {
                        BasicComponent(
                            modifier = Modifier,
                            title = "食神谱：海鲜大咖😋",
                            summary = "攻击力提升10%",
                            endActions = {
                                ImagesRow(
                                    imageResIds = listOf(R.drawable.card_data_cookery_3_29),
                                    imageWidth = 44,
                                    imageHeight = 44,
                                )
                            },
                            onClick = {  },
                        )
                        SwitchPreference(
                            title = "启用该食神谱",
                            summary = "将自动计算出启用后的数据",
                            checked = cookeryChecked.value,
                            onCheckedChange = { checked ->
                                cookeryChecked.value = checked
                                cookeryValue = if (checked) 1.1f else 1.0f
                            }
                        )
                    }

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
                                    imageResIds = listOf(R.drawable.card_data_index_3_1_1_crystone),
                                    imageWidth = 44,
                                    imageHeight = 44,
                                )
                            },
                            onClick = {  },
                        )
                        SwitchPreference(
                            title = "启用该结晶",
                            summary = "将自动计算出启用后的数据",
                            checked = crystoneChecked.value,
                            onCheckedChange = { checked ->
                                crystoneChecked.value = checked
                                crystoneValue = if (checked) CRYSTONE_ATTACK[crystoneLevel] else 1.0f
                            }
                        )
                        AnimatedVisibility(visible = crystoneChecked.value) {
                            Column {
                                BasicComponent(
                                    modifier = Modifier,
                                    title = "当前等级：$crystoneLevel",
                                    onClick = {  },
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

                    Spacer(modifier = Modifier.padding(bottom = 48.dp))
                }
            }
        }
    }
}