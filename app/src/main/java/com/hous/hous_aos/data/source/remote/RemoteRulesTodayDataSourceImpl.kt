package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.BuildConfig
import com.hous.hous_aos.data.api.RulesApi
import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.response.RulesTodayInfoListResponse
import com.hous.hous_aos.data.model.response.TempManagerRequest
import com.hous.hous_aos.data.model.response.TempManagerResponse
import javax.inject.Inject

class RemoteRulesTodayDataSourceImpl @Inject constructor(
    private val rulesApi: RulesApi
) :
    RemoteRulesTodayDataSource {
    private val ROOM_ID = BuildConfig.ROOM_ID

    override suspend fun getTodayTodayInfoList(roomId: String): WrapperClass<RulesTodayInfoListResponse> {
        return rulesApi.getTodayTodayInfoList(ROOM_ID)
    }

    override suspend fun getTempManagerInfoList(
        roomId: String,
        ruleId: String
    ): WrapperClass<TempManagerResponse> {
        return rulesApi.getTempManagerInfoList(ROOM_ID, ruleId)
    }

    override suspend fun putTempManagerInfoList(
        roomId: String,
        ruleId: String,
        tmpRuleMembers: TempManagerRequest
    ): TempManagerRequest {
        return rulesApi.putTempManagerInfoList(ROOM_ID, ruleId, tmpRuleMembers)
    }
}
