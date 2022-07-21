package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.data.entity.Homie
import com.hous.hous_aos.data.entity.ResultData
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
    suspend fun addEvent(roomId: String, body: EventListRequest): WrapperClass<Any>
    suspend fun deleteEvent(roomId: String, eventId: String): WrapperClass<Any>
    suspend fun getHomieList(homieId: String): WrapperClass<Homie>
    suspend fun getHomieResult(userId: String): WrapperClass<ResultData>
}
