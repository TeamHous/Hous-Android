package com.hous.hous_aos.ui.newrules

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hous.hous_aos.R
import com.hous.hous_aos.data.model.response.NewRulesResponse

data class NewRulesUiState(
    val ruleName: String = "",
    val categoryName: String = "",
    val categoryId: String = "",
    val notificationState: Boolean = false,
    val isKeyRules: Boolean = false,
    val ruleCategory: List<NewRulesResponse.Category> =
        listOf(
            NewRulesResponse.Category("1", "청소기"),
            NewRulesResponse.Category("2", "분리수거"),
            NewRulesResponse.Category("3", "세탁기"),
            NewRulesResponse.Category("4", "물 주기"),
        ),
    val homies: List<NewRulesResponse.Homie> =
        listOf(
            NewRulesResponse.Homie("1", "강원용", "RED"),
            NewRulesResponse.Homie("2", "이영주", "BLUE"),
            NewRulesResponse.Homie("3", "이준원", "YELLOW"),
            NewRulesResponse.Homie("4", "최인영", "GREEN"),
            NewRulesResponse.Homie("5", "최소현", "PURPLE"),
        )
)

@Composable
fun NewRulesScreen() {
    val uiState = remember { mutableStateOf(NewRulesUiState()) }
    val checkBoxState: MutableState<State> = remember { mutableStateOf(State.UNSELECT) }
    val isRuleAddButton = remember { mutableStateOf(false) }
    val test = remember {
        mutableStateOf(
            listOf(
                Pair(
                    mutableStateOf("담당자 없음"),
                    listOf(
                        Pair("월", mutableStateOf(State.UNSELECT)),
                        Pair("화", mutableStateOf(State.UNSELECT)),
                        Pair("수", mutableStateOf(State.UNSELECT)),
                        Pair("목", mutableStateOf(State.UNSELECT)),
                        Pair("금", mutableStateOf(State.UNSELECT)),
                        Pair("토", mutableStateOf(State.UNSELECT)),
                        Pair("일", mutableStateOf(State.UNSELECT)),
                    )
                )
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.hous_blue_bg))
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn {
            item {
                Spacer(modifier = Modifier.size(50.dp))

                NewRulesToolbar(uiState)
                Spacer(modifier = Modifier.size(27.dp))

                Text(
                    text = stringResource(id = R.string.new_rules_rule_name),
                    fontStyle = FontStyle(R.style.B2),
                    color = colorResource(id = R.color.hous_blue)
                )
                Spacer(modifier = Modifier.size(8.dp))

                NewRulesTextField(uiState)
                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    text = stringResource(id = R.string.new_rules_category),
                    fontStyle = FontStyle(R.style.B2),
                    color = colorResource(id = R.color.hous_blue)
                )
                Spacer(modifier = Modifier.size(8.dp))

                CategoryBox(10.dp, uiState)
                Spacer(modifier = Modifier.size(16.dp))

                NewRulesCheckBox(checkBoxState, test.value[0].second)
                Spacer(modifier = Modifier.size(4.dp))

                Row {
                    Spacer(Modifier.size(30.dp))
                    Text(
                        text = stringResource(id = R.string.new_rules_description),
                        fontStyle = FontStyle(R.style.Description),
                        color = colorResource(id = R.color.g_4)
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))

                Text(
                    text = stringResource(id = R.string.new_rules_manager_setting),
                    fontStyle = FontStyle(R.style.B2),
                    color = colorResource(id = R.color.hous_blue)
                )
                Spacer(modifier = Modifier.size(12.dp))
            }

            itemsIndexed(test.value) { index, value ->
                NewRulesManagerItem(
                    test,
                    value.second,
                    index,
                    checkBoxState,
                    test.value.size,
                    uiState
                )
                Spacer(modifier = Modifier.size(16.dp))
            }

            item {
                NewRulesAddMangerButton(test)
                Spacer(modifier = Modifier.size(16.dp))
                NewRulesAddRuleButton()
            }
        }
    }
}

@Composable
fun NewRulesToolbar(
    uiState: MutableState<NewRulesUiState>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = ""
        )
        Text(
            text = stringResource(id = R.string.new_rules_title),
            fontStyle = FontStyle(R.style.B1)
        )
        if (uiState.value.notificationState) {
            Image(
                painter = painterResource(id = R.drawable.ic_alarmon),
                contentDescription = "",
                modifier = Modifier.clickable {
                    uiState.value = uiState.value.copy(notificationState = false)
                }
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_alarmoff),
                contentDescription = "",
                modifier = Modifier.clickable {
                    uiState.value = uiState.value.copy(notificationState = true)
                }
            )
        }
    }
}

