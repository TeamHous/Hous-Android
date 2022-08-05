package com.hous.data.source.remote

import com.hous.data.entity.Homie
import com.hous.data.entity.ResultData
import com.hous.data.model.WrapperClass
import com.hous.data.model.request.PutTestResultRequest
import com.hous.data.model.response.TypeTestResponse

interface RemoteProfileDataSource {
    suspend fun getUserProfile(): WrapperClass<Homie>
    suspend fun putTestResult(typeScore: PutTestResultRequest): WrapperClass<Any>
    suspend fun getTypeTestList(): WrapperClass<TypeTestResponse>
    suspend fun getMyResult(): WrapperClass<ResultData>
}
