package com.hous.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface ProfileApi {
    @GET("user/profile")
    suspend fun getUserProfile(): com.hous.data.model.WrapperClass<com.hous.data.entity.Homie>

    @PUT("user/type/test")
    suspend fun putTestResult(
        @Body body: com.hous.data.model.request.PutTestResultRequest
    ): com.hous.data.model.WrapperClass<Any>

    @GET("type/test")
    suspend fun getTypeTestList(): com.hous.data.model.WrapperClass<com.hous.data.model.response.TypeTestListResponse>

    @GET("user/me/type")
    suspend fun getMyResult(): com.hous.data.model.WrapperClass<com.hous.data.entity.ResultData>
}
