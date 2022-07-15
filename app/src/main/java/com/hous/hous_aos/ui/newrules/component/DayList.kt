package com.hous.hous_aos.ui.newrules.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import com.hous.hous_aos.data.model.response.NewRulesResponse
import com.hous.hous_aos.ui.newrules.State

@Composable
fun NewRulesDayList(
    dayList: List<Pair<String, MutableState<State>>>,
    checkBoxState: MutableState<State> = mutableStateOf(State.BLOCK),
    totalSize: Int,
    test: Pair<MutableState<NewRulesResponse.Homie>, List<Pair<String, MutableState<State>>>>
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        items(dayList) { NewRulesDay(it.first, it.second, checkBoxState, dayList, totalSize, test) }
    }
}
