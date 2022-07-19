package com.hous.hous_aos.data.api

import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.request.NewRulesRequest
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface NewRulesApi {
    @POST("/room/{roomId}/rule")
    suspend fun addNewRule(
        @Path("roomId") roomId: String,
        @Body newRulesRequest: NewRulesRequest
    ): WrapperClass<Any>
}
