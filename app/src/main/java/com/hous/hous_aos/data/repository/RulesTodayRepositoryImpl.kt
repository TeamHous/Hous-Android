package com.hous.hous_aos.data.repository

import com.hous.hous_aos.data.entity.Rule
import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.request.MyToDoCheckRequest
import com.hous.hous_aos.data.model.response.RulesTableResponse
import com.hous.hous_aos.data.model.response.RulesTodayInfoListResponse
import com.hous.hous_aos.data.model.response.TempManagerRequest
import com.hous.hous_aos.data.model.response.TempManagerResponse
import com.hous.hous_aos.data.source.remote.RemoteRulesTodayDataSource
import javax.inject.Inject

class RulesTodayRepositoryImpl @Inject constructor(
    private val remoteRulesTodayDataSource: RemoteRulesTodayDataSource
) : RulesTodayRepository {
    override suspend fun getTodayTodayInfoList(roomId: String): Result<WrapperClass<RulesTodayInfoListResponse>> {

        return runCatching { remoteRulesTodayDataSource.getTodayTodayInfoList(roomId) }
    }

    override suspend fun getTempManagerInfoList(
        roomId: String,
        rulesId: String
    ): Result<WrapperClass<TempManagerResponse>> {
        return runCatching { remoteRulesTodayDataSource.getTempManagerInfoList(roomId, rulesId) }
    }

    override suspend fun putTempManagerInfoList(
        roomId: String,
        ruleId: String,
        tmpRuleMembers: TempManagerRequest
    ): Result<WrapperClass<Any>> {
        return runCatching {
            remoteRulesTodayDataSource.putTempManagerInfoList(
                roomId,
                ruleId,
                tmpRuleMembers
            )
        }
    }

    override suspend fun getMyTodoInfoList(roomId: String): Result<WrapperClass<List<Rule>>> {
        return runCatching {
            remoteRulesTodayDataSource.getMyToDoInfoList(roomId)
        }
    }

    override suspend fun putMyToDoCheckLust(
        roomId: String,
        ruleId: String,
        isCheck: MyToDoCheckRequest
    ): Result<WrapperClass<MyToDoCheckRequest>> {
        return runCatching {
            remoteRulesTodayDataSource.putMyToDoCheckLust(roomId, ruleId, isCheck)
        }
    }

    override suspend fun getRuleTableInfoList(
        roomId: String,
        categoryId: String
    ): Result<WrapperClass<RulesTableResponse>> {
        return runCatching {
            remoteRulesTodayDataSource.getRuleTableInfoList(roomId, categoryId)
        }
    }
}
