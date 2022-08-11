package com.hous.data.source.remote

import com.hous.data.model.request.MyToDoCheckRequest
import com.hous.data.model.request.TempManagerRequest
import com.hous.domain.model.RuleInfo
import com.hous.domain.model.TempManagerInfo
import com.hous.domain.model.rules.RulesTableInfo
import com.hous.domain.model.rules.RulesTodayInfo

interface RemoteRulesTodayDataSource {
    suspend fun getTodayTodayInfoList(roomId: String): RulesTodayInfo?

    suspend fun getTempManagerInfoList(
        roomId: String,
        ruleId: String
    ): TempManagerInfo?

    suspend fun putTempManagerInfoList(
        roomId: String,
        ruleId: String,
        tmpRuleMembers: TempManagerRequest
    ): Any?

    suspend fun getMyToDoInfoList(roomId: String): List<RuleInfo>?

    suspend fun putMyToDoCheckLust(
        roomId: String,
        ruleId: String,
        isCheck: MyToDoCheckRequest
    )

    suspend fun getRuleTableInfoList(
        roomId: String,
        categoryId: String
    ): RulesTableInfo?
}
