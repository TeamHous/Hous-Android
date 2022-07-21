package com.hous.hous_aos.data.api

import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.request.EventListRequest
import com.hous.hous_aos.data.model.response.HomeResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface HomeApi {
    @GET("room/{roomId}/home")
    suspend fun getHomeList(
        @Path("roomId") roomId: String
    ): WrapperClass<HomeResponse>

    @PUT("room/{roomId}/event/{eventId}")
    suspend fun putEventList(
        @Path("roomId") roomId: String,
        @Path("eventId") eventId: String,
        @Body body: EventListRequest
    ): WrapperClass<Any>
}
