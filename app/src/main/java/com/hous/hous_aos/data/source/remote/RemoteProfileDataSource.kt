package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.data.entity.Homie
import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.request.PutTestResultRequest
import com.hous.hous_aos.data.model.response.TestResultResponse
import com.hous.hous_aos.data.model.response.TypeTestResponse

interface RemoteProfileDataSource {
    suspend fun getUserProfile(): WrapperClass<Homie>
    suspend fun putTestResult(typeScore: PutTestResultRequest): WrapperClass<Any>
    suspend fun getTypeTestList(): WrapperClass<TypeTestResponse>
    suspend fun getMyResult(typeId: Int): WrapperClass<TestResultResponse>
}
