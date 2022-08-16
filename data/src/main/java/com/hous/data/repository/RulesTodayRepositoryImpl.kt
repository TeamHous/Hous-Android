package com.hous.data.repository

import com.hous.data.model.request.MyToDoCheckRequest
import com.hous.data.model.request.TempManagerRequest
import com.hous.data.source.remote.RemoteRulesTodayDataSource
import com.hous.domain.model.RuleInfo
import com.hous.domain.model.TempManagerInfo
import com.hous.domain.model.rules.RulesTableInfo
import com.hous.domain.model.rules.RulesTodayInfo
import javax.inject.Inject

class RulesTodayRepositoryImpl @Inject constructor(
    private val remoteRulesTodayDataSource: RemoteRulesTodayDataSource
) : RulesTodayRepository {
    override suspend fun getTodayTodayInfoList(roomId: String): RulesTodayInfo? =
        remoteRulesTodayDataSource.getTodayTodayInfoList(roomId)

    override suspend fun getTempManagerInfoList(
        roomId: String,
        rulesId: String
    ): TempManagerInfo? = remoteRulesTodayDataSource.getTempManagerInfoList(roomId, rulesId)

    override suspend fun putTempManagerInfoList(
        roomId: String,
        ruleId: String,
        tmpRuleMembers: List<String>
    ): Any? {
        return remoteRulesTodayDataSource.putTempManagerInfoList(
            roomId,
            ruleId,
            TempManagerRequest(tmpRuleMembers)
        )
    }

    override suspend fun getMyTodoInfoList(roomId: String): List<RuleInfo>? {
        return remoteRulesTodayDataSource.getMyToDoInfoList(roomId)
    }

    override suspend fun putMyToDoCheckLust(
        roomId: String,
        ruleId: String,
        isCheck: Boolean
    ) {
        remoteRulesTodayDataSource.putMyToDoCheckLust(roomId, ruleId, MyToDoCheckRequest(isCheck))
    }

    override suspend fun getRuleTableInfoList(
        roomId: String,
        categoryId: String
    ): RulesTableInfo? =
        remoteRulesTodayDataSource.getRuleTableInfoList(roomId, categoryId)
}
