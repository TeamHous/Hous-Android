package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.request.EventListRequest
import com.hous.hous_aos.data.model.response.HomeResponse

interface RemoteHomeDataSource {
    suspend fun getHomeList(roomId: String): WrapperClass<HomeResponse>
    suspend fun addEvent(roomId: String, body: EventListRequest): WrapperClass<Any>
}
