package com.hous.hous_aos.ui.newrules

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hous.hous_aos.R
import com.hous.hous_aos.data.model.response.NewRulesResponse
import com.hous.hous_aos.ui.newrules.component.CategoryItem
import com.hous.hous_aos.ui.newrules.component.ManagerItem
import com.hous.hous_aos.ui.newrules.component.NewRulesAddMangerButton
import com.hous.hous_aos.ui.newrules.component.NewRulesAddRuleButton
import com.hous.hous_aos.ui.newrules.component.NewRulesCheckBox
import com.hous.hous_aos.ui.newrules.component.NewRulesTextField
import com.hous.hous_aos.ui.newrules.component.NewRulesToolbar
import com.hous.hous_aos.ui.newrules.component.State

@Composable
fun NewRulesScreen(
    viewModel: NewRulesViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val isRuleAddButton = remember { mutableStateOf(false) }
    val test = remember {
        mutableStateOf(
            listOf(
                Pair(
                    mutableStateOf(NewRulesResponse.Homie("", "담당자 없음", "NULL")),
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
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.hous_blue_bg))
            .padding(horizontal = 20.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
    ) {
        /* 버튼 on / off */
//        isRuleAddButton.value = uiState.value.ruleName.isNotEmpty() &&
//            uiState.value.categoryName.isNotEmpty() &&
//            (checkBoxState.value == State.SELECT || IsAdd(test))
        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            item {
                Spacer(modifier = Modifier.size(50.dp))

                NewRulesToolbar(
                    notificationState = uiState.notificationState,
                    checkBoxState = uiState.checkBoxState,
                    toggleState = viewModel::toggleNotificationState
                )
                Spacer(modifier = Modifier.size(27.dp))

                Text(
                    text = stringResource(id = R.string.new_rules_rule_name),
                    fontStyle = FontStyle(R.style.B2),
                    color = colorResource(id = R.color.hous_blue)
                )
                Spacer(modifier = Modifier.size(8.dp))

                NewRulesTextField(
                    ruleName = uiState.ruleName,
                    changeRuleName = viewModel::setRuleName,
                    focusManager = focusManager
                )
                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    text = stringResource(id = R.string.new_rules_category),
                    fontStyle = FontStyle(R.style.B2),
                    color = colorResource(id = R.color.hous_blue)
                )
                Spacer(modifier = Modifier.size(8.dp))

                CategoryItem(
                    radius = 10.dp,
                    categoryName = uiState.categoryName,
                    ruleCategoryList = uiState.ruleCategory,
                    checkBoxState = uiState.checkBoxState,
                    setCategory = viewModel::setCategoryName
                )
                Spacer(modifier = Modifier.size(16.dp))

                NewRulesCheckBox(
                    checkBoxState = uiState.checkBoxState,
                    dayList = test.value[0].second,
                    setCheckBoxState = viewModel::setCheckBoxState
                )
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
                ManagerItem(
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
                NewRulesAddMangerButton(test, uiState)
            }
        }
        NewRulesAddRuleButton(isRuleAddButton)
    }
}

fun IsAdd(
    test: MutableState<List<Pair<MutableState<NewRulesResponse.Homie>, List<Pair<String, MutableState<State>>>>>>
): Boolean {
    var isAdd = true
    for (dayList in test.value) {
        var temp = false
        for (dayState in dayList.second) {
            if (dayState.second.value == State.SELECT) {
                temp = true
                break
            }
        }
        if (!temp) {
            isAdd = false
            break
        }
    }
    return isAdd
}

fun isAddDay(
    uiState: MutableState<NewRulesUiState>
): Boolean {
    var temp = false
    uiState.value.homies.forEach { homie ->
        if (uiState.value.homieState[homie.name]!!) temp = true
    }
    return temp
}

fun addDay(
    uiState: MutableState<NewRulesUiState>
): Pair<MutableState<NewRulesResponse.Homie>, List<Pair<String, MutableState<State>>>> {
    var temp: NewRulesResponse.Homie = NewRulesResponse.Homie("", "", "")
    for (i in uiState.value.homies) {
        if (uiState.value.homieState[i.name]!!) {
            temp = NewRulesResponse.Homie(i._id, i.name, i.typeColor)
            uiState.value.homieState[temp.name] = false
            break
        }
    }
    return Pair(
        mutableStateOf(NewRulesResponse.Homie(temp._id, temp.name, temp.typeColor)),
        listOf("월", "화", "수", "목", "금", "토", "일").map {
            Pair(
                it,
                mutableStateOf(State.UNSELECT)
            )
        }
    )
}

@Preview
@Composable
fun NewRulesPreview() {
    NewRulesScreen()
}
