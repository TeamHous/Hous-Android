package com.hous.data.repository

import com.hous.data.entity.Homie
import com.hous.data.entity.ResultData
import com.hous.data.model.WrapperClass
import com.hous.data.model.request.PutTestResultRequest
import com.hous.data.model.response.TypeTestListResponse
import com.hous.data.source.remote.RemoteProfileDataSource
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDataSource: RemoteProfileDataSource
) : ProfileRepository {
    override suspend fun getUserProfile(): Result<WrapperClass<Homie>> =
        runCatching { profileDataSource.getUserProfile() }

    override suspend fun putTestResult(typeScore: PutTestResultRequest): Result<WrapperClass<Any>> =
        runCatching { profileDataSource.putTestResult(typeScore) }

    override suspend fun getTypeTestList(): Result<WrapperClass<TypeTestListResponse>> =
        runCatching { profileDataSource.getTypeTestList() }

    override suspend fun getMyResult(): Result<WrapperClass<ResultData>> =
        runCatching { profileDataSource.getMyResult() }
}
