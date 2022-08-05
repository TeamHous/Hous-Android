package com.hous.data.source.remote

import com.hous.data.api.HomeApi
import com.hous.data.entity.Event
import com.hous.data.entity.Homie
import com.hous.data.entity.ResultData
import com.hous.data.model.WrapperClass
import com.hous.data.model.request.EventListRequest
import com.hous.data.model.response.HomeResponse
import com.hous.hous_aos.BuildConfig
import javax.inject.Inject

class RemoteHomeDataSourceImpl @Inject constructor(
    private val homeApi: HomeApi
) : RemoteHomeDataSource {
    private val ROOM_ID = BuildConfig.ROOM_ID
    override suspend fun getHomeList(roomId: String): WrapperClass<HomeResponse> =
        homeApi.getHomeList(ROOM_ID)

    override suspend fun putEventList(
        roomId: String,
        eventId: String,
        body: EventListRequest
    ): WrapperClass<Any> = homeApi.putEventList(ROOM_ID, eventId, body)

    override suspend fun getEventList(
        roomId: String,
        eventId: String
    ): WrapperClass<Event> = homeApi.getEventList(ROOM_ID, eventId)

    override suspend fun addEvent(roomId: String, body: EventListRequest): WrapperClass<Any> =
        homeApi.addEvent(ROOM_ID, body)

    override suspend fun deleteEvent(roomId: String, eventId: String): WrapperClass<Any> =
        homeApi.deleteEvent(ROOM_ID, eventId)

    override suspend fun getHomieList(homieId: String): WrapperClass<Homie> =
        homeApi.getHomieList(homieId)

    override suspend fun getHomieResult(userId: String): WrapperClass<ResultData> =
        homeApi.getHomieResult(userId)
}
