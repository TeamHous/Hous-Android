package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.response.RulesTodayInfoListResponse
import com.hous.hous_aos.data.model.response.TempManagerResponse

interface RemoteRulesTodayDataSource {
    suspend fun getTodayTodayInfoList(roomId: String): WrapperClass<RulesTodayInfoListResponse>

    suspend fun getTempManagerInfoList(
        roomId: String,
        ruleId: String
    ): WrapperClass<TempManagerResponse>
}
