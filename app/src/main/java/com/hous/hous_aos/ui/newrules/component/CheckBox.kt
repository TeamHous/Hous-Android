package com.hous.hous_aos.ui.newrules.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.hous.hous_aos.R

@Composable
fun NewRulesCheckBox(
    checkBoxState: State,
    setCheckBoxState: (String, State) -> Unit,
    setAllDayData: (Boolean) -> Unit
) {
    when (checkBoxState) {
        State.UNSELECT -> NewRulesBoxRow(
            checkBoxState,
            boxColor = colorResource(id = R.color.hous_blue_bg_2),
            textColor = colorResource(id = R.color.g_4),
            setCheckBoxState = setCheckBoxState,
            setAllDayData = setAllDayData
        )
        State.SELECT -> NewRulesBoxRow(
            checkBoxState,
            boxColor = colorResource(id = R.color.hous_blue),
            textColor = colorResource(id = R.color.hous_blue),
            setCheckBoxState = setCheckBoxState,
            setAllDayData = setAllDayData
        )
        State.BLOCK -> NewRulesBoxRow(
            checkBoxState,
            boxColor = colorResource(id = R.color.g_4),
            textColor = colorResource(id = R.color.g_4),
            setCheckBoxState = setCheckBoxState,
            setAllDayData = setAllDayData
        )
    }
}

@Composable
fun NewRulesBoxRow(
    checkBoxState: State,
    boxColor: Color,
    textColor: Color,
    setCheckBoxState: (String, State) -> Unit,
    setAllDayData: (Boolean) -> Unit
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
                    if (checkBoxState != State.BLOCK) {
                        if (checkBoxState == State.SELECT) {
                            setCheckBoxState("newRulesBoxRow", State.UNSELECT)
                            setAllDayData(true)
                        } else {
                            setCheckBoxState("newRulesBoxRow", State.SELECT)
                            setAllDayData(false)
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
