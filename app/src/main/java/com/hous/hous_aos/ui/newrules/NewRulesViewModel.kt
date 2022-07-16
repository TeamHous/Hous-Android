package com.hous.hous_aos.ui.newrules

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hous.hous_aos.data.model.response.NewRulesResponse
import com.hous.hous_aos.ui.newrules.component.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class NewRulesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NewRulesUiState())
    val uiState = _uiState.asStateFlow()
    val buttonState: StateFlow<Boolean> = uiState.map {
        it.ruleCategory.isNotEmpty() &&
            it.categoryName.isNotEmpty() &&
            (uiState.value.checkBoxState == State.SELECT || isDayCheck())
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(3000L), false)

    private fun isDayCheck(): Boolean {
        var isDay = true
        for (manager in uiState.value.managerList) {
            var temp = false
            for (dayList in manager.dayDataList) {
                if (dayList.dayState == State.SELECT) {
                    temp = true
                    break
                }
            }
            if (!temp) {
                isDay = false
                break
            }
        }
        return isDay
    }

    fun setRuleName(rule: String) {
        _uiState.value = _uiState.value.copy(ruleName = rule)
    }

    fun setCategoryName(
        categoryId: String,
        category: String
    ) {
        _uiState.value = _uiState.value.copy(
            categoryId = categoryId,
            categoryName = category
        )
    }

    fun setCheckBoxState(where: String, state: State) {
        Log.d("lsdkjflsadjlfk", "Where: $where value: $state")
        when (state) {
            State.UNSELECT -> _uiState.value = _uiState.value.copy(checkBoxState = State.UNSELECT)
            State.SELECT -> _uiState.value = _uiState.value.copy(checkBoxState = State.SELECT)
            State.BLOCK -> _uiState.value = _uiState.value.copy(checkBoxState = State.BLOCK)
        }
    }

    fun toggleNotificationState(isChange: Boolean) {
        _uiState.value = _uiState.value.copy(notificationState = isChange)
    }

    fun setAllDayData(isChange: Boolean) {
        if (isChange) {
            val tempManager = listOf(
                Manager(
                    uiState.value.managerList[0].managerHomie,
                    dayDataList = listOf(
                        DayData("월", State.UNSELECT),
                        DayData("화", State.UNSELECT),
                        DayData("수", State.UNSELECT),
                        DayData("목", State.UNSELECT),
                        DayData("금", State.UNSELECT),
                        DayData("토", State.UNSELECT),
                        DayData("일", State.UNSELECT),
                    )
                )
            )
            _uiState.value = _uiState.value.copy(managerList = tempManager)
        } else {
            val tempManager = listOf(
                Manager(
                    uiState.value.managerList[0].managerHomie,
                    dayDataList = listOf(
                        DayData("월", State.BLOCK),
                        DayData("화", State.BLOCK),
                        DayData("수", State.BLOCK),
                        DayData("목", State.BLOCK),
                        DayData("금", State.BLOCK),
                        DayData("토", State.BLOCK),
                        DayData("일", State.BLOCK),
                    )
                )
            )
            _uiState.value = _uiState.value.copy(managerList = tempManager)
        }
    }

    fun deleteManager(index: Int) {
        uiState.value.homieState[uiState.value.managerList[index].managerHomie.name] = true
        if (uiState.value.managerList.size > 1) {
            val tempManager = mutableListOf<Manager>()
            _uiState.value.managerList.forEach { manager -> tempManager.add(manager) }
            tempManager.removeAt(index)
            _uiState.value = _uiState.value.copy(managerList = tempManager)
        } else {
            _uiState.value = _uiState.value.copy(managerList = listOf(Manager()))
            _uiState.value = _uiState.value.copy(checkBoxState = State.UNSELECT)
        }
    }

    fun choiceManager(managerIndex: Int, homie: NewRulesResponse.Homie) {
        if (uiState.value.managerList[managerIndex].managerHomie.name != "담당자 없음")
            _uiState.value.homieState[uiState.value.managerList[managerIndex].managerHomie.name] =
                true
        val tempManager = Manager(
            managerHomie = homie,
            dayDataList = uiState.value.managerList[managerIndex].dayDataList
        )
        val tempManagerList = mutableListOf<Manager>()
        _uiState.value.managerList.forEach { manager -> tempManagerList.add(manager) }
        tempManagerList[managerIndex] = tempManager
        _uiState.value.homieState[homie.name] = false
        _uiState.value = _uiState.value.copy(managerList = tempManagerList)
    }

    fun selectDay(managerIndex: Int, dayData: DayData) {
        if (dayData.dayState != State.BLOCK) {
            val tempManager = Manager(
                managerHomie = _uiState.value.managerList[managerIndex].managerHomie,
                dayDataList = changeDayState(dayData, managerIndex)
            )
            val tempManagerList = mutableListOf<Manager>()
            uiState.value.managerList.forEach { manager -> tempManagerList.add(manager) }
            tempManagerList[managerIndex] = tempManager
            _uiState.value = _uiState.value.copy(managerList = tempManagerList)
        }
    }

    fun isShowAddButton(): Boolean =
        uiState.value.managerList[uiState.value.managerList.size - 1].managerHomie.name != "담당자 없음"

    fun addManager() {
        val tempManagerList = mutableListOf<Manager>()
        val nextManager = Manager(managerHomie = nextManager())
        uiState.value.managerList.forEach { manager -> tempManagerList.add(manager) }
        tempManagerList.add(nextManager)
        _uiState.value = _uiState.value.copy(managerList = tempManagerList)
    }

    private fun nextManager(): NewRulesResponse.Homie {
        var tempHomie = NewRulesResponse.Homie("", "담당자 없음", "NULL")
        for (i in uiState.value.homies) {
            if (uiState.value.homieState[i.name]!!) {
                tempHomie = NewRulesResponse.Homie(i._id, i.name, i.typeColor)
                uiState.value.homieState[tempHomie.name] = false
                break
            }
        }
        return tempHomie
    }

    private fun changeDayState(dayData: DayData, managerIndex: Int): List<DayData> {
        val tempDay = mutableListOf<DayData>()
        uiState.value.managerList[managerIndex].dayDataList.forEach { d ->
            if (d.day == dayData.day) {
                when (dayData.dayState) {
                    State.UNSELECT -> {
                        tempDay.add(DayData(d.day, State.SELECT))
                        _uiState.value = _uiState.value.copy(checkBoxState = State.BLOCK)
                    }
                    State.SELECT -> {
                        tempDay.add(DayData(d.day, State.UNSELECT))
                        if (uiState.value.managerList.size == 1) {
                            var isCheck = false
                            uiState.value.managerList[0].dayDataList.forEach { dayData ->
                                if (dayData.dayState == State.SELECT) isCheck = true
                            }
                            if (isCheck && uiState.value.managerList[0].managerHomie.name == "담당자 없음")
                                _uiState.value = _uiState.value.copy(checkBoxState = State.UNSELECT)
                        }
                    }
                }
            } else tempDay.add(d)
        }
        return tempDay
    }
}

