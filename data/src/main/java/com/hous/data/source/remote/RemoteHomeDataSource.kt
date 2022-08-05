package com.hous.data.source.remote

import com.hous.data.entity.Event
import com.hous.data.entity.Homie
import com.hous.data.entity.ResultData
import com.hous.data.model.WrapperClass
import com.hous.data.model.request.EventListRequest
import com.hous.data.model.response.HomeResponse

interface RemoteHomeDataSource {
    suspend fun getHomeList(roomId: String): WrapperClass<HomeResponse>
    suspend fun getEventList(roomId: String, eventId: String): WrapperClass<Event>
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
