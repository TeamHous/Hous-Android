package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.response.HomeResponse

interface RemoteHomeDataSource {
    suspend fun getHomeList(roomId: String): WrapperClass<HomeResponse>
    suspend fun deleteEvent(roomId: String, eventId: String): WrapperClass<Any>
}
