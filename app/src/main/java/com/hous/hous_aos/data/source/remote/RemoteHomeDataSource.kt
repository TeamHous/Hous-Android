package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.response.HomeResponse
import retrofit2.http.Path

interface RemoteHomeDataSource{
    suspend fun getHomeList(roomId: String): WrapperClass<HomeResponse>
}
