package com.careful.hyperfvm.compose.ui.components.card_data.card_component

import android.os.Bundle
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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.Saver
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
import com.careful.hyperfvm.compose.card_data_detail.damageInMagmaAndSeawater
import com.careful.hyperfvm.compose.card_data_detail.damageInPoisonGas
import com.careful.hyperfvm.compose.ui.components.card_data.ImagesRow
import com.careful.hyperfvm.compose.ui.theme.getDarkMode
import kotlinx.coroutines.launch
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
    R.string.name_card_data_index_16_1_9_1,
    R.string.name_card_data_index_16_1_9_2,
    R.string.name_card_data_index_16_1_9_3
)

@Stable
private val CARD_IMAGES =  listOf(
    R.drawable.card_data_index_16_1_9_1,
    R.drawable.card_data_index_16_1_9_2,
    R.drawable.card_data_index_16_1_9_3
)

@Stable
private val CARD_IMAGES_BIG =  listOf(
    R.drawable.card_data_index_16_1_9_1_big,
    R.drawable.card_data_index_16_1_9_2_big,
    R.drawable.card_data_index_16_1_9_3_big
)

@Stable
private val CARD_DESCRIPTION = listOf(
    "能力：放置时在本格产生一个泡泡黏住路过的老鼠",
    "能力：体力大幅度提升",
    "能力：一次可种下3个",
    "可强化",
    "看技能",
    "所属分类：承载类",
    "耗能：0",
    "作为副卡：好卡\uD83D\uDE00",
)

@Stable
private val RELATED_CARD_NAMES = listOf(
    R.string.name_card_data_index_6_2_4_2,
    R.string.name_card_data_index_13_3_4_2,
    R.string.name_card_data_index_6_2_5_2,
    R.string.name_card_data_index_6_2_3_2
)

@Stable
private val RELATED_CARD_IMAGES = listOf(
    R.drawable.card_data_index_6_2_4_2,
    R.drawable.card_data_index_13_3_4_2,
    R.drawable.card_data_index_6_2_5_2,
    R.drawable.card_data_index_6_2_3_2
)

@Stable
private val CARD_DATA_STRING = listOf(
    "体力(作为木盘子)", "体力(作为棉花糖)", "在岩浆/海底中的在场时间(分钟)", "在毒气中的在场时间(分钟)"
)

@Stable
private val CARD_DATA_1 = listOf(
    50, 60, 70, 80, 90, 100, 110, 130, 150, 170, 210, 250, 290, 330, 370, 410, 450, -1, -1
)

@Stable
private val CARD_DATA_2 = listOf(
    0.36f, 0.38f, 0.4f, 0.42f, 0.45f, 0.48f, 0.51f, 0.57f, 0.63f, 0.69f, 0.77f, 0.85f, 0.93f, 1.01f, 1.09f, 1.17f, 1.25f, -1f, -1f
)

@Stable
private val CARD_DATA_FUSION_TITLE = listOf(
    "泡泡时间", "附加体力(棉花糖体力单位：万)", "附加体力(棉花糖体力单位：万)"
)

@Stable
private val CARD_DATA_FUSION_STRING = listOf(
    "初级融合-泡泡时间", "深度融合-附加体力(木盘子)", "深度融合-附加体力(棉花糖)", "灵魂融合-附加体力(木盘子)", "灵魂融合-附加体力(棉花糖)"
)

@Stable
private val CARD_DATA_FUSION_1 = listOf(
    -1, 16, 17, 18, 19, 20, 21, 22, 23, 25, 27, 29, 31, 34, 37, 40, 45, -1, -1
)

@Stable
private val CARD_DATA_FUSION_2 = listOf(
    -1, 50, 80, 110, 140, 170, 200, 230, 260, 310, 360, 410, 510, 620, 750, 900, 1100, -1, -1
)

@Stable
private val CARD_DATA_FUSION_3 = listOf(
    -1f, 3.04f, 3.2f, 3.36f, 3.6f, 3.84f, 4.08f, 4.56f, 5.04f, 5.52f, 6.16f, 6.8f, 7.44f, 8.08f, 8.72f, 9.36f, 10f, -1f, -1f
)

