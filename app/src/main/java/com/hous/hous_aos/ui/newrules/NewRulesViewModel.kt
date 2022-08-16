package com.hous.hous_aos.ui.newrules

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hous.domain.model.CategoryInfo
import com.hous.domain.model.DayDataInfo
import com.hous.domain.model.HomieInfo
import com.hous.domain.model.Manager
import com.hous.domain.model.State
import com.hous.domain.usecase.AddNewRuleUseCase
import com.hous.domain.usecase.GetNewRuleInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewRulesViewModel @Inject constructor(
    private val addNewRuleUseCase: AddNewRuleUseCase,
    private val getNewRuleInfoUseCase: GetNewRuleInfoUseCase
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
            getNewRuleInfoUseCase("")
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        ruleCategory = it.ruleCategories,
                        homies = it.homies
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
                    uiState.value.managerList[0].managerHomie,
                    dayDataList = listOf(
                        DayDataInfo("월", State.UNSELECT),
                        DayDataInfo("화", State.UNSELECT),
                        DayDataInfo("수", State.UNSELECT),
                        DayDataInfo("목", State.UNSELECT),
                        DayDataInfo("금", State.UNSELECT),
                        DayDataInfo("토", State.UNSELECT),
                        DayDataInfo("일", State.UNSELECT)
                    )
                )
            )
            _uiState.value = _uiState.value.copy(managerList = tempManager)
        } else {
            val tempManager = listOf(
                Manager(
                    uiState.value.managerList[0].managerHomie,
                    dayDataList = listOf(
                        DayDataInfo("월", State.BLOCK),
                        DayDataInfo("화", State.BLOCK),
                        DayDataInfo("수", State.BLOCK),
                        DayDataInfo("목", State.BLOCK),
                        DayDataInfo("금", State.BLOCK),
                        DayDataInfo("토", State.BLOCK),
                        DayDataInfo("일", State.BLOCK)
                    )
                )
            )
            _uiState.value = _uiState.value.copy(managerList = tempManager)
        }
    }

    fun deleteManager(index: Int) {
        uiState.value.homieState[uiState.value.managerList[index].managerHomie.userName] = true
        if (uiState.value.managerList.size > 1) {
            val tempManager = mutableListOf<Manager>()
            _uiState.value.managerList.forEach { manager -> tempManager.add(manager) }
            tempManager.removeAt(index)
            _uiState.value = _uiState.value.copy(managerList = tempManager)
        } else {
            _uiState.value = _uiState.value.copy(managerList = listOf(Manager()))
            setCheckBoxState("deleteManager", State.UNSELECT)
        }
    }

    fun choiceManager(managerIndex: Int, homie: HomieInfo) {
        if (uiState.value.managerList[managerIndex].managerHomie.userName != "담당자 없음") {
            _uiState.value.homieState[uiState.value.managerList[managerIndex].managerHomie.userName] =
                true
        }
        val tempManager = Manager(
            managerHomie = homie,
            dayDataList = uiState.value.managerList[managerIndex].dayDataList
        )
        val tempManagerList = mutableListOf<Manager>()
        _uiState.value.managerList.forEach { manager -> tempManagerList.add(manager) }
        tempManagerList[managerIndex] = tempManager
        _uiState.value.homieState[homie.userName] = false
        _uiState.value = _uiState.value.copy(managerList = tempManagerList)
    }

    fun selectDay(managerIndex: Int, dayData: DayDataInfo) {
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
        uiState.value.managerList[uiState.value.managerList.size - 1].managerHomie.userName != "담당자 없음"

    fun addManager() {
        val tempManagerList = mutableListOf<Manager>()
        val nextManager = Manager(managerHomie = nextManager())
        uiState.value.managerList.forEach { manager -> tempManagerList.add(manager) }
        tempManagerList.add(nextManager)
        _uiState.value = _uiState.value.copy(managerList = tempManagerList)
    }

    fun addNewRule() {
        viewModelScope.launch {
            addNewRuleUseCase(
                ruleName = uiState.value.ruleName,
                categoryId = uiState.value.categoryId,
                notificationState = uiState.value.notificationState,
                checkBoxState = uiState.value.checkBoxState,
                managerList = uiState.value.managerList
            )
        }
    }

    private fun nextManager(): HomieInfo {
        var tempHomie = HomieInfo("", "담당자 없음", typeColor = "NULL")
        for (i in uiState.value.homies) {
            if (uiState.value.homieState[i.userName]!!) {
                tempHomie = HomieInfo(
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

    private fun changeDayState(dayData: DayDataInfo, managerIndex: Int): List<DayDataInfo> {
        val tempDay = mutableListOf<DayDataInfo>()
        uiState.value.managerList[managerIndex].dayDataList.forEach { d ->
            if (d.day == dayData.day) {
                when (dayData.dayState) {
                    State.UNSELECT -> {
                        tempDay.add(DayDataInfo(d.day, State.SELECT))
                        setCheckBoxState("changeDayState Unselect", State.BLOCK)
                    }
                    State.SELECT -> {
                        tempDay.add(DayDataInfo(d.day, State.UNSELECT))
                        if (uiState.value.managerList.size == 1) {
                            var isCheck = true
                            uiState.value.managerList[0].dayDataList.forEach { dayData ->
                                if (dayData.dayState == State.SELECT) isCheck = false
                            }
                            if (isCheck && uiState.value.managerList[0].managerHomie.userName == "담당자 없음") {
                                setCheckBoxState("changeDayState select", State.UNSELECT)
                            }
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
    val ruleCategory: List<CategoryInfo> =
        listOf(
            CategoryInfo("1", "청소기"),
            CategoryInfo("2", "분리수거"),
            CategoryInfo("3", "세탁기"),
            CategoryInfo("4", "물 주기")
        ),
    val homies: List<HomieInfo> =
        listOf(
            HomieInfo("1", "강원용", typeColor = "RED"),
            HomieInfo("2", "이영주", typeColor = "BLUE"),
            HomieInfo("3", "이준원", typeColor = "YELLOW"),
            HomieInfo("4", "최인영", typeColor = "GREEN"),
            HomieInfo("5", "최소현", typeColor = "PURPLE")
        ),
    val homieState: HashMap<String, Boolean> = hashMapOf(
        "강원용" to true,
        "이영주" to true,
        "이준원" to true,
        "최인영" to true,
        "최소현" to true
    ),
    val managerList: List<Manager> = listOf(Manager())
)
