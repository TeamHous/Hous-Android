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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hous.hous_aos.R
import timber.log.Timber

@Composable
fun NewRulesScreen() {
    val isAlarm = remember { mutableStateOf(false) }
    val text = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.hous_blue_bg))
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.size(50.dp))
        NewRulesToolbar(isAlarm)

        Spacer(modifier = Modifier.size(27.dp))
        Text(
            text = "규칙이름",
            fontStyle = FontStyle(R.style.B2),
            color = colorResource(id = R.color.hous_blue)
        )

        Spacer(modifier = Modifier.size(8.dp))
        NewRulesTextField(text, 10.dp)

        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "카테고리",
            fontStyle = FontStyle(R.style.B2),
            color = colorResource(id = R.color.hous_blue)
        )

        Spacer(modifier = Modifier.size(8.dp))
        NewRulesBox(10.dp, R.color.white) {
            Text(
                text = "청소",
                fontStyle = FontStyle(R.style.B2),
                color = colorResource(id = R.color.g_6)
            )
        }
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
    text: MutableState<String>,
    radius: Dp
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .scale(scaleY = 0.5F, scaleX = 1F),
        value = text.value,
        onValueChange = {
            text.value = it
        },
        shape = RoundedCornerShape(radius),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = colorResource(id = R.color.white),
            unfocusedIndicatorColor = colorResource(id = R.color.white),
            focusedIndicatorColor = colorResource(id = R.color.white),
            cursorColor = colorResource(id = R.color.white),
        ),
    )
}

@Composable
private fun NewRulesBox(
    radius: Dp,
    customColor: Int,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(36.dp)
            .clip(shape = RoundedCornerShape(radius))
            .background(colorResource(id = customColor))
            .padding(horizontal = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterStart),
        ) {
            content()
        }
        Box(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterEnd),
        ) {
            HousDropDownMenu()
        }
    }
}

@Composable
private fun HousDropDownMenu() {

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
            onClick = { Timber.d("test1") }
        ) {
            Text("test1")
        }

        DropdownMenuItem(
            onClick = { Timber.d("test2") }
        ) {
            Text("test2")
        }
    }
}

@Preview
@Composable
fun NewRulesPreview() {
    NewRulesScreen()
}
