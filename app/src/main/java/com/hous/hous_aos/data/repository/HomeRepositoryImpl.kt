package com.hous.hous_aos.data.repository

import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.response.EventResponse
import com.hous.hous_aos.data.model.request.EventListRequest
import com.hous.hous_aos.data.model.response.HomeResponse
import com.hous.hous_aos.data.source.remote.RemoteHomeDataSource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: RemoteHomeDataSource
) : HomeRepository {
    override suspend fun getHomeList(roomId: String): Result<WrapperClass<HomeResponse>> {
        return runCatching { homeDataSource.getHomeList(roomId) }
    }

    override suspend fun getEventList(
        roomId: String,
        eventId: String
    ): Result<WrapperClass<EventResponse>> =
        runCatching { homeDataSource.getEventList(roomId, eventId) }
    override suspend fun putEventList(
        roomId: String,
        eventId: String,
        body: EventListRequest
    ): Result<WrapperClass<Any>> =
        runCatching { homeDataSource.putEventList(roomId, eventId, body) }
    override suspend fun addEvent(
        roomId: String,
        body: EventListRequest
    ): Result<WrapperClass<Any>> = runCatching { homeDataSource.addEvent(roomId, body) }
    override suspend fun deleteEvent(roomId: String, eventId: String): Result<WrapperClass<Any>> =
        runCatching { homeDataSource.deleteEvent(roomId, eventId) }
}
