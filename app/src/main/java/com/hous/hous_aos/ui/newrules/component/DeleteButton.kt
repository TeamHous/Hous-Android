package com.hous.hous_aos.ui.newrules.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.hous.hous_aos.R
import com.hous.hous_aos.data.model.response.NewRulesResponse
import com.hous.hous_aos.ui.newrules.NewRulesUiState
import com.hous.hous_aos.ui.newrules.State

@Composable
fun DeleteButton(
    test: MutableState<List<Pair<MutableState<NewRulesResponse.Homie>, List<Pair<String, MutableState<State>>>>>>,
    dayList: List<Pair<String, MutableState<State>>>,
    index: Int,
    checkBoxState: MutableState<State>,
    listSize: Int,
    uiState: MutableState<NewRulesUiState>
) {
    checkBoxState.value = State.BLOCK
    Image(
        painter = painterResource(id = R.drawable.ic_delete),
        contentDescription = "",
        modifier = Modifier.clickable {
            if (listSize > 1) {
                uiState.value.homieState[test.value[index].first.value.name] = true
                val ttt =
                    mutableListOf<Pair<MutableState<NewRulesResponse.Homie>, List<Pair<String, MutableState<State>>>>>()
                test.value.forEach { ttt.add(it) }
                ttt.removeAt(index)
                test.value = ttt
            } else {
                uiState.value.homieState[test.value[index].first.value.name] = true
                test.value[index].first.value =
                    test.value[index].first.value.copy(_id = "")
                test.value[index].first.value =
                    test.value[index].first.value.copy(name = "담당자 없음")
                test.value[index].first.value =
                    test.value[index].first.value.copy(typeColor = "NULL")
                checkBoxState.value = State.UNSELECT
                dayList.forEach { it.second.value = State.UNSELECT }
            }
        }
    )
}
