package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.BuildConfig
import com.hous.hous_aos.data.api.RulesApi
import com.hous.hous_aos.data.entity.Rule
import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.request.MyToDoCheckRequest
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
    ): WrapperClass<Any> {
        return rulesApi.putTempManagerInfoList(ROOM_ID, ruleId, tmpRuleMembers)
    }

    override suspend fun getMyToDoInfoList(roomId: String): WrapperClass<List<Rule>> {
        return rulesApi.getMyTodoInfoList(ROOM_ID)
    }

    override suspend fun putMyToDoCheckLust(roomId: String, ruleId: String, isCheck: MyToDoCheckRequest): WrapperClass<MyToDoCheckRequest> {
        return rulesApi.putMyToDoCheckLust(ROOM_ID, ruleId, isCheck)
    }
}