@Stable
private val CARD_DATA_FUSION_4 = listOf(
    -1, 50, 80, 110, 140, 170, 200, 230, 260, 310, 360, 410, 510, 620, 750, 900, 1100, -1, -1
)

@Stable
private val CARD_DATA_FUSION_5 = listOf(
    -1f, 0.76f, 0.8f, 0.84f, 0.9f, 0.96f, 1.02f, 1.14f, 1.26f, 1.38f, 1.54f, 1.7f, 1.86f, 2.02f, 2.18f, 2.34f, 2.5f, -1f, -1f
)

@Stable
private val CARD_DATA_SKILL_IMAGES = listOf(
    R.drawable.card_data_index_6_2_4_skill_1,
    R.drawable.card_data_index_6_2_4_skill_2,
    R.drawable.card_data_index_6_2_4_skill_3,
    R.drawable.card_data_index_6_2_4_skill_4
)

@Stable
private val CARD_DATA_SKILL_STRING = listOf(
    "冷却时间"
)

@Stable
private val CARD_DATA_SKILL_1 = listOf(
    7f, 6.5f, 6f, 5.5f, 5f, 4.5f, 4f, 3.5f, 3f
)

@Stable
@Composable
fun CardComponent_16_1_9() {
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

    CardDataDetailWindowBottomSheet_16_1_9(showBottomSheet, cardName)
}