@Composable
fun NewRulesTextField(
    uiState: MutableState<NewRulesUiState>
) {
    val maxChar = 16
    BasicTextField(
        value = uiState.value.ruleName,
        onValueChange = {
            if (it.length < maxChar) uiState.value = uiState.value.copy(ruleName = it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(id = R.color.white))
            .height(37.dp)
            .padding(vertical = 8.dp, horizontal = 15.dp),
        textStyle = TextStyle(
            color = colorResource(id = R.color.black),
            fontStyle = FontStyle(R.style.B2)
        ),
        maxLines = 1,
        singleLine = true,
        cursorBrush = SolidColor(colorResource(id = R.color.hous_blue))
    )
}

@Composable
private fun CategoryBox(
    radius: Dp,
    uiState: MutableState<NewRulesUiState>,
    checkBoxState: MutableState<State> = mutableStateOf(State.BLOCK),
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(36.dp)
            .clip(shape = RoundedCornerShape(radius))
            .background(colorResource(id = R.color.white))
            .padding(horizontal = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterStart),
        ) {
            Text(
                text = uiState.value.categoryName,
                fontStyle = FontStyle(R.style.B2),
                color = colorResource(id = R.color.g_6)
            )
        }
        Box(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterEnd),
        ) {
            CategoryDropDownMenu(uiState, checkBoxState)
        }
    }
}

@Composable
private fun CategoryDropDownMenu(
    uiState: MutableState<NewRulesUiState>,
    checkBoxState: MutableState<State>,
) {
    if (checkBoxState.value != State.SELECT) {
        var isExpanded by remember { mutableStateOf(false) }
        Image(
            modifier = Modifier
                .fillMaxHeight()
                .clickable { isExpanded = true },
            painter = painterResource(id = R.drawable.ic_open),
            contentDescription = ""
        )

        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            uiState.value.ruleCategory.forEach {
                DropdownMenuItem(
                    onClick = {
                        uiState.value = uiState.value.copy(categoryId = it._id)
                        uiState.value = uiState.value.copy(categoryName = it.categoryName)
                        isExpanded = false
                    }
                ) {
                    Text(it.categoryName)
                }
            }
        }
    }
}

@Composable
private fun ManagerBox(
    radius: Dp,
    test: Pair<MutableState<String>, List<Pair<String, MutableState<State>>>>,
    uiState: MutableState<NewRulesUiState>,
    checkBoxState: MutableState<State> = mutableStateOf(State.BLOCK),
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(36.dp)
            .clip(shape = RoundedCornerShape(radius))
            .background(colorResource(id = R.color.white))
            .padding(horizontal = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterStart),
        ) {
            Text(
                text = test.first.value,
                fontStyle = FontStyle(R.style.B2),
                color = colorResource(id = R.color.g_6)
            )
        }
        Box(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterEnd),
        ) {
            ManagerDropDownMenu(test, uiState, checkBoxState)
        }
    }
}

@Composable
private fun ManagerDropDownMenu(
    test: Pair<MutableState<String>, List<Pair<String, MutableState<State>>>>,
    uiState: MutableState<NewRulesUiState>,
    checkBoxState: MutableState<State>,
) {
    if (checkBoxState.value != State.SELECT) {
        var isExpanded by remember { mutableStateOf(false) }
        Image(
            modifier = Modifier
                .fillMaxHeight()
                .clickable { isExpanded = true },
            painter = painterResource(id = R.drawable.ic_open),
            contentDescription = ""
        )

        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            uiState.value.homies.forEach {
                DropdownMenuItem(
                    onClick = {
                        test.first.value = it.name
                        isExpanded = false
                    }
                ) {
                    Text(it.name)
                }
            }
        }
    }
}

@Composable
fun NewRulesCheckBox(
    checkBoxState: MutableState<State>,
    dayList: List<Pair<String, MutableState<State>>>,
) {
    when (checkBoxState.value) {
        State.UNSELECT -> NewRulesBoxRow(
            checkBoxState,
            boxColor = colorResource(id = R.color.hous_blue_bg_2),
            textColor = colorResource(id = R.color.g_4),
            dayList = dayList
        )
        State.SELECT -> NewRulesBoxRow(
            checkBoxState,
            boxColor = colorResource(id = R.color.hous_blue),
            textColor = colorResource(id = R.color.hous_blue),
            dayList = dayList
        )
        State.BLOCK -> NewRulesBoxRow(
            checkBoxState,
            boxColor = colorResource(id = R.color.g_4),
            textColor = colorResource(id = R.color.g_4),
            dayList = dayList
        )
    }
}

@Composable
fun NewRulesBoxRow(
    checkBoxState: MutableState<State>,
    boxColor: Color,
    textColor: Color,
    dayList: List<Pair<String, MutableState<State>>>,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(5.dp))
                .background(color = boxColor)
                .clickable {
                    if (checkBoxState.value != State.BLOCK) {
                        if (checkBoxState.value == State.SELECT) {
                            checkBoxState.value = State.UNSELECT
                            dayList.forEach { it.second.value = State.UNSELECT }
                        } else {
                            checkBoxState.value = State.SELECT
                            dayList.forEach { it.second.value = State.BLOCK }
                        }
                    }
                }
                .size(24.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = ""
            )
        }
        Spacer(modifier = Modifier.size(6.dp))
        Text(
            text = stringResource(id = R.string.new_rules_key_rules),
            color = textColor,
            fontStyle = FontStyle(R.style.B1)
        )
    }
}

