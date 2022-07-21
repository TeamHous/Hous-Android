package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.BuildConfig
import com.hous.hous_aos.data.api.HomeApi
import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.request.EventListRequest
import com.hous.hous_aos.data.model.response.HomeResponse
import javax.inject.Inject

class RemoteHomeDataSourceImpl @Inject constructor(
    private val homeApi: HomeApi
) : RemoteHomeDataSource {
    private val ROOM_ID = BuildConfig.ROOM_ID
    override suspend fun getHomeList(roomId: String): WrapperClass<HomeResponse> =
        homeApi.getHomeList(ROOM_ID)

    override suspend fun addEvent(roomId: String, body: EventListRequest): WrapperClass<Any> =
        homeApi.addEvent(roomId, body)
}
