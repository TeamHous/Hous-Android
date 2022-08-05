package com.hous.data.api

import com.hous.data.entity.Event
import com.hous.data.entity.Homie
import com.hous.data.entity.ResultData
import com.hous.data.model.WrapperClass
import com.hous.data.model.request.EventListRequest
import com.hous.data.model.response.HomeResponse
import retrofit2.http.*

interface HomeApi {
    @GET("room/{roomId}/home")
    suspend fun getHomeList(
        @Path("roomId") roomId: String
    ): com.hous.data.model.WrapperClass<com.hous.data.model.response.HomeResponse>

    @DELETE("room/{roomId}/event/{eventId}")
    suspend fun deleteEvent(
        @Path("roomId") roomId: String,
        @Path("eventId") eventId: String
    ): com.hous.data.model.WrapperClass<Any>

    @GET("room/{roomId}/event/{eventId}")
    suspend fun getEventList(
        @Path("roomId") roomId: String,
        @Path("eventId") eventId: String
    ): com.hous.data.model.WrapperClass<com.hous.data.entity.Event>

    @PUT("room/{roomId}/event/{eventId}")
    suspend fun putEventList(
        @Path("roomId") roomId: String,
        @Path("eventId") eventId: String,
        @Body body: com.hous.data.model.request.EventListRequest
    ): com.hous.data.model.WrapperClass<Any>

    @POST("room/{roomId}/event")
    suspend fun addEvent(
        @Path("roomId") roomId: String,
        @Body body: com.hous.data.model.request.EventListRequest
    ): com.hous.data.model.WrapperClass<Any>

    @GET("user/{homieId}")
    suspend fun getHomieList(
        @Path("homieId") homieId: String
    ): com.hous.data.model.WrapperClass<com.hous.data.entity.Homie>

    @GET("user/{userId}/type")
    suspend fun getHomieResult(
        @Path("userId") userId: String
    ): com.hous.data.model.WrapperClass<com.hous.data.entity.ResultData>
}
