package com.hous.hous_aos.ui.newrules

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hous.data.entity.Category
import com.hous.data.entity.Homie
import com.hous.hous_aos.data.repository.NewRulesRepository
import com.hous.hous_aos.ui.newrules.component.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class NewRulesViewModel @Inject constructor(
    private val newRulesRepository: NewRulesRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewRulesUiState())
    val uiState = _uiState.asStateFlow()
    val buttonState: StateFlow<Boolean> = uiState.map {
        it.ruleName.isNotEmpty() &&
            it.categoryName.isNotEmpty() &&
            (uiState.value.checkBoxState == State.SELECT || isDayCheck())
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(3000L), false)

    init {
        viewModelScope.launch {
            newRulesRepository.getNewRuleList("")
                .onSuccess {
                    Log.d("NewRulesViewModel", "getNewRuleList success : ${it.message}")
                    _uiState.value = _uiState.value.copy(
                        ruleCategory = it.data!!.ruleCategories,
                        homies = it.data.homies
                    )
                    setUserMapping()
                }
                .onFailure {
                    Log.d("NewRulesViewModel", "getNewRuleList fail : ${it.message}")
                }
        }
    }

    private fun isDayCheck(): Boolean {
        var isDay = true
        for (manager in uiState.value.ManagerList) {
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

    private fun setUserMapping() {
        val tempHomieState = hashMapOf<String, Boolean>()
        uiState.value.homies.forEach {
            tempHomieState[it.userName] = true
        }
        _uiState.value = _uiState.value.copy(homieState = tempHomieState)
    }

    fun setRuleName(rule: String) {
        _uiState.value = _uiState.value.copy(ruleName = rule)
    }

    fun setCategoryName(categoryId: String, category: String) {
        _uiState.value = _uiState.value.copy(
            categoryId = categoryId,
            categoryName = category
        )
        Log.d("viewModel", "$categoryId $category")
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
                    uiState.value.ManagerList[0].managerHomie,
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
            _uiState.value = _uiState.value.copy(ManagerList = tempManager)
        } else {
            val tempManager = listOf(
                Manager(
                    uiState.value.ManagerList[0].managerHomie,
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
            _uiState.value = _uiState.value.copy(ManagerList = tempManager)
        }
    }

    fun deleteManager(index: Int) {
        uiState.value.homieState[uiState.value.ManagerList[index].managerHomie.userName] = true
        if (uiState.value.ManagerList.size > 1) {
            val tempManager = mutableListOf<Manager>()
            _uiState.value.ManagerList.forEach { manager -> tempManager.add(manager) }
            tempManager.removeAt(index)
            _uiState.value = _uiState.value.copy(ManagerList = tempManager)
        } else {
            _uiState.value = _uiState.value.copy(ManagerList = listOf(Manager()))
            setCheckBoxState("deleteManager", State.UNSELECT)
        }
    }

    fun choiceManager(managerIndex: Int, homie: com.hous.data.entity.Homie) {
        if (uiState.value.ManagerList[managerIndex].managerHomie.userName != "담당자 없음")
            _uiState.value.homieState[uiState.value.ManagerList[managerIndex].managerHomie.userName] =
                true
        val tempManager = Manager(
            managerHomie = homie,
            dayDataList = uiState.value.ManagerList[managerIndex].dayDataList
        )
        val tempManagerList = mutableListOf<Manager>()
        _uiState.value.ManagerList.forEach { manager -> tempManagerList.add(manager) }
        tempManagerList[managerIndex] = tempManager
        _uiState.value.homieState[homie.userName] = false
        _uiState.value = _uiState.value.copy(ManagerList = tempManagerList)
    }

    fun selectDay(managerIndex: Int, dayData: DayData) {
        if (dayData.dayState != State.BLOCK) {
            val tempManager = Manager(
                managerHomie = _uiState.value.ManagerList[managerIndex].managerHomie,
                dayDataList = changeDayState(dayData, managerIndex)
            )
            val tempManagerList = mutableListOf<Manager>()
            uiState.value.ManagerList.forEach { manager -> tempManagerList.add(manager) }
            tempManagerList[managerIndex] = tempManager
            _uiState.value = _uiState.value.copy(ManagerList = tempManagerList)
        }
    }

    fun isShowAddButton(): Boolean =
        uiState.value.ManagerList[uiState.value.ManagerList.size - 1].managerHomie.userName != "담당자 없음"

    fun addManager() {
        val tempManagerList = mutableListOf<Manager>()
        val nextManager = Manager(managerHomie = nextManager())
        uiState.value.ManagerList.forEach { manager -> tempManagerList.add(manager) }
        tempManagerList.add(nextManager)
        _uiState.value = _uiState.value.copy(ManagerList = tempManagerList)
    }

    fun addNewRule() {
        Log.d("NewRulesViewModel", "data : ${uiState.value}")
        viewModelScope.launch {
            newRulesRepository.addNewRule(uiState.value)
                .onSuccess { Log.d("NewRulesViewModel", "addNewRule success : ${it.message}") }
                .onFailure { Log.d("NewRulesViewModel", "addNewRule fail : ${it.message}") }
        }
    }

    private fun nextManager(): com.hous.data.entity.Homie {
        var tempHomie = com.hous.data.entity.Homie("", "담당자 없음", typeColor = "NULL")
        for (i in uiState.value.homies) {
            if (uiState.value.homieState[i.userName]!!) {
                tempHomie = com.hous.data.entity.Homie(
                    id = i.id,
                    userName = i.userName,
                    typeColor = i.typeColor
                )
                uiState.value.homieState[tempHomie.userName] = false
                break
            }
        }
        return tempHomie
    }

    private fun changeDayState(dayData: DayData, managerIndex: Int): List<DayData> {
        val tempDay = mutableListOf<DayData>()
        uiState.value.ManagerList[managerIndex].dayDataList.forEach { d ->
            if (d.day == dayData.day) {
                when (dayData.dayState) {
                    State.UNSELECT -> {
                        tempDay.add(DayData(d.day, State.SELECT))
                        setCheckBoxState("changeDayState Unselect", State.BLOCK)
                    }
                    State.SELECT -> {
                        tempDay.add(DayData(d.day, State.UNSELECT))
                        if (uiState.value.ManagerList.size == 1) {
                            var isCheck = true
                            uiState.value.ManagerList[0].dayDataList.forEach { dayData ->
                                if (dayData.dayState == State.SELECT) isCheck = false
                            }
                            if (isCheck && uiState.value.ManagerList[0].managerHomie.userName == "담당자 없음")
                                setCheckBoxState("changeDayState select", State.UNSELECT)
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
    val ruleCategory: List<com.hous.data.entity.Category> =
        listOf(
            com.hous.data.entity.Category("1", "청소기"),
            com.hous.data.entity.Category("2", "분리수거"),
            com.hous.data.entity.Category("3", "세탁기"),
            com.hous.data.entity.Category("4", "물 주기"),
        ),
    val homies: List<com.hous.data.entity.Homie> =
        listOf(
            com.hous.data.entity.Homie("1", "강원용", typeColor = "RED"),
            com.hous.data.entity.Homie("2", "이영주", typeColor = "BLUE"),
            com.hous.data.entity.Homie("3", "이준원", typeColor = "YELLOW"),
            com.hous.data.entity.Homie("4", "최인영", typeColor = "GREEN"),
            com.hous.data.entity.Homie("5", "최소현", typeColor = "PURPLE"),
        ),
    val homieState: HashMap<String, Boolean> = hashMapOf(
        "강원용" to true,
        "이영주" to true,
        "이준원" to true,
        "최인영" to true,
        "최소현" to true,
    ),
    val ManagerList: List<Manager> = listOf(Manager())
)

data class Manager(
    val managerHomie: com.hous.data.entity.Homie = com.hous.data.entity.Homie(
        userName = "담당자 없음",
        typeColor = "NULL"
    ),
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
