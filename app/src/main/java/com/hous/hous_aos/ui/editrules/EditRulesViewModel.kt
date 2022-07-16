package com.hous.hous_aos.ui.editrules

import androidx.lifecycle.ViewModel
import com.hous.hous_aos.ui.newrules.NewRulesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EditRulesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NewRulesUiState())
    val uiState = _uiState.asStateFlow()
}
