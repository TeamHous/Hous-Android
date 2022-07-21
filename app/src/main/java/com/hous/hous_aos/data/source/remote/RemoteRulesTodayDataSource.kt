package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.data.entity.Rule
import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.request.MyToDoCheckRequest
import com.hous.hous_aos.data.model.response.RulesTableResponse
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
    ): WrapperClass<Any>

    suspend fun getMyToDoInfoList(roomId: String): WrapperClass<List<Rule>>

    suspend fun putMyToDoCheckLust(
        roomId: String,
        ruleId: String,
        isCheck: MyToDoCheckRequest
    ): WrapperClass<MyToDoCheckRequest>

    suspend fun getRuleTableInfoList(
        roomId: String,
        categoryId: String,
    ): WrapperClass<RulesTableResponse>
}
