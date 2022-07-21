package com.hous.hous_aos.data.repository

import com.hous.hous_aos.data.model.WrapperClass
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
    ): Result<TempManagerRequest> {
        return runCatching {
            remoteRulesTodayDataSource.putTempManagerInfoList(
                roomId,
                ruleId,
                tmpRuleMembers
            )
        }
    }
}
