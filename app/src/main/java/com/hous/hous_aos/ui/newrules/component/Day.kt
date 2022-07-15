package com.hous.hous_aos.ui.newrules.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.hous.hous_aos.R
import com.hous.hous_aos.data.model.response.NewRulesResponse

@Composable
fun NewRulesDay(
    day: String,
    selected: MutableState<State>,
    dayList: List<Pair<String, MutableState<State>>>,
    totalSize: Int,
    test: Pair<MutableState<NewRulesResponse.Homie>, List<Pair<String, MutableState<State>>>>,
    setCheckBoxState: (State) -> Unit
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

    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(shape = CircleShape)
            .background(color)
            .clickable {
                if (selected.value != State.BLOCK) {
                    if (selected.value == State.SELECT) {
                        selected.value = State.UNSELECT
                        if (totalSize == 1) {
                            var isCheck = true
                            dayList.forEach { if (it.second.value == State.SELECT) isCheck = false }
                            if (isCheck && test.first.value.name == "담당자 없음")
                                setCheckBoxState(State.UNSELECT)
                        }
                    } else {
                        selected.value = State.SELECT
                        setCheckBoxState(State.BLOCK)
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
