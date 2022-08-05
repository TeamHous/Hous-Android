package com.hous.data.repository

import com.hous.data.entity.Rule
import com.hous.data.model.WrapperClass
import com.hous.data.model.request.MyToDoCheckRequest
import com.hous.data.model.response.RulesTableResponse
import com.hous.data.model.response.RulesTodayInfoListResponse
import com.hous.data.model.response.TempManagerRequest
import com.hous.data.model.response.TempManagerResponse

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
    ): Result<WrapperClass<Any>>

    suspend fun getMyTodoInfoList(roomId: String): Result<WrapperClass<List<Rule>>>

    suspend fun putMyToDoCheckLust(
        roomId: String,
        ruleId: String,
        isCheck: MyToDoCheckRequest
    ): Result<WrapperClass<MyToDoCheckRequest>>

    suspend fun getRuleTableInfoList(
        roomId: String,
        categoryId: String
    ): Result<WrapperClass<RulesTableResponse>>
}
