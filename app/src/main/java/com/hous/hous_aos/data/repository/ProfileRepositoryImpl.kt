package com.hous.hous_aos.data.repository

import com.hous.hous_aos.data.entity.Homie
import com.hous.hous_aos.data.entity.ResultData
import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.request.PutTestResultRequest
import com.hous.hous_aos.data.model.response.TypeTestResponse
import com.hous.hous_aos.data.source.remote.RemoteProfileDataSource
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDataSource: RemoteProfileDataSource
) : ProfileRepository {
    override suspend fun getUserProfile(): Result<WrapperClass<Homie>> =
        runCatching { profileDataSource.getUserProfile() }

    override suspend fun putTestResult(typeScore: PutTestResultRequest): Result<WrapperClass<Any>> =
        runCatching { profileDataSource.putTestResult(typeScore) }

    override suspend fun getTypeTestList(): Result<WrapperClass<TypeTestResponse>> =
        runCatching { profileDataSource.getTypeTestList() }

    override suspend fun getMyResult(typeId: String): Result<WrapperClass<ResultData>> =
        runCatching { profileDataSource.getMyResult(typeId) }
}
