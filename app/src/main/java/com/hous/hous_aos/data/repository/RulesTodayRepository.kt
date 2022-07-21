package com.hous.hous_aos.data.repository

import com.hous.hous_aos.data.entity.Rule
import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.response.RulesTodayInfoListResponse
import com.hous.hous_aos.data.model.response.TempManagerRequest
import com.hous.hous_aos.data.model.response.TempManagerResponse

interface RulesTodayRepository {
    suspend fun getTodayTodayInfoList(roomId: String): Result<WrapperClass<RulesTodayInfoListResponse>>

    suspend fun getTempManagerInfoList(
        roomId: String,
        rulesId: String
    ): Result<WrapperClass<TempManagerResponse>>

    suspend fun putTempManagerInfoList(
        roomId: String,
        ruleId: String,
        tmpRuleMembers: TempManagerRequest
    ): Result<TempManagerRequest>

    suspend fun getMyTodoInfoList(roomId: String): Result<WrapperClass<List<Rule>>>

}
