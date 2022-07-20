package com.hous.hous_aos.data.repository

import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.response.RulesTodayInfoListResponse

interface RulesTodayRepository {
    suspend fun getTodayTodayInfoList(roomId: String): Result<WrapperClass<RulesTodayInfoListResponse>>
}
