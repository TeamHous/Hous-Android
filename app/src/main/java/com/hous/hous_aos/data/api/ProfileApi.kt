package com.hous.hous_aos.data.api

import com.hous.hous_aos.data.entity.Homie
import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.request.PutTestResultRequest
import com.hous.hous_aos.data.model.response.TypeTestResponse
import com.hous.hous_aos.data.entity.ResultData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProfileApi {
    @GET("user/profile")
    suspend fun getUserProfile(): WrapperClass<Homie>

    @PUT("user/type/test")
    suspend fun putTestResult(
        @Body body: PutTestResultRequest
    ): WrapperClass<Any>

    @GET("type/test")
    suspend fun getTypeTestList(): WrapperClass<TypeTestResponse>

    @GET("user/me/type")
    suspend fun getMyResult(
        @Path("typeId") typeId: String
    ): WrapperClass<ResultData>
}
