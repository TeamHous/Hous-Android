package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.data.entity.Rule
import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.response.RulesTodayInfoListResponse
import com.hous.hous_aos.data.model.response.TempManagerRequest
import com.hous.hous_aos.data.model.response.TempManagerResponse

interface RemoteRulesTodayDataSource {
    suspend fun getTodayTodayInfoList(roomId: String): WrapperClass<RulesTodayInfoListResponse>

    suspend fun getTempManagerInfoList(
        roomId: String,
        ruleId: String
    ): WrapperClass<TempManagerResponse>

    suspend fun putTempManagerInfoList(
        roomId: String,
        ruleId: String,
        tmpRuleMembers: TempManagerRequest
    ): TempManagerRequest

    suspend fun getMyToDoInfoList(roomId: String): WrapperClass<List<Rule>>
}
