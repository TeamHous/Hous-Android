package com.hous.hous_aos.data.api

import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.response.RulesTodayInfoListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RulesApi {
    @GET("room/{roomId}/rules")
    suspend fun getTodayTodayInfoList(
        @Path("roomId") roomId: String
    ): WrapperClass<RulesTodayInfoListResponse>
}
