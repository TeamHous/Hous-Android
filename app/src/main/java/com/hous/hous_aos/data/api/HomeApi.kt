package com.hous.hous_aos.data.api

import com.hous.hous_aos.data.entity.Event
import com.hous.hous_aos.data.entity.Homie
import com.hous.hous_aos.data.entity.ResultData
import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.request.EventListRequest
import com.hous.hous_aos.data.model.response.HomeResponse
import retrofit2.http.*

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

    @GET("room/{roomId}/event/{eventId}")
    suspend fun getEventList(
        @Path("roomId") roomId: String,
        @Path("eventId") eventId: String
    ): WrapperClass<Event>

    @PUT("room/{roomId}/event/{eventId}")
    suspend fun putEventList(
        @Path("roomId") roomId: String,
        @Path("eventId") eventId: String,
        @Body body: EventListRequest
    ): WrapperClass<Any>

    @POST("room/{roomId}/event")
    suspend fun addEvent(
        @Path("roomId") roomId: String,
        @Body body: EventListRequest
    ): WrapperClass<Any>

    @GET("user/{homieId}")
    suspend fun getHomieList(
        @Path("homieId") homieId: String
    ): WrapperClass<Homie>

    @GET("user/{userId}/type")
    suspend fun getHomieResult(
        @Path("userId") userId: String
    ): WrapperClass<ResultData>
}
