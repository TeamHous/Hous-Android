package com.hous.data.repository

import com.hous.data.entity.Manager
import com.hous.data.entity.State
import com.hous.data.model.WrapperClass

interface NewRulesRepository {
    suspend fun addNewRule(
        ruleName: String,
        categoryId: String,
        notificationState: Boolean,
        checkBoxState: State,
        managerList: List<Manager>
    ): Result<WrapperClass<Any>>

    suspend fun getNewRuleList(roomId: String): Result<WrapperClass<com.hous.data.model.response.NewRulesListResponse>>
}
