package com.hous.hous_aos.data.repository

import com.hous.data.model.WrapperClass
import com.hous.data.model.response.NewRulesListResponse
import com.hous.hous_aos.ui.newrules.NewRulesUiState

interface NewRulesRepository {
    suspend fun addNewRule(newRulesUiState: NewRulesUiState): Result<com.hous.data.model.WrapperClass<Any>>
    suspend fun getNewRuleList(roomId: String): Result<com.hous.data.model.WrapperClass<com.hous.data.model.response.NewRulesListResponse>>
}
