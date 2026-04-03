package com.careful.hyperfvm.compose.ui.pages.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.careful.hyperfvm.compose.R
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_16_1_1
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_16_1_2
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_16_1_3
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_16_1_4
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_16_1_5
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_16_1_6
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_16_1_7
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_16_1_8
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_16_1_9
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_1_1_1
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_1_1_2
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_1_1_3
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_1_1_4
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_1_1_5
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_1_1_6
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_1_1_7
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_1_1_8
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_1_2_1
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_1_2_2
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_1_2_3
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_1_2_4
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_1_2_5
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_1_2_6
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_1_2_7
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_1_3_1
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_1_3_2
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_1_3_3
import com.careful.hyperfvm.compose.blur.haze.haze
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_2_1_1
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_2_1_2
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_2_1_3
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_2_1_4
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_2_1_5
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_2_1_6
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_2_2_1
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_2_2_11
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_2_2_2
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_2_2_3
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_2_2_4
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_2_2_5
import com.careful.hyperfvm.compose.ui.components.card_data.card_component.CardComponent_2_2_8
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import top.yukonga.miuix.kmp.basic.InputField
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SearchBar
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.overScrollVertical
import top.yukonga.miuix.kmp.utils.scrollEndHaptic

@Composable
fun CardDataIndexPage(
    outPaddingValues: PaddingValues,
) {
    val hazeConfig = haze()
    val scrollBehavior = MiuixScrollBehavior()

    // 搜索关键词
    var searchText by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                TopAppBar(
                    modifier = Modifier
                        .hazeEffect(hazeConfig.hazeState) {
                            style = hazeConfig.hazeStyle
                            blurRadius = 25.dp
                            noiseFactor = 0f
                        },
                    color = Color.Transparent,
                    title = stringResource(R.string.title_top_bar_card_data_index),
                    scrollBehavior = scrollBehavior,
                )

                SearchBar(
                    modifier = Modifier
                        .hazeEffect(hazeConfig.hazeState) {
                            style = hazeConfig.hazeStyle
                            blurRadius = 25.dp
                            noiseFactor = 0f
                        }
                        .padding(vertical = 12.dp),
                    inputField = {
                        InputField(
                            query = searchText,
                            onQueryChange = { searchText = it },
                            onSearch = { /* 处理搜索操作 */ },
                            expanded = expanded,
                            onExpandedChange = { expanded = it },
                            label = "搜索您要查询的防御卡"
                        )
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    outsideEndAction = {
                        Text(
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .clickable(
                                    interactionSource = null,
                                    indication = null
                                ) {
                                    expanded = false
                                    searchText = ""
                                },
                            text = "取消",
                            color = MiuixTheme.colorScheme.primary
                        )
                    }
                ) {}
            }
        },
    ) { paddingValues ->

        // 先根据搜索词，确定需要显示的卡片
        // 注意如果某一组所有卡片都不显示时，对应的标题也要隐藏
        val visibility1 = mutableListOf(
            // 第0个占位用，不用管
            mutableListOf(true),
            // 从第1个开始，这样更方便
            mutableListOf(true, true, true, true, true, true, true, true, true),
            mutableListOf(true, true, true, true, true, true, true, true),
            mutableListOf(true, true, true, true),
        )
        val visibility2 = mutableListOf(
            // 第0个占位用，不用管
            mutableListOf(true),
            // 从第1个开始，这样更方便
            mutableListOf(true, true, true, true, true, true, true),
            mutableListOf(true, true, true, true, true, true, true, true, true, true, true, true, true, true),
            mutableListOf(true, true, true, true, true),
        )
        val visibility16 = mutableListOf(
            // 第0个占位用，不用管
            mutableListOf(true),
            // 从第1个开始，这样更方便
            mutableListOf(true, true, true, true, true, true, true, true, true, true),
        )

        if (!(expanded && (stringResource(R.string.name_card_data_index_1_1_1_0).contains(searchText) || stringResource(R.string.name_card_data_index_1_1_1_1).contains(searchText) || stringResource(R.string.name_card_data_index_1_1_1_2).contains(searchText)) || !expanded))
            visibility1[1][1] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_1_1_2_0).contains(searchText) || stringResource(R.string.name_card_data_index_1_1_2_1).contains(searchText) || stringResource(R.string.name_card_data_index_1_1_2_2).contains(searchText)) || !expanded))
            visibility1[1][2] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_1_1_3_0).contains(searchText) || stringResource(R.string.name_card_data_index_1_1_3_1).contains(searchText) || stringResource(R.string.name_card_data_index_1_1_3_2).contains(searchText)) || !expanded))
            visibility1[1][3] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_1_1_4_0).contains(searchText) || stringResource(R.string.name_card_data_index_1_1_4_1).contains(searchText) || stringResource(R.string.name_card_data_index_1_1_4_2).contains(searchText) || stringResource(R.string.name_card_data_index_1_1_4_3).contains(searchText)) || !expanded))
            visibility1[1][4] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_1_1_5_0).contains(searchText) || stringResource(R.string.name_card_data_index_1_1_5_1).contains(searchText) || stringResource(R.string.name_card_data_index_1_1_5_2).contains(searchText)) || !expanded))
            visibility1[1][5] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_1_1_6_0).contains(searchText) || stringResource(R.string.name_card_data_index_1_1_6_1).contains(searchText) || stringResource(R.string.name_card_data_index_1_1_6_2).contains(searchText)) || !expanded))
            visibility1[1][6] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_1_1_7_0).contains(searchText) || stringResource(R.string.name_card_data_index_1_1_7_1).contains(searchText) || stringResource(R.string.name_card_data_index_1_1_7_2).contains(searchText)) || !expanded))
            visibility1[1][7] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_1_1_8_0).contains(searchText) || stringResource(R.string.name_card_data_index_1_1_8_1).contains(searchText) || stringResource(R.string.name_card_data_index_1_1_8_2).contains(searchText)) || !expanded))
            visibility1[1][8] = false
        if (!visibility1[1][1] && !visibility1[1][2] && !visibility1[1][3] && !visibility1[1][4] && !visibility1[1][5] && !visibility1[1][6] && !visibility1[1][7] && !visibility1[1][8])
            visibility1[1][0] = false

        if (!(expanded && (stringResource(R.string.name_card_data_index_1_2_1_0).contains(searchText) || stringResource(R.string.name_card_data_index_1_2_1_1).contains(searchText) || stringResource(R.string.name_card_data_index_1_2_1_2).contains(searchText)) || !expanded))
            visibility1[2][1] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_1_2_2_0).contains(searchText) || stringResource(R.string.name_card_data_index_1_2_2_1).contains(searchText) || stringResource(R.string.name_card_data_index_1_2_2_2).contains(searchText)) || !expanded))
            visibility1[2][2] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_1_2_3_0).contains(searchText) || stringResource(R.string.name_card_data_index_1_2_3_1).contains(searchText) || stringResource(R.string.name_card_data_index_1_2_3_2).contains(searchText)) || !expanded))
            visibility1[2][3] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_1_2_4_0).contains(searchText) || stringResource(R.string.name_card_data_index_1_2_4_1).contains(searchText) || stringResource(R.string.name_card_data_index_1_2_4_2).contains(searchText) || stringResource(R.string.name_card_data_index_1_2_4_3).contains(searchText)) || !expanded))
            visibility1[2][4] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_1_2_5_0).contains(searchText) || stringResource(R.string.name_card_data_index_1_2_5_1).contains(searchText) || stringResource(R.string.name_card_data_index_1_2_5_2).contains(searchText)) || !expanded))
            visibility1[2][5] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_1_2_6_0).contains(searchText) || stringResource(R.string.name_card_data_index_1_2_6_1).contains(searchText) || stringResource(R.string.name_card_data_index_1_2_6_2).contains(searchText)) || !expanded))
            visibility1[2][6] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_1_2_7_0).contains(searchText) || stringResource(R.string.name_card_data_index_1_2_7_1).contains(searchText) || stringResource(R.string.name_card_data_index_1_2_7_2).contains(searchText)) || !expanded))
            visibility1[2][7] = false
        if (!visibility1[2][1] && !visibility1[2][2] && !visibility1[2][3] && !visibility1[2][4] && !visibility1[2][5] && !visibility1[2][6] && !visibility1[2][7])
            visibility1[2][0] = false

        if (!(expanded && (stringResource(R.string.name_card_data_index_1_3_1_0).contains(searchText) || stringResource(R.string.name_card_data_index_1_3_1_1).contains(searchText) || stringResource(R.string.name_card_data_index_1_3_1_2).contains(searchText)) || !expanded))
            visibility1[3][1] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_1_3_2_0).contains(searchText) || stringResource(R.string.name_card_data_index_1_3_2_1).contains(searchText) || stringResource(R.string.name_card_data_index_1_3_2_2).contains(searchText)) || !expanded))
            visibility1[3][2] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_1_3_3_0).contains(searchText) || stringResource(R.string.name_card_data_index_1_3_3_1).contains(searchText) || stringResource(R.string.name_card_data_index_1_3_3_2).contains(searchText)) || !expanded))
            visibility1[3][3] = false
        if (!visibility1[3][1] && !visibility1[3][2] && !visibility1[3][3])
            visibility1[3][0] = false

        if (!(expanded && (stringResource(R.string.name_card_data_index_2_1_1_0).contains(searchText) || stringResource(R.string.name_card_data_index_2_1_1_1).contains(searchText) || stringResource(R.string.name_card_data_index_2_1_1_2).contains(searchText)) || !expanded))
            visibility2[1][1] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_2_1_2_0).contains(searchText) || stringResource(R.string.name_card_data_index_2_1_2_1).contains(searchText) || stringResource(R.string.name_card_data_index_2_1_2_2).contains(searchText)) || !expanded))
            visibility2[1][2] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_2_1_3_0).contains(searchText) || stringResource(R.string.name_card_data_index_2_1_3_1).contains(searchText) || stringResource(R.string.name_card_data_index_2_1_3_2).contains(searchText) || stringResource(R.string.name_card_data_index_2_1_3_3).contains(searchText)) || !expanded))
            visibility2[1][3] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_2_1_4_0).contains(searchText) || stringResource(R.string.name_card_data_index_2_1_4_1).contains(searchText) || stringResource(R.string.name_card_data_index_2_1_4_2).contains(searchText)) || !expanded))
            visibility2[1][4] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_2_1_5_0).contains(searchText) || stringResource(R.string.name_card_data_index_2_1_5_1).contains(searchText) || stringResource(R.string.name_card_data_index_2_1_5_2).contains(searchText)) || !expanded))
            visibility2[1][5] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_2_1_6_0).contains(searchText) || stringResource(R.string.name_card_data_index_2_1_6_1).contains(searchText) || stringResource(R.string.name_card_data_index_2_1_6_2).contains(searchText)) || !expanded))
            visibility2[1][6] = false
        if (!visibility2[1][1] && !visibility2[1][2] && !visibility2[1][3] && !visibility2[1][4] && !visibility2[1][5] && !visibility2[1][6])
            visibility2[1][0] = false

        if (!(expanded && (stringResource(R.string.name_card_data_index_2_2_1_0).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_1_1).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_1_2).contains(searchText)) || !expanded))
            visibility2[2][1] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_2_2_2_0).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_2_1).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_2_2).contains(searchText)) || !expanded))
            visibility2[2][2] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_2_2_3_0).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_3_1).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_3_2).contains(searchText)) || !expanded))
            visibility2[2][3] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_2_2_4_0).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_4_1).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_4_2).contains(searchText)) || !expanded))
            visibility2[2][4] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_2_2_5_0).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_5_1).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_5_2).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_5_3).contains(searchText)) || !expanded))
            visibility2[2][5] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_2_2_6_0).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_6_1).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_6_2).contains(searchText)) || !expanded))
            visibility2[2][6] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_2_2_7_0).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_7_1).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_7_2).contains(searchText)) || !expanded))
            visibility2[2][7] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_2_2_8_0).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_8_1).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_8_2).contains(searchText)) || !expanded))
            visibility2[2][8] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_2_2_9_0).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_9_1).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_9_2).contains(searchText)) || !expanded))
            visibility2[2][9] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_2_2_10_0).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_10_1).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_10_2).contains(searchText)) || !expanded))
            visibility2[2][10] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_2_2_11_0).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_11_1).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_11_2).contains(searchText)) || !expanded))
            visibility2[2][11] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_2_2_12_0).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_12_1).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_12_2).contains(searchText)) || !expanded))
            visibility2[2][12] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_2_2_13_0).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_13_1).contains(searchText) || stringResource(R.string.name_card_data_index_2_2_13_2).contains(searchText)) || !expanded))
            visibility2[2][13] = false
        if (!visibility2[2][1] && !visibility2[2][2] && !visibility2[2][3] && !visibility2[2][4] && !visibility2[2][5] && !visibility2[2][6] && !visibility2[2][7] && !visibility2[2][8] && !visibility2[2][9] && !visibility2[2][10] && !visibility2[2][11] && !visibility2[2][12] && !visibility2[2][13])
            visibility2[2][0] = false

        if (!(expanded && (stringResource(R.string.name_card_data_index_16_1_1_1).contains(searchText) || stringResource(R.string.name_card_data_index_16_1_1_2).contains(searchText) || stringResource(R.string.name_card_data_index_16_1_1_3).contains(searchText)) || !expanded))
            visibility16[1][1] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_16_1_2_1).contains(searchText) || stringResource(R.string.name_card_data_index_16_1_2_2).contains(searchText) || stringResource(R.string.name_card_data_index_16_1_2_3).contains(searchText)) || !expanded))
            visibility16[1][2] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_16_1_3_1).contains(searchText) || stringResource(R.string.name_card_data_index_16_1_3_2).contains(searchText) || stringResource(R.string.name_card_data_index_16_1_3_3).contains(searchText)) || !expanded))
            visibility16[1][3] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_16_1_4_1).contains(searchText) || stringResource(R.string.name_card_data_index_16_1_4_2).contains(searchText) || stringResource(R.string.name_card_data_index_16_1_4_3).contains(searchText)) || !expanded))
            visibility16[1][4] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_16_1_5_1).contains(searchText) || stringResource(R.string.name_card_data_index_16_1_5_2).contains(searchText) || stringResource(R.string.name_card_data_index_16_1_5_3).contains(searchText)) || !expanded))
            visibility16[1][5] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_16_1_6_1).contains(searchText) || stringResource(R.string.name_card_data_index_16_1_6_2).contains(searchText) || stringResource(R.string.name_card_data_index_16_1_6_3).contains(searchText)) || !expanded))
            visibility16[1][6] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_16_1_7_1).contains(searchText) || stringResource(R.string.name_card_data_index_16_1_7_2).contains(searchText) || stringResource(R.string.name_card_data_index_16_1_7_3).contains(searchText)) || !expanded))
            visibility16[1][7] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_16_1_8_1).contains(searchText) || stringResource(R.string.name_card_data_index_16_1_8_2).contains(searchText) || stringResource(R.string.name_card_data_index_16_1_8_3).contains(searchText)) || !expanded))
            visibility16[1][8] = false
        if (!(expanded && (stringResource(R.string.name_card_data_index_16_1_9_1).contains(searchText) || stringResource(R.string.name_card_data_index_16_1_9_2).contains(searchText) || stringResource(R.string.name_card_data_index_16_1_9_3).contains(searchText)) || !expanded))
            visibility16[1][9] = false
        if (!visibility16[1][1] && !visibility16[1][2] && !visibility16[1][3] && !visibility16[1][4] && !visibility16[1][5] && !visibility16[1][6] && !visibility16[1][7] && !visibility16[1][8] && !visibility16[1][9])
            visibility16[1][0] = false

        LazyColumn(
            modifier = Modifier
                .scrollEndHaptic()
                .overScrollVertical()
                .fillMaxSize()
                .hazeSource(state = hazeConfig.hazeState) // 给haze效果提供源
                .nestedScroll(scrollBehavior.nestedScrollConnection), // 关联TopAppBar滚动
            overscrollEffect = null,
            contentPadding = PaddingValues(
                top = paddingValues.calculateTopPadding() + 12.dp,
                bottom = outPaddingValues.calculateBottomPadding() + 12.dp,
            )
        ) {
            item(key = "card_1_1") {
                AnimatedVisibility(visibility1[1][0]) {
                    SmallTitle(text = stringResource(R.string.title_card_data_index_1_1))
                }
            }

            item(key = "card_1_1_1") {
                AnimatedVisibility(visibility1[1][1]) {
                    CardComponent_1_1_1()
                }
            }
            item(key = "card_1_1_2") {
                AnimatedVisibility(visibility1[1][2]) {
                    CardComponent_1_1_2()
                }
            }
            item(key = "card_1_1_3") {
                AnimatedVisibility(visibility1[1][3]) {
                    CardComponent_1_1_3()
                }
            }
            item(key = "card_1_1_4") {
                AnimatedVisibility(visibility1[1][4]) {
                    CardComponent_1_1_4()
                }
            }
            item(key = "card_1_1_5") {
                AnimatedVisibility(visibility1[1][5]) {
                    CardComponent_1_1_5()
                }
            }
            item(key = "card_1_1_6") {
                AnimatedVisibility(visibility1[1][6]) {
                    CardComponent_1_1_6()
                }
            }
            item(key = "card_1_1_7") {
                AnimatedVisibility(visibility1[1][7]) {
                    CardComponent_1_1_7()
                }
            }
            item(key = "card_1_1_8") {
                AnimatedVisibility(visibility1[1][8]) {
                    CardComponent_1_1_8()
                }
            }

            item(key = "card_1_2") {
                AnimatedVisibility(visibility1[2][0]) {
                    SmallTitle(text = stringResource(R.string.title_card_data_index_1_2))
                }
            }

            item(key = "card_1_2_1") {
                AnimatedVisibility(visibility1[2][1]) {
                    CardComponent_1_2_1()
                }
            }

            item(key = "card_1_2_2") {
                AnimatedVisibility(visibility1[2][2]) {
                    CardComponent_1_2_2()
                }
            }

            item(key = "card_1_2_3") {
                AnimatedVisibility(visibility1[2][3]) {
                    CardComponent_1_2_3()
                }
            }

            item(key = "card_1_2_4") {
                AnimatedVisibility(visibility1[2][4]) {
                    CardComponent_1_2_4()
                }
            }

            item(key = "card_1_2_5") {
                AnimatedVisibility(visibility1[2][5]) {
                    CardComponent_1_2_5()
                }
            }

            item(key = "card_1_2_6") {
                AnimatedVisibility(visibility1[2][6]) {
                    CardComponent_1_2_6()
                }
            }

            item(key = "card_1_2_7") {
                AnimatedVisibility(visibility1[2][7]) {
                    CardComponent_1_2_7()
                }
            }

            item(key = "card_1_3") {
                AnimatedVisibility(visibility1[3][0]) {
                    SmallTitle(text = stringResource(R.string.title_card_data_index_1_3))
                }
            }

            item(key = "card_1_3_1") {
                AnimatedVisibility(visibility1[3][1]) {
                    CardComponent_1_3_1()
                }
            }

            item(key = "card_1_3_2") {
                AnimatedVisibility(visibility1[3][2]) {
                    CardComponent_1_3_2()
                }
            }

            item(key = "card_1_3_3") {
                AnimatedVisibility(visibility1[3][3]) {
                    CardComponent_1_3_3()
                }
            }

            item(key = "card_2_1") {
                AnimatedVisibility(visibility2[1][0]) {
                    SmallTitle(text = stringResource(R.string.title_card_data_index_2_1))
                }
            }

            item(key = "card_2_1_1") {
                AnimatedVisibility(visibility2[1][1]) {
                    CardComponent_2_1_1()
                }
            }

            item(key = "card_2_1_2") {
                AnimatedVisibility(visibility2[1][2]) {
                    CardComponent_2_1_2()
                }
            }

            item(key = "card_2_1_3") {
                AnimatedVisibility(visibility2[1][3]) {
                    CardComponent_2_1_3()
                }
            }

            item(key = "card_2_1_4") {
                AnimatedVisibility(visibility2[1][4]) {
                    CardComponent_2_1_4()
                }
            }

            item(key = "card_2_1_5") {
                AnimatedVisibility(visibility2[1][5]) {
                    CardComponent_2_1_5()
                }
            }

            item(key = "card_2_1_6") {
                AnimatedVisibility(visibility2[1][6]) {
                    CardComponent_2_1_6()
                }
            }

            item(key = "card_2_2") {
                AnimatedVisibility(visibility2[2][0]) {
                    SmallTitle(text = stringResource(R.string.title_card_data_index_2_2))
                }
            }

            item(key = "card_2_2_1") {
                AnimatedVisibility(visibility2[2][1]) {
                    CardComponent_2_2_1()
                }
            }

            item(key = "card_2_2_2") {
                AnimatedVisibility(visibility2[2][2]) {
                    CardComponent_2_2_2()
                }
            }

            item(key = "card_2_2_3") {
                AnimatedVisibility(visibility2[2][3]) {
                    CardComponent_2_2_3()
                }
            }

            item(key = "card_2_2_4") {
                AnimatedVisibility(visibility2[2][4]) {
                    CardComponent_2_2_4()
                }
            }

            item(key = "card_2_2_5") {
                AnimatedVisibility(visibility2[2][5]) {
                    CardComponent_2_2_5()
                }
            }

            item(key = "card_2_2_8") {
                AnimatedVisibility(visibility2[2][8]) {
                    CardComponent_2_2_8()
                }
            }

            item(key = "card_2_2_11") {
                AnimatedVisibility(visibility2[2][11]) {
                    CardComponent_2_2_11()
                }
            }

            item(key = "card_16_1") {
                AnimatedVisibility(visibility16[1][0]) {
                    SmallTitle(text = stringResource(R.string.title_card_data_index_16_1))
                }
            }

            item(key = "card_16_1_1") {
                AnimatedVisibility(visibility16[1][1]) {
                    CardComponent_16_1_1()
                }
            }
            item(key = "card_16_1_2") {
                AnimatedVisibility(visibility16[1][2]) {
                    CardComponent_16_1_2()
                }
            }
            item(key = "card_16_1_3") {
                AnimatedVisibility(visibility16[1][3]) {
                    CardComponent_16_1_3()
                }
            }
            item(key = "card_16_1_4") {
                AnimatedVisibility(visibility16[1][4]) {
                    CardComponent_16_1_4()
                }
            }
            item(key = "card_16_1_5") {
                AnimatedVisibility(visibility16[1][5]) {
                    CardComponent_16_1_5()
                }
            }
            item(key = "card_16_1_6") {
                AnimatedVisibility(visibility16[1][6]) {
                    CardComponent_16_1_6()
                }
            }
            item(key = "card_16_1_7") {
                AnimatedVisibility(visibility16[1][7]) {
                    CardComponent_16_1_7()
                }
            }
            item(key = "card_16_1_8") {
                AnimatedVisibility(visibility16[1][8]) {
                    CardComponent_16_1_8()
                }
            }
            item(key = "card_16_1_9") {
                AnimatedVisibility(visibility16[1][9]) {
                    CardComponent_16_1_9()
                }
            }
        }
    }
}