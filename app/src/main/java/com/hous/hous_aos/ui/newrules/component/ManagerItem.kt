package com.hous.hous_aos.ui.newrules.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hous.domain.model.DayDataInfo
import com.hous.domain.model.HomieInfo
import com.hous.domain.model.Manager
import com.hous.domain.model.State

@Composable
fun ManagerItem(
    manager: Manager,
    currentIndex: Int,
    checkBoxState: State,
    homies: List<HomieInfo>,
    homieState: HashMap<String, Boolean>,
    setCheckBoxState: (String, State) -> Unit,
    deleteManager: (Int) -> Unit,
    choiceManager: (Int, HomieInfo) -> Unit,
    selectDay: (Int, DayDataInfo) -> Unit
) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (manager.managerHomie.userName != "담당자 없음") {
                DeleteButton(
                    index = currentIndex,
                    setCheckBoxState = setCheckBoxState,
                    deleteManager = deleteManager
                )
                Spacer(modifier = Modifier.size(12.dp))
            }

            ManagerBox(
                radius = 10.dp,
                managerIndex = currentIndex,
                homies = homies,
                manager = manager,
                homieState = homieState,
                checkBoxState = checkBoxState,
                choiceManager = choiceManager
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        NewRulesDayList(
            manager = manager,
            currentIndex = currentIndex,
            selectDay = selectDay
        )
    }
}
