package com.hous.data.repository

import com.hous.data.model.WrapperClass
import com.hous.hous_aos.ui.newrules.NewRulesUiState

interface NewRulesRepository {
    suspend fun addNewRule(newRulesUiState: NewRulesUiState): Result<WrapperClass<Any>>
    suspend fun getNewRuleList(roomId: String): Result<WrapperClass<com.hous.data.model.response.NewRulesListResponse>>
}
