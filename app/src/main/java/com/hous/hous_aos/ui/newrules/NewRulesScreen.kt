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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hous.hous_aos.R
import com.hous.data.entity.Homie
import com.hous.hous_aos.ui.newrules.component.CategoryItem
import com.hous.hous_aos.ui.newrules.component.ManagerItem
import com.hous.hous_aos.ui.newrules.component.NewRulesAddMangerButton
import com.hous.hous_aos.ui.newrules.component.NewRulesAddRuleButton
import com.hous.hous_aos.ui.newrules.component.NewRulesCheckBox
import com.hous.hous_aos.ui.newrules.component.NewRulesTextField
import com.hous.hous_aos.ui.newrules.component.NewRulesToolbar

@Composable
fun NewRulesScreen(
    viewModel: NewRulesViewModel,
    finish: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val buttonState by viewModel.buttonState.collectAsState()
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
        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            item {
                Spacer(modifier = Modifier.size(50.dp))

                NewRulesToolbar(
                    notificationState = uiState.notificationState,
                    checkBoxState = uiState.checkBoxState,
                    toggleState = viewModel::toggleNotificationState,
                    finish = finish
                )
                Spacer(modifier = Modifier.size(27.dp))

                Text(
                    text = stringResource(id = R.string.new_rules_rule_name),
                    fontFamily = FontFamily(
                        Font(
                            resId = R.font.spoqa_han_sans_neo_medium,
                            style = FontStyle(R.style.B2)
                        )
                    ),
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
                    fontFamily = FontFamily(
                        Font(
                            resId = R.font.spoqa_han_sans_neo_medium,
                            style = FontStyle(R.style.B2)
                        )
                    ),
                    color = colorResource(id = R.color.hous_blue)
                )
                Spacer(modifier = Modifier.size(8.dp))

                CategoryItem(
                    radius = 10.dp,
                    categoryName = uiState.categoryName,
                    ruleCategoryList = uiState.ruleCategory,
                    setCategory = viewModel::setCategoryName
                )
                Spacer(modifier = Modifier.size(16.dp))

                NewRulesCheckBox(
                    checkBoxState = uiState.checkBoxState,
                    setCheckBoxState = viewModel::setCheckBoxState,
                    setAllDayData = viewModel::setAllDayData
                )
                Spacer(modifier = Modifier.size(4.dp))

                Row {
                    Spacer(Modifier.size(30.dp))
                    Text(
                        text = stringResource(id = R.string.new_rules_description),
                        fontFamily = FontFamily(
                            Font(
                                resId = R.font.spoqa_han_sans_neo_medium,
                                style = FontStyle(R.style.Description)
                            )
                        ),
                        color = colorResource(id = R.color.g_4)
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))

                Text(
                    text = stringResource(id = R.string.new_rules_manager_setting),
                    fontFamily = FontFamily(
                        Font(
                            resId = R.font.spoqa_han_sans_neo,
                            style = FontStyle(R.style.B2)
                        )
                    ),
                    color = colorResource(id = R.color.hous_blue)
                )
                Spacer(modifier = Modifier.size(12.dp))
            }

            itemsIndexed(uiState.ManagerList) { index, value ->
                ManagerItem(
                    manager = value,
                    currentIndex = index,
                    checkBoxState = uiState.checkBoxState,
                    homies = uiState.homies,
                    homieState = uiState.homieState,
                    deleteManager = viewModel::deleteManager,
                    setCheckBoxState = viewModel::setCheckBoxState,
                    choiceManager = viewModel::choiceManager,
                    selectDay = viewModel::selectDay
                )
                Spacer(modifier = Modifier.size(16.dp))
            }

            item {
                NewRulesAddMangerButton(
                    homies = uiState.homies,
                    homieState = uiState.homieState,
                    isShowAddButton = viewModel::isShowAddButton,
                    addManager = viewModel::addManager
                )
            }
        }
        NewRulesAddRuleButton(
            isAddButton = buttonState,
            addNewRule = viewModel::addNewRule,
            finish = finish
        )
        Spacer(modifier = Modifier.size(24.dp))
    }
}

fun isAddDay(
    homies: List<com.hous.data.entity.Homie>,
    homieState: HashMap<String, Boolean>
): Boolean {
    var temp = false
    homies.forEach { h ->
        if (homieState[h.userName]!!) temp = true
    }
    return temp
}

@Preview
@Composable
fun NewRulesPreview() {
//    NewRulesScreen()
}
