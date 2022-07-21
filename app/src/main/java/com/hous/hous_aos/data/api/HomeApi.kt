package com.hous.hous_aos.data.api

import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.response.HomeResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApi {
    @GET("room/{roomId}/home")
    suspend fun getHomeList(
        @Path("roomId") roomId: String
    ): WrapperClass<HomeResponse>

    @DELETE("room/{roomId}/event/{eventId}")
    suspend fun deleteEvent(
        @Path("roomId") roomId: String,
        @Path("eventId") eventId: String
    ): WrapperClass<Any>
}
