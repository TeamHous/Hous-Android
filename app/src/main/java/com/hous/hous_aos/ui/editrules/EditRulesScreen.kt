package com.hous.hous_aos.ui.editrules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.hous.hous_aos.R
import com.hous.hous_aos.ui.editrules.component.RuleDeleteButton
import com.hous.hous_aos.ui.editrules.component.RuleEditButton
import com.hous.hous_aos.ui.newrules.component.NewRulesTextField
import com.hous.hous_aos.ui.newrules.component.NewRulesToolbar
import com.hous.hous_aos.ui.newrules.component.State

@Composable
fun EditRulesScreen() {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            item {
                Spacer(modifier = Modifier.size(50.dp))
                NewRulesToolbar(
                    title = stringResource(id = R.string.new_rules_title),
                    notificationState = false,
                    checkBoxState = State.SELECT,
                    toggleState = { }
                )
                Spacer(modifier = Modifier.size(27.dp))

                Text(
                    text = stringResource(id = R.string.new_rules_rule_name),
                    fontStyle = FontStyle(R.style.B2),
                    color = colorResource(id = R.color.hous_blue)
                )
                Spacer(modifier = Modifier.size(8.dp))

                NewRulesTextField(
                    ruleName = "uiState.ruleName",
                    changeRuleName = {},
                    focusManager = focusManager
                )
                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    text = stringResource(id = R.string.new_rules_category),
                    fontStyle = FontStyle(R.style.B2),
                    color = colorResource(id = R.color.hous_blue)
                )
                Spacer(modifier = Modifier.size(8.dp))

//                CategoryItem(
//                    radius = 10.dp,
//                    categoryName = "uiState.categoryName",
//                    ruleCategoryList = emptyList(),
//                    setCategory = {  }
//                )
                Spacer(modifier = Modifier.size(16.dp))

//                NewRulesCheckBox(
//                    checkBoxState = State.SELECT,
//                    setCheckBoxState = {  },
//                    setAllDayData = {}
//                )
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
        }

        Row {
            RuleDeleteButton()
            Spacer(modifier = Modifier.size(16.dp))
            RuleEditButton()
        }
    }
}
