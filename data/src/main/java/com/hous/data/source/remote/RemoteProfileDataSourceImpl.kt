package com.hous.data.source.remote

import com.hous.data.BuildConfig
import com.hous.data.api.ProfileApi
import com.hous.data.entity.Homie
import com.hous.data.entity.ResultData
import com.hous.data.model.WrapperClass
import com.hous.data.model.request.PutTestResultRequest
import com.hous.data.model.response.TypeTestListResponse
import javax.inject.Inject

class RemoteProfileDataSourceImpl @Inject constructor(
    private val profileApi: ProfileApi
) : RemoteProfileDataSource {
    private val ROOM_ID = BuildConfig.ROOM_ID
    override suspend fun getUserProfile(): WrapperClass<Homie> = profileApi.getUserProfile()

    override suspend fun putTestResult(typeScore: PutTestResultRequest): WrapperClass<Any> =
        profileApi.putTestResult(typeScore)

    override suspend fun getTypeTestList(): WrapperClass<TypeTestListResponse> =
        profileApi.getTypeTestList()

    override suspend fun getMyResult(): WrapperClass<ResultData> =
        profileApi.getMyResult()
}
