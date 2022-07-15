package com.hous.hous_aos.ui.newrules.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.dp
import com.hous.hous_aos.data.model.response.NewRulesResponse

@Composable
fun NewRulesDayList(
    dayList: List<Pair<String, MutableState<State>>>,
    totalSize: Int,
    test: Pair<MutableState<NewRulesResponse.Homie>, List<Pair<String, MutableState<State>>>>,
    setCheckBoxState: (String, State) -> Unit
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        items(dayList) {
            NewRulesDay(
                day = it.first,
                selected = it.second,
                dayList = dayList,
                totalSize = totalSize,
                test = test,
                setCheckBoxState = setCheckBoxState
            )
        }
    }
}
