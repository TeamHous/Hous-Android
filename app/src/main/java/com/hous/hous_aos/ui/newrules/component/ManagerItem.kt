package com.hous.hous_aos.ui.newrules.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hous.hous_aos.data.model.response.NewRulesResponse

@Composable
fun ManagerItem(
    test: MutableState<List<Pair<MutableState<NewRulesResponse.Homie>, List<Pair<String, MutableState<State>>>>>>,
    dayList: List<Pair<String, MutableState<State>>>,
    index: Int,
    checkBoxState: State,
    listSize: Int,
    homies: List<NewRulesResponse.Homie>,
    homieState: HashMap<String, Boolean>,
    setCheckBoxState: (State) -> Unit
) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (test.value[index].first.value.name != "담당자 없음") {
                DeleteButton(
                    test = test,
                    dayList = dayList,
                    index = index,
                    listSize = listSize,
                    homieState = homieState,
                    setCheckBoxState = setCheckBoxState
                )
                Spacer(modifier = Modifier.size(12.dp))
            } else if (test.value[0].first.value.name == "담당자 없음")
                setCheckBoxState(State.UNSELECT)

            ManagerBox(
                radius = 10.dp,
                test = test.value[index],
                homies = homies,
                homieState = homieState,
                checkBoxState = checkBoxState,
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        NewRulesDayList(
            dayList = dayList,
            totalSize = listSize,
            test = test.value[0],
            setCheckBoxState = setCheckBoxState
        )
    }
}