@Composable
fun NewRulesDay(
    day: String,
    selected: MutableState<State>,
    checkBoxState: MutableState<State> = mutableStateOf(State.BLOCK),
    dayList: List<Pair<String, MutableState<State>>>,
) {
    val color = when (selected.value) {
        State.UNSELECT -> colorResource(id = R.color.white)
        State.SELECT -> colorResource(id = R.color.hous_blue_bg_2)
        State.BLOCK -> colorResource(id = R.color.g_2)
    }
    val textColor = when (selected.value) {
        State.BLOCK -> colorResource(id = R.color.g_4)
        else -> colorResource(id = R.color.hous_blue)
    }
    if (checkBoxState.value == State.SELECT) selected.value = State.BLOCK

    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(shape = CircleShape)
            .background(color)
            .clickable {
                if (selected.value != State.BLOCK) {
                    if (selected.value == State.SELECT) {
                        selected.value = State.UNSELECT
                        var isCheck = true
                        dayList.forEach { if (it.second.value == State.SELECT) isCheck = false }
                        if (isCheck) checkBoxState.value = State.UNSELECT
                    } else {
                        selected.value = State.SELECT
                        checkBoxState.value = State.BLOCK
                    }
                }
            }
    ) {
        Text(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center),
            text = day,
            color = textColor,
            fontStyle = FontStyle(R.style.B3)
        )
    }
}

private fun addDay(): Pair<MutableState<String>, List<Pair<String, MutableState<State>>>> =
    Pair(
        mutableStateOf("담당자 없음"),
        listOf("월", "화", "수", "목", "금", "토", "일").map {
            Pair(
                it,
                mutableStateOf(State.UNSELECT)
            )
        }
    )

@Composable
fun NewRulesManagerItem(
    test: MutableState<List<Pair<MutableState<String>, List<Pair<String, MutableState<State>>>>>>,
    dayList: List<Pair<String, MutableState<State>>>,
    index: Int,
    checkBoxState: MutableState<State>,
    listSize: Int,
    uiState: MutableState<NewRulesUiState>
) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (test.value[index].first.value != "담당자 없음") {
                checkBoxState.value = State.BLOCK
                Image(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        if (listSize > 1) {
                            val ttt =
                                mutableListOf<Pair<MutableState<String>, List<Pair<String, MutableState<State>>>>>()
                            test.value.forEach { ttt.add(it) }
                            ttt.removeAt(index)
                            test.value = ttt
                        } else {
                            test.value[index].first.value = "담당자 없음"
                            checkBoxState.value = State.UNSELECT
                            dayList.forEach { it.second.value = State.UNSELECT }
                        }
                    }
                )
                Spacer(modifier = Modifier.size(12.dp))
            } else if (test.value[0].first.value == "담당자 없음") checkBoxState.value = State.UNSELECT

            ManagerBox(
                radius = 10.dp,
                test = test.value[index],
                uiState = uiState,
                checkBoxState = checkBoxState,
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        NewRulesDayList(dayList, checkBoxState)
    }
}

@Composable
fun NewRulesDayList(
    dayList: List<Pair<String, MutableState<State>>>,
    checkBoxState: MutableState<State> = mutableStateOf(State.BLOCK)
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        items(dayList) { NewRulesDay(it.first, it.second, checkBoxState, dayList) }
    }
}

@Composable
private fun NewRulesAddMangerButton(
    test: MutableState<List<Pair<MutableState<String>, List<Pair<String, MutableState<State>>>>>>
) {
    if (test.value[test.value.size - 1].first.value != "담당자 없음") {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(32.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(colorResource(id = R.color.white))
                .padding(vertical = 4.dp)
                .clickable {
                    val ttt =
                        mutableListOf<Pair<MutableState<String>, List<Pair<String, MutableState<State>>>>>()
                    test.value.forEach { ttt.add(it) }
                    ttt.add(addDay())
                    test.value = ttt
                }
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_plus_blue),
                    contentDescription = ""
                )
            }
        }
    }
}

@Composable
private fun NewRulesAddRuleButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(48.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .background(colorResource(id = R.color.hous_blue))
            .padding(vertical = 12.dp)
            .clickable { /* 서버 통신 */ },
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center),
        ) {
            Text(
                text = "추가하기",
                fontStyle = FontStyle(R.style.B1),
                color = colorResource(id = R.color.white)
            )
        }
    }
}

enum class State {
    UNSELECT, SELECT, BLOCK
}

@Preview
@Composable
fun NewRulesPreview() {
    NewRulesScreen()
}
