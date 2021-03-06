package com.hous.hous_aos.data.api

import com.hous.hous_aos.data.entity.Rule
import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.request.MyToDoCheckRequest
import com.hous.hous_aos.data.model.response.RulesTableResponse
import com.hous.hous_aos.data.model.response.RulesTodayInfoListResponse
import com.hous.hous_aos.data.model.response.TempManagerRequest
import com.hous.hous_aos.data.model.response.TempManagerResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface RulesApi {
    @GET("room/{roomId}/rules")
    suspend fun getTodayTodayInfoList(
        @Path("roomId") roomId: String
    ): WrapperClass<RulesTodayInfoListResponse>

    @GET("/room/{roomId}/rule/{ruleId}/today")
    suspend fun getTempManagerInfoList(
        @Path("roomId") roomId: String,
        @Path("ruleId") ruleId: String
    ): WrapperClass<TempManagerResponse>

    @PUT("/room/{roomId}/rule/{ruleId}/today")
    suspend fun putTempManagerInfoList(
        @Path("roomId") roomId: String,
        @Path("ruleId") ruleId: String,
        @Body tmpRuleMembers: TempManagerRequest
    ): WrapperClass<Any>

    @GET("/room/{roomId}/rules/me")
    suspend fun getMyTodoInfoList(
        @Path("roomId") roomId: String
    ): WrapperClass<List<Rule>>

    @PUT("room/{roomId}/rule/{ruleId}/check")
    suspend fun putMyToDoCheckList(
        @Path("roomId") roomId: String,
        @Path("ruleId") ruleId: String,
        @Body isCheck: MyToDoCheckRequest
    ): WrapperClass<MyToDoCheckRequest>

    @GET("/room/{roomId}/category/{categoryId}/rule")
    suspend fun getRuleTableInfoList(
        @Path("roomId") roomId: String,
        @Path("categoryId") categoryId: String,
    ): WrapperClass<RulesTableResponse>
}
