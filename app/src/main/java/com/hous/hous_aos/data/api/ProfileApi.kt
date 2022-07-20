package com.hous.hous_aos.data.api

import com.hous.hous_aos.data.entity.Homie
import com.hous.hous_aos.data.model.WrapperClass
import retrofit2.http.GET

interface ProfileApi {
    @GET("user/profile")
    suspend fun getUserProfile(): WrapperClass<Homie>
}