@Composable
fun CardDataDetailWindowBottomSheet_16_1_9(
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
    val coroutineScope = rememberCoroutineScope()
    val lazyListStateSaver = Saver<LazyListState, Bundle>(
        save = { state ->
            Bundle().apply {
                putInt("firstVisibleItemIndex", state.firstVisibleItemIndex)
                putInt("firstVisibleItemScrollOffset", state.firstVisibleItemScrollOffset)
            }
        },
        restore = { bundle ->
            LazyListState(
                bundle.getInt("firstVisibleItemIndex"),
                bundle.getInt("firstVisibleItemScrollOffset")
            )
        }
    )
    val lazyListState = rememberSaveable(saver = lazyListStateSaver) { LazyListState() }

    val showBottomSheet1 = rememberSaveable { mutableStateOf(false) }
    val showBottomSheet2 = rememberSaveable { mutableStateOf(false) }
    val showBottomSheet3 = rememberSaveable { mutableStateOf(false) }
    val showBottomSheet4 = rememberSaveable { mutableStateOf(false) }

    val fusionChecked1 = rememberSaveable { mutableStateOf(false) }
    val fusionChecked2 = rememberSaveable { mutableStateOf(false) }
    var fusionLevel by rememberSaveable { mutableIntStateOf(9) }
    var fusionValue1 by rememberSaveable { mutableIntStateOf(0) }
    var fusionValue2 by rememberSaveable { mutableFloatStateOf(0f) }
    var fusionValue3 by rememberSaveable { mutableIntStateOf(0) }
    var fusionValue4 by rememberSaveable { mutableFloatStateOf(0f) }

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
        state = lazyListState,
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
                    onClick = {
                        coroutineScope.launch {
                            lazyListState.animateScrollToItem(3)
                        }
                    },
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
                    onClick = {
                        coroutineScope.launch {
                            lazyListState.animateScrollToItem(5)
                        }
                    },
                    onLongPress = {  }
                ) {
                    BasicComponent(
                        modifier = Modifier,
                        title = "冷却：" + CARD_DESCRIPTION[4],
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
                    onClick = {  },
                ) {
                    Text(
                        text = "初级融合后",
                        style = MiuixTheme.textStyles.headline1,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = buildAnnotatedString {
                            append("释放泡泡黏住路过陆、地鼠军，泡泡持续 ")
                            withStyle(style = SpanStyle(color = MiuixTheme.colorScheme.primary, fontWeight = FontWeight.Bold)) {
                                append("泡泡时间")
                            }
                            append(" 秒后消失，对自身和右方1格造成灰烬爆炸")
                        },
                        style = MiuixTheme.textStyles.body2,
                        color = MiuixTheme.colorScheme.onSurfaceVariantSummary
                    )
                }
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

            SmallTitle(text = "强化提升：" + CARD_DATA_STRING[0] + "、" + CARD_DATA_STRING[1]
                    + "、" + CARD_DATA_STRING[2] + "、" + CARD_DATA_STRING[3])
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
                        summary = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[0] + fusionValue1 + fusionValue3) + "\n" +
                                CARD_DATA_STRING[1] + "：" + "%.2f".format(CARD_DATA_2[0] + fusionValue2 + fusionValue4) + "\n" +
                                CARD_DATA_STRING[2] + "：" + ((CARD_DATA_2[0] + fusionValue2 + fusionValue4) * 10000 / damageInMagmaAndSeawater / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) } + "\n" +
                                CARD_DATA_STRING[3] + "：" + ((CARD_DATA_2[0] + fusionValue2 + fusionValue4) * 10000 / damageInPoisonGas / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) }
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[1] + fusionValue1 + fusionValue3) + "\n" +
                                CARD_DATA_STRING[1] + "：" + "%.2f".format(CARD_DATA_2[1] + fusionValue2 + fusionValue4),
                        summary = CARD_DATA_STRING[2] + "：" + ((CARD_DATA_2[1] + fusionValue2 + fusionValue4) * 10000 / damageInMagmaAndSeawater / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) } + "\n" +
                                CARD_DATA_STRING[3] + "：" + ((CARD_DATA_2[1] + fusionValue2 + fusionValue4) * 10000 / damageInPoisonGas / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) },
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[2] + fusionValue1 + fusionValue3) + "\n" +
                                CARD_DATA_STRING[1] + "：" + "%.2f".format(CARD_DATA_2[2] + fusionValue2 + fusionValue4),
                        summary = CARD_DATA_STRING[2] + "：" + ((CARD_DATA_2[2] + fusionValue2 + fusionValue4) * 10000 / damageInMagmaAndSeawater / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) } + "\n" +
                                CARD_DATA_STRING[3] + "：" + ((CARD_DATA_2[2] + fusionValue2 + fusionValue4) * 10000 / damageInPoisonGas / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) },
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[3] + fusionValue1 + fusionValue3) + "\n" +
                                CARD_DATA_STRING[1] + "：" + "%.2f".format(CARD_DATA_2[3] + fusionValue2 + fusionValue4),
                        summary = CARD_DATA_STRING[2] + "：" + ((CARD_DATA_2[3] + fusionValue2 + fusionValue4) * 10000 / damageInMagmaAndSeawater / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) } + "\n" +
                                CARD_DATA_STRING[3] + "：" + ((CARD_DATA_2[3] + fusionValue2 + fusionValue4) * 10000 / damageInPoisonGas / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) },
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[4] + fusionValue1 + fusionValue3) + "\n" +
                                CARD_DATA_STRING[1] + "：" + "%.2f".format(CARD_DATA_2[4] + fusionValue2 + fusionValue4),
                        summary = CARD_DATA_STRING[2] + "：" + ((CARD_DATA_2[4] + fusionValue2 + fusionValue4) * 10000 / damageInMagmaAndSeawater / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) } + "\n" +
                                CARD_DATA_STRING[3] + "：" + ((CARD_DATA_2[4] + fusionValue2 + fusionValue4) * 10000 / damageInPoisonGas / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) },
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[5] + fusionValue1 + fusionValue3) + "\n" +
                                CARD_DATA_STRING[1] + "：" + "%.2f".format(CARD_DATA_2[5] + fusionValue2 + fusionValue4),
                        summary = CARD_DATA_STRING[2] + "：" + ((CARD_DATA_2[5] + fusionValue2 + fusionValue4) * 10000 / damageInMagmaAndSeawater / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) } + "\n" +
                                CARD_DATA_STRING[3] + "：" + ((CARD_DATA_2[5] + fusionValue2 + fusionValue4) * 10000 / damageInPoisonGas / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) },
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[6] + fusionValue1 + fusionValue3) + "\n" +
                                CARD_DATA_STRING[1] + "：" + "%.2f".format(CARD_DATA_2[6] + fusionValue2 + fusionValue4),
                        summary = CARD_DATA_STRING[2] + "：" + ((CARD_DATA_2[6] + fusionValue2 + fusionValue4) * 10000 / damageInMagmaAndSeawater / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) } + "\n" +
                                CARD_DATA_STRING[3] + "：" + ((CARD_DATA_2[6] + fusionValue2 + fusionValue4) * 10000 / damageInPoisonGas / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) },
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[7] + fusionValue1 + fusionValue3) + "\n" +
                                CARD_DATA_STRING[1] + "：" + "%.2f".format(CARD_DATA_2[7] + fusionValue2 + fusionValue4),
                        summary = CARD_DATA_STRING[2] + "：" + ((CARD_DATA_2[7] + fusionValue2 + fusionValue4) * 10000 / damageInMagmaAndSeawater / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) } + "\n" +
                                CARD_DATA_STRING[3] + "：" + ((CARD_DATA_2[7] + fusionValue2 + fusionValue4) * 10000 / damageInPoisonGas / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) },
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[8] + fusionValue1 + fusionValue3) + "\n" +
                                CARD_DATA_STRING[1] + "：" + "%.2f".format(CARD_DATA_2[8] + fusionValue2 + fusionValue4),
                        summary = CARD_DATA_STRING[2] + "：" + ((CARD_DATA_2[8] + fusionValue2 + fusionValue4) * 10000 / damageInMagmaAndSeawater / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) } + "\n" +
                                CARD_DATA_STRING[3] + "：" + ((CARD_DATA_2[8] + fusionValue2 + fusionValue4) * 10000 / damageInPoisonGas / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) },
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[9] + fusionValue1 + fusionValue3) + "\n" +
                                CARD_DATA_STRING[1] + "：" + "%.2f".format(CARD_DATA_2[9] + fusionValue2 + fusionValue4),
                        summary = CARD_DATA_STRING[2] + "：" + ((CARD_DATA_2[9] + fusionValue2 + fusionValue4) * 10000 / damageInMagmaAndSeawater / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) } + "\n" +
                                CARD_DATA_STRING[3] + "：" + ((CARD_DATA_2[9] + fusionValue2 + fusionValue4) * 10000 / damageInPoisonGas / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) },
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[10] + fusionValue1 + fusionValue3) + "\n" +
                                CARD_DATA_STRING[1] + "：" + "%.2f".format(CARD_DATA_2[10] + fusionValue2 + fusionValue4),
                        summary = CARD_DATA_STRING[2] + "：" + ((CARD_DATA_2[10] + fusionValue2 + fusionValue4) * 10000 / damageInMagmaAndSeawater / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) } + "\n" +
                                CARD_DATA_STRING[3] + "：" + ((CARD_DATA_2[10] + fusionValue2 + fusionValue4) * 10000 / damageInPoisonGas / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) },
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[11] + fusionValue1 + fusionValue3) + "\n" +
                                CARD_DATA_STRING[1] + "：" + "%.2f".format(CARD_DATA_2[11] + fusionValue2 + fusionValue4),
                        summary = CARD_DATA_STRING[2] + "：" + ((CARD_DATA_2[11] + fusionValue2 + fusionValue4) * 10000 / damageInMagmaAndSeawater / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) } + "\n" +
                                CARD_DATA_STRING[3] + "：" + ((CARD_DATA_2[11] + fusionValue2 + fusionValue4) * 10000 / damageInPoisonGas / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) },
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[12] + fusionValue1 + fusionValue3) + "\n" +
                                CARD_DATA_STRING[1] + "：" + "%.2f".format(CARD_DATA_2[12] + fusionValue2 + fusionValue4),
                        summary = CARD_DATA_STRING[2] + "：" + ((CARD_DATA_2[12] + fusionValue2 + fusionValue4) * 10000 / damageInMagmaAndSeawater / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) } + "\n" +
                                CARD_DATA_STRING[3] + "：" + ((CARD_DATA_2[12] + fusionValue2 + fusionValue4) * 10000 / damageInPoisonGas / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) },
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[13] + fusionValue1 + fusionValue3) + "\n" +
                                CARD_DATA_STRING[1] + "：" + "%.2f".format(CARD_DATA_2[13] + fusionValue2 + fusionValue4),
                        summary = CARD_DATA_STRING[2] + "：" + ((CARD_DATA_2[13] + fusionValue2 + fusionValue4) * 10000 / damageInMagmaAndSeawater / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) } + "\n" +
                                CARD_DATA_STRING[3] + "：" + ((CARD_DATA_2[13] + fusionValue2 + fusionValue4) * 10000 / damageInPoisonGas / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) },
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[14] + fusionValue1 + fusionValue3) + "\n" +
                                CARD_DATA_STRING[1] + "：" + "%.2f".format(CARD_DATA_2[14] + fusionValue2 + fusionValue4),
                        summary = CARD_DATA_STRING[2] + "：" + ((CARD_DATA_2[14] + fusionValue2 + fusionValue4) * 10000 / damageInMagmaAndSeawater / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) } + "\n" +
                                CARD_DATA_STRING[3] + "：" + ((CARD_DATA_2[14] + fusionValue2 + fusionValue4) * 10000 / damageInPoisonGas / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) },
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[15] + fusionValue1 + fusionValue3) + "\n" +
                                CARD_DATA_STRING[1] + "：" + "%.2f".format(CARD_DATA_2[15] + fusionValue2 + fusionValue4),
                        summary = CARD_DATA_STRING[2] + "：" + ((CARD_DATA_2[15] + fusionValue2 + fusionValue4) * 10000 / damageInMagmaAndSeawater / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) } + "\n" +
                                CARD_DATA_STRING[3] + "：" + ((CARD_DATA_2[15] + fusionValue2 + fusionValue4) * 10000 / damageInPoisonGas / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) },
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
                        title = CARD_DATA_STRING[0] + "：" + (CARD_DATA_1[16] + fusionValue1 + fusionValue3) + "\n" +
                                CARD_DATA_STRING[1] + "：" + "%.2f".format(CARD_DATA_2[16] + fusionValue2 + fusionValue4),
                        summary = CARD_DATA_STRING[2] + "：" + ((CARD_DATA_2[16] + fusionValue2 + fusionValue4) * 10000 / damageInMagmaAndSeawater / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) } + "\n" +
                                CARD_DATA_STRING[3] + "：" + ((CARD_DATA_2[16] + fusionValue2 + fusionValue4) * 10000 / damageInPoisonGas / 60)
                            .let { if (it >= 20) "20" else "%.2f".format(it) },
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
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[1] + "\n" +
                                CARD_DATA_FUSION_STRING[4] + "：" + CARD_DATA_FUSION_5[1],
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
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[2] + "\n" +
                                CARD_DATA_FUSION_STRING[4] + "：" + CARD_DATA_FUSION_5[2],
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
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[3] + "\n" +
                                CARD_DATA_FUSION_STRING[4] + "：" + CARD_DATA_FUSION_5[3],
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
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[4] + "\n" +
                                CARD_DATA_FUSION_STRING[4] + "：" + CARD_DATA_FUSION_5[4],
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
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[5] + "\n" +
                                CARD_DATA_FUSION_STRING[4] + "：" + CARD_DATA_FUSION_5[5],
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
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[6] + "\n" +
                                CARD_DATA_FUSION_STRING[4] + "：" + CARD_DATA_FUSION_5[6],
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
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[7] + "\n" +
                                CARD_DATA_FUSION_STRING[4] + "：" + CARD_DATA_FUSION_5[7],
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
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[8] + "\n" +
                                CARD_DATA_FUSION_STRING[4] + "：" + CARD_DATA_FUSION_5[8],
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
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[9] + "\n" +
                                CARD_DATA_FUSION_STRING[4] + "：" + CARD_DATA_FUSION_5[9],
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
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[10] + "\n" +
                                CARD_DATA_FUSION_STRING[4] + "：" + CARD_DATA_FUSION_5[10],
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
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[11] + "\n" +
                                CARD_DATA_FUSION_STRING[4] + "：" + CARD_DATA_FUSION_5[11],
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
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[12] + "\n" +
                                CARD_DATA_FUSION_STRING[4] + "：" + CARD_DATA_FUSION_5[12],
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
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[13] + "\n" +
                                CARD_DATA_FUSION_STRING[4] + "：" + CARD_DATA_FUSION_5[13],
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
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[14] + "\n" +
                                CARD_DATA_FUSION_STRING[4] + "：" + CARD_DATA_FUSION_5[14],
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
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[15] + "\n" +
                                CARD_DATA_FUSION_STRING[4] + "：" + CARD_DATA_FUSION_5[15],
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
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[16] + "\n" +
                                CARD_DATA_FUSION_STRING[4] + "：" + CARD_DATA_FUSION_5[16],
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
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[17] + "\n" +
                                CARD_DATA_FUSION_STRING[4] + "：" + CARD_DATA_FUSION_5[17],
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
                                CARD_DATA_FUSION_STRING[3] + "：" + CARD_DATA_FUSION_4[18] + "\n" +
                                CARD_DATA_FUSION_STRING[4] + "：" + CARD_DATA_FUSION_5[18],
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
                ) {
                    Text(
                        text = "附加说明\uD83E\uDD28",
                        style = MiuixTheme.textStyles.headline1,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = buildAnnotatedString {
                            append("1️⃣死亡产生3×3十字范围灰烬爆炸\n2️⃣岩浆&海水的伤害为每秒10点\n3️⃣毒气的伤害为每秒100点\n4️⃣作为棉花糖使用时，在场 ")
                            withStyle(style = SpanStyle(color = MiuixTheme.colorScheme.primary, fontWeight = FontWeight.Bold)) {
                                append("20分钟")
                            }
                            append(" 后自动销毁")
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

                    // =================================================== 真爱结晶 ===================================================

                    // ===================================================== 品阶 =====================================================

                    Card(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .padding(bottom = 12.dp),
                    ) {
                        SwitchPreference(
                            title = "将深度附加体力加入计算",
                            summary = "将自动计算出启用后的数据",
                            checked = fusionChecked1.value,
                            onCheckedChange = { checked ->
                                fusionChecked1.value = checked
                                fusionValue1 = if (checked) CARD_DATA_FUSION_2[fusionLevel] else 0
                                fusionValue2 = if (checked) CARD_DATA_FUSION_3[fusionLevel] else 0f
                                fusionValue3 = if (checked && fusionChecked2.value) CARD_DATA_FUSION_4[fusionLevel] else 0
                                fusionValue4 = if (checked && fusionChecked2.value) CARD_DATA_FUSION_5[fusionLevel] else 0f
                            }
                        )
                        AnimatedVisibility(visible = fusionChecked1.value) {
                            SwitchPreference(
                                title = "将灵魂附加体力加入计算",
                                summary = "将自动计算出启用后的数据",
                                checked = fusionChecked2.value,
                                onCheckedChange = { checked ->
                                    fusionChecked2.value = checked
                                    fusionValue3 = if (checked) CARD_DATA_FUSION_4[fusionLevel] else 0
                                    fusionValue4 = if (checked) CARD_DATA_FUSION_5[fusionLevel] else 0f
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
                                        fusionValue1 = CARD_DATA_FUSION_2[fusionLevel]
                                        fusionValue2 = CARD_DATA_FUSION_3[fusionLevel]
                                        fusionValue3 = if (fusionChecked2.value) CARD_DATA_FUSION_4[fusionLevel] else 0
                                        fusionValue4 = if (fusionChecked2.value) CARD_DATA_FUSION_5[fusionLevel] else 0f
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