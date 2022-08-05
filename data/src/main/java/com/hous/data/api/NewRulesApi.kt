package com.hous.data.api

import com.hous.data.model.WrapperClass
import com.hous.data.model.request.NewRulesRequest
import com.hous.data.model.response.NewRulesListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NewRulesApi {
    @POST("room/{roomId}/rule")
    suspend fun addNewRule(
        @Path("roomId") roomId: String,
        @Body newRulesRequest: com.hous.data.model.request.NewRulesRequest
    ): com.hous.data.model.WrapperClass<Any>

    @GET("room/{roomId}/rule/new")
    suspend fun getNewRuleList(
        @Path("roomId") roomId: String
    ): com.hous.data.model.WrapperClass<com.hous.data.model.response.NewRulesListResponse>
}
