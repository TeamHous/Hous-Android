package com.hous.domain.repository

import com.hous.domain.model.Manager
import com.hous.domain.model.NewRuleInfo
import com.hous.domain.model.State

interface NewRulesRepository {
    suspend fun addNewRule(
        ruleName: String,
        categoryId: String,
        notificationState: Boolean,
        checkBoxState: State,
        managerList: List<Manager>
    )

    suspend fun getNewRuleList(roomId: String): Result<NewRuleInfo>
}
