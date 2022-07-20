package com.hous.hous_aos.data.api

import com.hous.hous_aos.data.entity.Homie
import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.ui.profile.TypeTest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface ProfileApi {
    @GET("user/profile")
    suspend fun getUserProfile(): WrapperClass<Homie>

    @PUT("user/type/test")
    suspend fun putTestResult(
        @Body typeScore: List<Int>
    ): WrapperClass<Any>

    @GET("type/test")
    suspend fun getTypeTestList(): WrapperClass<List<TypeTest>>
}
