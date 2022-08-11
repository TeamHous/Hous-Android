package com.hous.data.api

import com.hous.data.entity.Event
import com.hous.data.entity.Homie
import com.hous.data.entity.ResultData
import com.hous.data.model.WrapperClass
import com.hous.data.model.request.EventListRequest
import com.hous.data.model.response.HomeResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
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
