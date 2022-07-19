package com.hous.hous_aos.data.repository

import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.ui.newrules.NewRulesUiState

interface NewRulesRepository {
    suspend fun addNewRule(newRulesUiState: NewRulesUiState): Result<WrapperClass<Any>>
}
