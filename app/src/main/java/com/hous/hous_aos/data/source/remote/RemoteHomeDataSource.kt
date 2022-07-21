package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.response.EventResponse
import com.hous.hous_aos.data.model.request.EventListRequest
import com.hous.hous_aos.data.model.response.HomeResponse

interface RemoteHomeDataSource {
    suspend fun getHomeList(roomId: String): WrapperClass<HomeResponse>
    suspend fun getEventList(roomId: String, eventId: String): WrapperClass<EventResponse>
    suspend fun putEventList(
        roomId: String,
        eventId: String,
        body: EventListRequest
    ): WrapperClass<Any>
}
