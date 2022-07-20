package com.hous.hous_aos.data.api

import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.response.RulesTodayInfoListResponse
import com.hous.hous_aos.data.model.response.TempManagerResponse
import retrofit2.http.GET
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
}
