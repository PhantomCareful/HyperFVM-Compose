package com.careful.hyperfvm.compose.ui.components.card_data.card_component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
    R.string.name_card_data_index_1_2_4_0,
    R.string.name_card_data_index_1_2_4_1,
    R.string.name_card_data_index_1_2_4_2,
    R.string.name_card_data_index_1_2_4_3,
)

@Stable
private val CARD_IMAGES = listOf(
    R.drawable.card_data_index_1_2_4_0,
    R.drawable.card_data_index_1_2_4_1,
    R.drawable.card_data_index_1_2_4_2,
    R.drawable.card_data_index_1_2_4_3,
)

@Stable
private val CARD_IMAGES_BIG = listOf(
    R.drawable.card_data_index_1_2_4_0_big,
    R.drawable.card_data_index_1_2_4_1_big,
    R.drawable.card_data_index_1_2_4_2_big,
    R.drawable.card_data_index_1_2_4_3_big,
)

@Stable
private val CARD_DESCRIPTION = listOf(
    "能力：向正前方三行分别射出2发子弹，一共6发",
    "能力：中路增加1发攻击力50%的子弹",
    "能力：2行侧路各增加1发子弹",
    "能力：两行侧路间隔发射穿透效果超级子弹",
    "所属分类：管线类/三线类",
    "耗能：300",
    "作为副卡：好卡\uD83D\uDE00",
    "子弹数(三转加中路，四转加侧路)：6/6.5/8.5/8.5",
)

@Stable
private val RELATED_CARD_NAMES = listOf(
    R.string.name_card_data_index_1_2_1_2,
    R.string.name_card_data_index_1_2_2_2,
)

@Stable
private val RELATED_CARD_IMAGES = listOf(
    R.drawable.card_data_index_1_2_1_2,
    R.drawable.card_data_index_1_2_2_2,
)

@Stable
private val CARD_DATA_STRING = listOf(
    "攻击力"
)

@Stable
private val CARD_DATA_1 = listOf(
    17, 20, 23, 26, 29, 32, 35, 41, 51, 63, 87, 111, 135, 158, 185, 210, 235, 325, 488
)

@Stable
private val CARD_SUPPORT_DATA_STRING = listOf(
    "攻击力",
    "三线酒架技能等级",
    "射手座精灵技能等级"
)

@Stable
private val CARD_SUPPORT_DATA = listOf(
    listOf(0, 1, 2, 3, 6, 8, 12, 15, 24),
    listOf(0, 1, 2, 4, 6, 9, 12, 16, 25),
    listOf(1, 2, 2, 4, 7, 9, 13, 16, 25),
    listOf(1, 2, 3, 5, 7, 10, 13, 17, 26),
    listOf(2, 3, 4, 6, 8, 11, 14, 18, 27),
    listOf(3, 4, 5, 7, 9, 12, 15, 19, 28),
    listOf(5, 6, 6, 8, 11, 13, 17, 21, 29),
    listOf(6, 8, 7, 10, 12, 15, 19, 22, 31),
    listOf(10, 11, 9, 14, 16, 19, 22, 26, 35),
)

@Stable
private val CARD_DATA_SKILL_IMAGES = listOf(
    R.drawable.card_data_index_1_2_4_skill_1,
    R.drawable.card_data_index_1_2_4_skill_2,
    R.drawable.card_data_index_1_2_4_skill_3,
    R.drawable.card_data_index_1_2_4_skill_4
)

@Stable
private val CARD_DATA_SKILL_STRING = listOf(
    "攻击间隔"
)

@Stable
private val CARD_DATA_SKILL_1 = listOf(
    1.4f, 1.35f, 1.3f, 1.25f, 1.2f, 1.15f, 1.1f, 1.05f, 1f
)

@Stable
private val CARD_DECOMPOSE_AND_GET_IMAGES = listOf(
    R.drawable.card_data_index_1_2_4_0,
    R.drawable.card_data_index_1_2_4_1,
    R.drawable.card_data_index_1_2_4_2,
    R.drawable.card_data_index_1_2_4_3,
    R.drawable.card_data_index_1_2_4_skill_1,
    R.drawable.card_data_index_1_2_4_skill_2,
    R.drawable.card_data_index_1_2_4_skill_3,
    R.drawable.card_data_index_1_2_4_skill_4,
    R.drawable.card_data_index_1_2_4_transfer_1_a,
    R.drawable.card_data_index_1_2_4_transfer_1_b,
    R.drawable.card_data_index_1_2_4_transfer_1_c,
    R.drawable.card_data_index_1_2_4_transfer_2_a,
    R.drawable.card_data_index_1_2_4_transfer_2_b,
    R.drawable.card_data_index_1_2_4_transfer_2_c,
    R.drawable.card_data_index_1_2_4_transfer_3_a,
    R.drawable.card_data_index_1_2_4_transfer_3_b,
    R.drawable.card_data_index_1_2_4_transfer_3_c,
    R.drawable.card_data_index_1_2_4_compose,
    R.drawable.god_stone,
)

