package com.hous.hous_aos.ui.newrules.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.hous.domain.model.DayData
import com.hous.domain.model.Manager

@Composable
fun NewRulesDayList(
    manager: Manager,
    currentIndex: Int,
    selectDay: (Int, DayData) -> Unit
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        itemsIndexed(manager.dayDataList) { _, value ->
            NewRulesDay(
                dayData = value,
                currentIndex = currentIndex,
                selectDay = selectDay
            )
        }
    }
}
