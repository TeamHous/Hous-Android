package com.hous.hous_aos.data.repository

import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.response.EventResponse
import com.hous.hous_aos.data.model.request.EventListRequest
import com.hous.hous_aos.data.model.response.HomeResponse

interface HomeRepository {
    suspend fun getHomeList(roomId: String): Result<WrapperClass<HomeResponse>>
    suspend fun getEventList(roomId: String, eventId: String): Result<WrapperClass<EventResponse>>
    suspend fun putEventList(
        roomId: String,
        eventId: String,
        body: EventListRequest
    ): Result<WrapperClass<Any>>
}
    suspend fun addEvent(roomId: String, body: EventListRequest): Result<WrapperClass<Any>>
}
}
