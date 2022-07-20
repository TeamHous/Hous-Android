package com.hous.hous_aos.data.api

import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.request.NewRulesRequest
import com.hous.hous_aos.data.model.response.NewRulesListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NewRulesApi {
    @POST("room/{roomId}/rule")
    suspend fun addNewRule(
        @Path("roomId") roomId: String,
        @Body newRulesRequest: NewRulesRequest
    ): WrapperClass<Any>

    @GET("room/{roomId}/rule/new")
    suspend fun getNewRuleList(
        @Path("roomId") roomId: String
    ): WrapperClass<NewRulesListResponse>
}
