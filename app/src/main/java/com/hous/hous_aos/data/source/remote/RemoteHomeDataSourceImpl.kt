package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.BuildConfig
import com.hous.hous_aos.data.api.HomeApi
import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.request.EventListRequest
import com.hous.hous_aos.data.model.response.EventResponse
import com.hous.hous_aos.data.model.response.HomeResponse
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
    ): WrapperClass<EventResponse> = homeApi.getEventList(ROOM_ID, eventId)

    override suspend fun addEvent(roomId: String, body: EventListRequest): WrapperClass<Any> =
        homeApi.addEvent(ROOM_ID, body)
}
