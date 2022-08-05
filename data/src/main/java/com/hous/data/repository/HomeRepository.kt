package com.hous.data.repository

import com.hous.data.entity.Event
import com.hous.data.entity.Homie
import com.hous.data.entity.ResultData
import com.hous.data.model.WrapperClass
import com.hous.data.model.request.EventListRequest
import com.hous.data.model.response.HomeResponse

interface HomeRepository {
    suspend fun getHomeList(roomId: String): Result<WrapperClass<HomeResponse>>
    suspend fun getEventList(roomId: String, eventId: String): Result<WrapperClass<Event>>
    suspend fun putEventList(
        roomId: String,
        eventId: String,
        body: EventListRequest
    ): Result<WrapperClass<Any>>

    suspend fun addEvent(roomId: String, body: EventListRequest): Result<WrapperClass<Any>>
    suspend fun deleteEvent(roomId: String, eventId: String): Result<WrapperClass<Any>>
    suspend fun getHomieList(homieId: String): Result<WrapperClass<Homie>>
    suspend fun getHomieResult(userId: String): Result<WrapperClass<ResultData>>
}