@Stable
private val CARD_DECOMPOSE_DATA = listOf(
    10, 40, 55, 0, 3, 6, 12, 24, 10, 10, 10, 15, 15, 15, 28, 0, 0, 10
)

@Stable
private val CARD_GET_DATA = listOf(
    0, 0, 0, 0, 9, 18, 36, 72, 30, 30, 30, 45, 45, 45, 0, 100, 100, 30
)

@Stable
@Composable
fun CardComponent_1_2_4() {
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
            summary = stringResource(CARD_NAMES[1]) + "-" + stringResource(CARD_NAMES[2]) + "-" + stringResource(
                CARD_NAMES[3]
            ),
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

    CardDataDetailWindowBottomSheet_1_2_4(showBottomSheet, cardName)
}

@Composable
fun CardDataDetailWindowBottomSheet_1_2_4(
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
            LazyColumn(
                modifier = Modifier
                    .scrollEndHaptic()
                    .overScrollVertical()
                    .fillMaxSize(),
                overscrollEffect = null,
            ) {
                item {
                    Info(showCardAuxiliaryBottomSheet)
                }
            }
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

    val showBottomSheet1 = rememberSaveable { mutableStateOf(false) }
    val showBottomSheet2 = rememberSaveable { mutableStateOf(false) }

    val supportChecked = rememberSaveable { mutableStateOf(false) }
    var supportLevel1 by rememberSaveable { mutableIntStateOf(0) }
    var supportLevel2 by rememberSaveable { mutableIntStateOf(0) }
    var supportValue by rememberSaveable { mutableIntStateOf(0) }

    val cookeryChecked = rememberSaveable { mutableStateOf(false) }
    var cookeryValue1 by rememberSaveable { mutableFloatStateOf(1f) }
    var cookeryValue2 by rememberSaveable { mutableFloatStateOf(1f) }

    val crystoneChecked = rememberSaveable { mutableStateOf(false) }
    var crystoneLevel by rememberSaveable { mutableIntStateOf(0) }
    var crystoneValue by rememberSaveable { mutableFloatStateOf(1f) }

    val handbookChecked = rememberSaveable { mutableStateOf(false) }
    var handbookValue by rememberSaveable { mutableFloatStateOf(1f) }

    val tabs = listOf("单个星级", "一些星级")
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    var stepsWithKeyPointsValue by rememberSaveable { mutableIntStateOf(14) }
    var rangeStepsFirstValue by rememberSaveable { mutableIntStateOf(14) }
    var rangeStepsLastValue by rememberSaveable { mutableIntStateOf(18) }

    // =================================================== 基础信息 ===================================================

    BaseInfoGoldenCard(
        cardImagesBig = CARD_IMAGES_BIG,
        cardImages = CARD_IMAGES,
        cardNames = CARD_NAMES,
        cardDescription = CARD_DESCRIPTION,
    )

    // =================================================== 人话解释 ===================================================

    SmallTitle(text = "人话解释")
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
        pressFeedbackType = PressFeedbackType.Sink,
        showIndication = true,
    ) {
        BasicComponent(
            modifier = Modifier,
            title = CARD_DESCRIPTION[7]
        )
    }

    // =================================================== 相关卡片 ===================================================

    SmallTitle(text = "相关卡片")
    CardDataDetailWindowBottomSheet_1_2_1(
        showBottomSheet1,
        stringResource(R.string.name_card_data_index_1_2_1_0)
    )
    CardDataDetailWindowBottomSheet_1_2_2(
        showBottomSheet2,
        stringResource(R.string.name_card_data_index_1_2_2_0)
    )

    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
        pressFeedbackType = PressFeedbackType.Sink,
    ) {
        BasicComponent(
            modifier = Modifier,
            title = stringResource(RELATED_CARD_NAMES[0]),
            summary = "此卡片是合成本金卡的必要素材",
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

    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
        pressFeedbackType = PressFeedbackType.Sink,
    ) {
        BasicComponent(
            modifier = Modifier,
            title = stringResource(RELATED_CARD_NAMES[1]),
            summary = "此卡片是合成本金卡的必要素材",
            endActions = {
                ImagesRow(
                    imageResIds = listOf(RELATED_CARD_IMAGES[1]),
                    imageWidth = 40,
                    imageHeight = 50,
                )
            },
            onClick = { showBottomSheet2.value = true },
        )
    }

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
                tabs = tabs,
                selectedTabIndex = selectedTabIndex,
                onTabSelected = { selectedTabIndex = it }
            )
            AnimatedVisibility(visible = selectedTabIndex == 0) {
                Slider(
                    value = stepsWithKeyPointsValue.toFloat(),
                    onValueChange = { stepsWithKeyPointsValue = it.roundToInt() },
                    valueRange = 0f..18f,
                    steps = 17,
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
                    valueRange = 0f..18f,
                    steps = 17,
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
                summary = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[0] * cookeryValue1).toInt() * crystoneValue).toInt() + supportValue)
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
                title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[1] * cookeryValue1).toInt() * crystoneValue).toInt() + supportValue),
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
                title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[2] * cookeryValue1).toInt() * crystoneValue).toInt() + supportValue),
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
                title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[3] * cookeryValue1).toInt() * crystoneValue).toInt() + supportValue),
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
                title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[4] * cookeryValue1).toInt() * crystoneValue).toInt() + supportValue),
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
                title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[5] * cookeryValue1).toInt() * crystoneValue).toInt() + supportValue),
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
                title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[6] * cookeryValue1).toInt() * crystoneValue).toInt() + supportValue),
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
                title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[7] * cookeryValue1).toInt() * crystoneValue).toInt() + supportValue),
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
                title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[8] * cookeryValue1).toInt() * crystoneValue).toInt() + supportValue),
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
                title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[9] * cookeryValue1).toInt() * crystoneValue).toInt() + supportValue),
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
                title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[10] * cookeryValue1).toInt() * crystoneValue).toInt() + supportValue),
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
                title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[11] * cookeryValue1).toInt() * crystoneValue).toInt() + supportValue),
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
                title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[12] * cookeryValue1).toInt() * crystoneValue).toInt() + supportValue),
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
                title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[13] * cookeryValue1).toInt() * crystoneValue).toInt() + supportValue),
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
                title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[14] * cookeryValue1).toInt() * crystoneValue).toInt() + supportValue),
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
                title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[15] * cookeryValue1).toInt() * crystoneValue).toInt() + supportValue),
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
                title = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[16] * cookeryValue1).toInt() * crystoneValue).toInt() + supportValue),
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
                summary = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[17] * cookeryValue1).toInt() * crystoneValue).toInt() + supportValue),
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
                summary = CARD_DATA_STRING[0] + "：" + (((CARD_DATA_1[18] * cookeryValue1).toInt() * crystoneValue).toInt() + supportValue),
            )
        }
    }

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
                    "%.2f".format(CARD_DATA_SKILL_1[0] -
                            ((CARD_DATA_SKILL_1[0] * 20 * (1 - cookeryValue2)).toInt() * 0.05) -
                            (((CARD_DATA_SKILL_1[0] - ((CARD_DATA_SKILL_1[0] * 20 * (1 - cookeryValue2)).toInt() * 0.05)) * 20 * (1 - handbookValue)).toInt() * 0.05)
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
                    "%.2f".format(CARD_DATA_SKILL_1[1] -
                            ((CARD_DATA_SKILL_1[1] * 20 * (1 - cookeryValue2)).toInt() * 0.05) -
                            (((CARD_DATA_SKILL_1[1] - ((CARD_DATA_SKILL_1[1] * 20 * (1 - cookeryValue2)).toInt() * 0.05)) * 20 * (1 - handbookValue)).toInt() * 0.05)
                    ) + "、" +
                    "%.2f".format(CARD_DATA_SKILL_1[2] -
                            ((CARD_DATA_SKILL_1[2] * 20 * (1 - cookeryValue2)).toInt() * 0.05) -
                            (((CARD_DATA_SKILL_1[2] - ((CARD_DATA_SKILL_1[2] * 20 * (1 - cookeryValue2)).toInt() * 0.05)) * 20 * (1 - handbookValue)).toInt() * 0.05)
                    ) + "、" +
                    "%.2f".format(CARD_DATA_SKILL_1[3] -
                            ((CARD_DATA_SKILL_1[3] * 20 * (1 - cookeryValue2)).toInt() * 0.05) -
                            (((CARD_DATA_SKILL_1[3] - ((CARD_DATA_SKILL_1[3] * 20 * (1 - cookeryValue2)).toInt() * 0.05)) * 20 * (1 - handbookValue)).toInt() * 0.05)
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
                    "%.2f".format(CARD_DATA_SKILL_1[4] -
                            ((CARD_DATA_SKILL_1[4] * 20 * (1 - cookeryValue2)).toInt() * 0.05) -
                            (((CARD_DATA_SKILL_1[4] - ((CARD_DATA_SKILL_1[4] * 20 * (1 - cookeryValue2)).toInt() * 0.05)) * 20 * (1 - handbookValue)).toInt() * 0.05)
                    ) + "、" +
                    "%.2f".format(CARD_DATA_SKILL_1[5] -
                            ((CARD_DATA_SKILL_1[5] * 20 * (1 - cookeryValue2)).toInt() * 0.05) -
                            (((CARD_DATA_SKILL_1[5] - ((CARD_DATA_SKILL_1[5] * 20 * (1 - cookeryValue2)).toInt() * 0.05)) * 20 * (1 - handbookValue)).toInt() * 0.05)
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
                    "%.2f".format(CARD_DATA_SKILL_1[6] -
                            ((CARD_DATA_SKILL_1[6] * 20 * (1 - cookeryValue2)).toInt() * 0.05) -
                            (((CARD_DATA_SKILL_1[6] - ((CARD_DATA_SKILL_1[6] * 20 * (1 - cookeryValue2)).toInt() * 0.05)) * 20 * (1 - handbookValue)).toInt() * 0.05)
                    ) + "、" +
                    "%.2f".format(CARD_DATA_SKILL_1[7] -
                            ((CARD_DATA_SKILL_1[7] * 20 * (1 - cookeryValue2)).toInt() * 0.05) -
                            (((CARD_DATA_SKILL_1[7] - ((CARD_DATA_SKILL_1[7] * 20 * (1 - cookeryValue2)).toInt() * 0.05)) * 20 * (1 - handbookValue)).toInt() * 0.05)
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
                    "%.2f".format(CARD_DATA_SKILL_1[8] -
                            ((CARD_DATA_SKILL_1[8] * 20 * (1 - cookeryValue2)).toInt() * 0.05) -
                            (((CARD_DATA_SKILL_1[8] - ((CARD_DATA_SKILL_1[8] * 20 * (1 - cookeryValue2)).toInt() * 0.05)) * 20 * (1 - handbookValue)).toInt() * 0.05)
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

    // =================================================== 分解兑换数据 ===================================================

    GoldenCardDecomposeAndGetInfo(
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
                        CARD_DECOMPOSE_AND_GET_IMAGES[18],
                    ),
                    imageWidth = 48,
                    imageHeight = 50,
                )
            },
            onClick = { showCardDecomposeAndGetCalculatorBottomSheet.value = true },
        )
    }

    GoldenCardDecomposeAndGetCalculator(
        showCardDecomposeAndGetCalculatorBottomSheet,
        CARD_DECOMPOSE_AND_GET_IMAGES,
        CARD_DECOMPOSE_DATA,
        CARD_GET_DATA,
        "神谕之石"
    )

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
            summary = "1️⃣终转首轮攻击侧路各额外发射(不占用普通子弹)1发3倍伤害穿透不过火的超级子弹，后间隔3轮发射1次(即第4n+1(n∈N)轮攻击)\n" +
                    "2️⃣存在边路补偿机制，放在1或7路边路无法发射的超级子弹会转到中路发射\n" +
                    "3️⃣三转增加子弹仅50%伤害\n" +
                    "4️⃣超级子弹均不可过火\n" +
                    "5️⃣爱神不转攻击前摇0.4秒，三转后攻击前摇缩短至0\n" +
                    "6️⃣爱神上下贴着布丁类卡片放置会导致侧路子弹无法正常发射",
        )
    }

    DeveloperTips()

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
                    val appState = LocalAppState.current
                    val colorMode = appState.colorMode
                    val darkMode = getDarkMode(colorMode)

                    Card(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .padding(bottom = 12.dp),
                        pressFeedbackType = PressFeedbackType.Sink,
                        showIndication = true,
                        colors = CardDefaults.defaultColors(
                            color = when {
                                (colorMode in 3..5) -> MiuixTheme.colorScheme.secondaryContainer
                                (darkMode) -> Color(0xFF1A3825)
                                else -> Color(0xFFDFFAE4)
                            }
                        )
                    ) {
                        BasicComponent(
                            modifier = Modifier,
                            summary = "攻速增幅计算规则：先计算食神谱增幅，再计算图鉴增幅，得到最终结果。\n注意：每次实际计算的是帧数的缩减量，计算结果向下取整。（你游20帧游戏这一块🙄）"
                        )
                    }

                    // =================================================== 金卡援护 ===================================================

                    Card(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .padding(bottom = 12.dp),
                        pressFeedbackType = PressFeedbackType.Sink,
                    ) {
                        SuperSwitch(
                            title = "金卡援护(" + CARD_SUPPORT_DATA_STRING[0] + ")",
                            summary = "将自动计算出启用后的数据",
                            checked = supportChecked.value,
                            onCheckedChange = { checked ->
                                supportChecked.value = checked
                                supportValue =
                                    if (checked) CARD_SUPPORT_DATA[supportLevel1][supportLevel2] else 0
                            }
                        )
                        AnimatedVisibility(visible = supportChecked.value) {
                            Column {
                                BasicComponent(
                                    modifier = Modifier,
                                    title = CARD_SUPPORT_DATA_STRING[1] + "：$supportLevel1" + "\n" +
                                            CARD_SUPPORT_DATA_STRING[2] + "：$supportLevel2",
                                    onClick = { },
                                )
                                Slider(
                                    value = supportLevel1.toFloat(),
                                    onValueChange = {
                                        supportLevel1 = it.roundToInt()
                                        supportValue =
                                            CARD_SUPPORT_DATA[supportLevel1][supportLevel2]
                                    },
                                    valueRange = 0f..8f,
                                    steps = 7,
                                    hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                                    showKeyPoints = true,
                                    modifier = Modifier
                                        .padding(12.dp),
                                )
                                Slider(
                                    value = supportLevel2.toFloat(),
                                    onValueChange = {
                                        supportLevel2 = it.roundToInt()
                                        supportValue =
                                            CARD_SUPPORT_DATA[supportLevel1][supportLevel2]
                                    },
                                    valueRange = 0f..8f,
                                    steps = 7,
                                    hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                                    showKeyPoints = true,
                                    modifier = Modifier
                                        .padding(12.dp),
                                )
                            }
                        }
                    }

                    // =================================================== 食神谱 ===================================================

                    Card(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .padding(bottom = 12.dp),
                        pressFeedbackType = PressFeedbackType.Sink,
                    ) {
                        BasicComponent(
                            modifier = Modifier,
                            title = "食神谱：汉帝茅台😋",
                            summary = "攻击力提升20%，攻速提升5%",
                            endActions = {
                                ImagesRow(
                                    imageResIds = listOf(R.drawable.card_data_cookery_3_18),
                                    imageWidth = 44,
                                    imageHeight = 44,
                                )
                            },
                            onClick = { },
                        )
                        SuperSwitch(
                            title = "启用该食神谱",
                            summary = "将自动计算出启用后的数据",
                            checked = cookeryChecked.value,
                            onCheckedChange = { checked ->
                                cookeryChecked.value = checked
                                cookeryValue1 = if (checked) 1.2f else 1.0f
                                cookeryValue2 = if (checked) 0.95f else 1.0f
                            }
                        )
                    }

                    // =================================================== 真爱结晶 ===================================================

                    Card(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .padding(bottom = 12.dp),
                        pressFeedbackType = PressFeedbackType.Sink,
                    ) {
                        BasicComponent(
                            modifier = Modifier,
                            title = "真爱结晶🌼",
                            summary = "提升攻击力",
                            endActions = {
                                ImagesRow(
                                    imageResIds = listOf(R.drawable.card_data_index_1_2_4_crystone),
                                    imageWidth = 44,
                                    imageHeight = 44,
                                )
                            },
                            onClick = { },
                        )
                        SuperSwitch(
                            title = "启用该结晶",
                            summary = "将自动计算出启用后的数据",
                            checked = crystoneChecked.value,
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
                        pressFeedbackType = PressFeedbackType.Sink,
                    ) {
                        BasicComponent(
                            modifier = Modifier,
                            title = "图鉴加成📕",
                            summary = "收集10张金卡，攻速提升10%",
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
                            title = "启用图鉴加成",
                            summary = "将自动计算出启用后的数据",
                            checked = handbookChecked.value,
                            onCheckedChange = { checked ->
                                handbookChecked.value = checked
                                handbookValue = if (checked) 0.9f else 1f
                            }
                        )
                    }

                    Spacer(modifier = Modifier.padding(bottom = 48.dp))
                }
            }
        }
    }
}