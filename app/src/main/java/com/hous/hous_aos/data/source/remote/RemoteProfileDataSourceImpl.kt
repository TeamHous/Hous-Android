package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.BuildConfig
import com.hous.hous_aos.data.api.ProfileApi
import com.hous.hous_aos.data.entity.Homie
import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.request.PutTestResultRequest
import com.hous.hous_aos.data.model.response.TypeTestResponse
import javax.inject.Inject

class RemoteProfileDataSourceImpl @Inject constructor(
    private val profileApi: ProfileApi
) : RemoteProfileDataSource {
    private val ROOM_ID = BuildConfig.ROOM_ID
    override suspend fun getUserProfile(): WrapperClass<Homie> = profileApi.getUserProfile()

    override suspend fun putTestResult(typeScore: PutTestResultRequest): WrapperClass<Any> =
        profileApi.putTestResult(typeScore)

    override suspend fun getTypeTestList(): WrapperClass<TypeTestResponse> =
        profileApi.getTypeTestList()
}