data class NewRulesUiState(
    val ruleName: String = "",
    val categoryName: String = "",
    val categoryId: String = "",
    val notificationState: Boolean = false,
    val checkBoxState: State = State.UNSELECT,
    val ruleCategory: List<NewRulesResponse.Category> =
        listOf(
            NewRulesResponse.Category("1", "청소기"),
            NewRulesResponse.Category("2", "분리수거"),
            NewRulesResponse.Category("3", "세탁기"),
            NewRulesResponse.Category("4", "물 주기"),
        ),
    val homies: List<NewRulesResponse.Homie> =
        listOf(
            NewRulesResponse.Homie("1", "강원용", "RED"),
            NewRulesResponse.Homie("2", "이영주", "BLUE"),
            NewRulesResponse.Homie("3", "이준원", "YELLOW"),
            NewRulesResponse.Homie("4", "최인영", "GREEN"),
            NewRulesResponse.Homie("5", "최소현", "PURPLE"),
        ),
    val homieState: HashMap<String, Boolean> = hashMapOf(
        "강원용" to true,
        "이영주" to true,
        "이준원" to true,
        "최인영" to true,
        "최소현" to true,
    ),
    val managerList: List<Manager> = listOf(Manager())
)

data class Manager(
    val managerHomie: NewRulesResponse.Homie = NewRulesResponse.Homie("", "담당자 없음", "NULL"),
    val dayDataList: List<DayData> = listOf(
        DayData("월", State.UNSELECT),
        DayData("화", State.UNSELECT),
        DayData("수", State.UNSELECT),
        DayData("목", State.UNSELECT),
        DayData("금", State.UNSELECT),
        DayData("토", State.UNSELECT),
        DayData("일", State.UNSELECT),
    )
)

data class DayData(
    val day: String,
    val dayState: State
)
