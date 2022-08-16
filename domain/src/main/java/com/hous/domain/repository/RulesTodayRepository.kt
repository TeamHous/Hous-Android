package com.hous.data.repository

import com.hous.domain.model.RuleInfo
import com.hous.domain.model.TempManagerInfo
import com.hous.domain.model.rules.RulesTableInfo
import com.hous.domain.model.rules.RulesTodayInfo

interface RulesTodayRepository {
    suspend fun getTodayTodayInfoList(roomId: String): RulesTodayInfo?

    suspend fun getTempManagerInfoList(
        roomId: String,
        rulesId: String
    ): TempManagerInfo?

    suspend fun putTempManagerInfoList(
        roomId: String,
        ruleId: String,
        tmpRuleMembers: List<String>
    ): Any?

    suspend fun getMyTodoInfoList(roomId: String): List<RuleInfo>?

    suspend fun putMyToDoCheckLust(
        roomId: String,
        ruleId: String,
        isCheck: Boolean
    )

    suspend fun getRuleTableInfoList(
        roomId: String,
        categoryId: String
    ): RulesTableInfo?
}
