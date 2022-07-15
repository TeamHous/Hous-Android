package com.hous.hous_aos.ui.newrules

import androidx.lifecycle.ViewModel
import com.hous.hous_aos.data.model.response.NewRulesResponse
import com.hous.hous_aos.ui.newrules.component.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NewRulesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NewRulesUiState())
    val uiState = _uiState.asStateFlow()

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

    fun setCheckBoxState(state: State) {
        when (state) {
            State.UNSELECT -> _uiState.value = _uiState.value.copy(checkBoxState = State.UNSELECT)
            State.SELECT -> _uiState.value = _uiState.value.copy(checkBoxState = State.SELECT)
            State.BLOCK -> _uiState.value = _uiState.value.copy(checkBoxState = State.BLOCK)
        }
    }

    fun toggleNotificationState(isChange: Boolean) {
        _uiState.value = _uiState.value.copy(notificationState = isChange)
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
    )
)
