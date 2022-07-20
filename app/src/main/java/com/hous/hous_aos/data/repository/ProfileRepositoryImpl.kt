package com.hous.hous_aos.data.repository

import com.hous.hous_aos.data.entity.Homie
import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.source.remote.RemoteProfileDataSource
import com.hous.hous_aos.ui.profile.TypeTest
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDataSource: RemoteProfileDataSource
) : ProfileRepository {
    override suspend fun getUserProfile(): Result<WrapperClass<Homie>> =
        runCatching { profileDataSource.getUserProfile() }

    override suspend fun putTestResult(typeScore: List<Int>): Result<WrapperClass<Any>> =
        runCatching { profileDataSource.putTestResult(typeScore) }

    override suspend fun getTypeTestList(): Result<WrapperClass<List<TypeTest>>> =
        runCatching { profileDataSource.getTypeTestList() }
}
