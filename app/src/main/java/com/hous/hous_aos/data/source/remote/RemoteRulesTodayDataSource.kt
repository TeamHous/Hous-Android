package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.response.RulesTodayInfoListResponse

interface RemoteRulesTodayDataSource {
    suspend fun getTodayTodayInfoList(roomId: String): WrapperClass<RulesTodayInfoListResponse>
}
