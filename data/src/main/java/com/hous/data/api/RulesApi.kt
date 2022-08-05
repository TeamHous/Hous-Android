package com.hous.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface RulesApi {
    @GET("room/{roomId}/rules")
    suspend fun getTodayTodayInfoList(
        @Path("roomId") roomId: String
    ): com.hous.data.model.WrapperClass<com.hous.data.model.response.RulesTodayInfoListResponse>

    @GET("/room/{roomId}/rule/{ruleId}/today")
    suspend fun getTempManagerInfoList(
        @Path("roomId") roomId: String,
        @Path("ruleId") ruleId: String
    ): com.hous.data.model.WrapperClass<com.hous.data.model.response.TempManagerResponse>

    @PUT("/room/{roomId}/rule/{ruleId}/today")
    suspend fun putTempManagerInfoList(
        @Path("roomId") roomId: String,
        @Path("ruleId") ruleId: String,
        @Body tmpRuleMembers: com.hous.data.model.response.TempManagerRequest
    ): com.hous.data.model.WrapperClass<Any>

    @GET("/room/{roomId}/rules/me")
    suspend fun getMyTodoInfoList(
        @Path("roomId") roomId: String
    ): com.hous.data.model.WrapperClass<List<com.hous.data.entity.Rule>>

    @PUT("room/{roomId}/rule/{ruleId}/check")
    suspend fun putMyToDoCheckList(
        @Path("roomId") roomId: String,
        @Path("ruleId") ruleId: String,
        @Body isCheck: com.hous.data.model.request.MyToDoCheckRequest
    ): com.hous.data.model.WrapperClass<com.hous.data.model.request.MyToDoCheckRequest>

    @GET("/room/{roomId}/category/{categoryId}/rule")
    suspend fun getRuleTableInfoList(
        @Path("roomId") roomId: String,
        @Path("categoryId") categoryId: String,
    ): com.hous.data.model.WrapperClass<com.hous.data.model.response.RulesTableResponse>
}
