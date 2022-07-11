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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hous.hous_aos.R

@Composable
fun NewRulesScreen() {
    val isAlarm = remember { mutableStateOf(false) }
    val value: MutableState<String> = remember { mutableStateOf("") }
    val categoryText = remember { mutableStateOf("") }
    val checkBoxState: MutableState<State> = remember { mutableStateOf(State.UNSELECT) }
    val isMenu = remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.hous_blue_bg))
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.size(50.dp))

        NewRulesToolbar(isAlarm)
        Spacer(modifier = Modifier.size(27.dp))

        Text(
            text = stringResource(id = R.string.new_rules_rule_name),
            fontStyle = FontStyle(R.style.B2),
            color = colorResource(id = R.color.hous_blue)
        )
        Spacer(modifier = Modifier.size(8.dp))

        NewRulesTextField(value)
        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = stringResource(id = R.string.new_rules_category),
            fontStyle = FontStyle(R.style.B2),
            color = colorResource(id = R.color.hous_blue)
        )
        Spacer(modifier = Modifier.size(8.dp))

        NewRulesBox(10.dp, categoryText, isMenu)
        Spacer(modifier = Modifier.size(16.dp))

        NewRulesCheckBox(checkBoxState)
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

        NewRulesManagerItem()
        Spacer(modifier = Modifier.size(180.dp))

        NewRulesButton()
        Spacer(modifier = Modifier.size(24.dp))
    }
}

@Composable
fun NewRulesToolbar(
    isAlarm: MutableState<Boolean>
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
        if (isAlarm.value) {
            Image(
                painter = painterResource(id = R.drawable.ic_alarmon),
                contentDescription = "",
                modifier = Modifier.clickable { isAlarm.value = !isAlarm.value }
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_alarmoff),
                contentDescription = "",
                modifier = Modifier.clickable { isAlarm.value = !isAlarm.value }
            )
        }
    }
}

@Composable
fun NewRulesTextField(
    text: MutableState<String>
) {
    val maxChar = 16
    BasicTextField(
        value = text.value,
        onValueChange = { if (it.length < maxChar) text.value = it },
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
private fun NewRulesBox(
    radius: Dp,
    prefixText: MutableState<String>,
    isMenu: MutableState<Boolean>
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
                text = prefixText.value,
                fontStyle = FontStyle(R.style.B2),
                color = colorResource(id = R.color.g_6)
            )
        }
        Box(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterEnd),
        ) {
            NewRulesDropDownMenu(prefixText, isMenu)
        }
    }
}

@Composable
private fun NewRulesDropDownMenu(
    categoryText: MutableState<String>,
    isMenu: MutableState<Boolean>
) {
    if (isMenu.value) {
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
            DropdownMenuItem(
                onClick = { categoryText.value = "청소" }
            ) {
                Text("청소")
            }

            DropdownMenuItem(
                onClick = { categoryText.value = "카테고리" }
            ) {
                Text("카테고리")
            }
        }
    }
}

@Composable
fun NewRulesCheckBox(
    checkBoxState: MutableState<State>
) {
    when (checkBoxState.value) {
        State.UNSELECT -> NewRulesBoxRow(
            boxColor = colorResource(id = R.color.hous_blue_bg_2),
            textColor = colorResource(id = R.color.g_4)
        )
        State.SELECT -> NewRulesBoxRow(
            boxColor = colorResource(id = R.color.hous_blue),
            textColor = colorResource(id = R.color.hous_blue)
        )
        State.BLOCK -> NewRulesBoxRow(
            boxColor = colorResource(id = R.color.g_4),
            textColor = colorResource(id = R.color.g_4)
        )
    }
}

@Composable
fun NewRulesBoxRow(boxColor: Color, textColor: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(5.dp))
                .background(color = boxColor)
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
    day: String
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(shape = CircleShape)
            .background(colorResource(id = R.color.g_2))
    ) {
        Text(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center),
            text = day,
            color = colorResource(id = R.color.g_4),
            fontStyle = FontStyle(R.style.B3)
        )
    }
}

@Composable
fun NewRulesManagerItem() {
    val managerText = remember { mutableStateOf("담당자 없음") }
    val isMenu = remember { mutableStateOf(true) }
    Column {
        NewRulesBox(
            radius = 10.dp,
            prefixText = managerText,
            isMenu = isMenu
        )
        Spacer(modifier = Modifier.size(10.dp))
        NewRulesDayList()
    }
}

@Composable
fun NewRulesDayList() {
    val dayList = stringArrayResource(id = R.array.new_rules_day_list)
    LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        items(dayList) { NewRulesDay(it) }
    }
}

@Composable
private fun NewRulesButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(48.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .background(colorResource(id = R.color.hous_blue))
            .padding(horizontal = 12.dp)
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
