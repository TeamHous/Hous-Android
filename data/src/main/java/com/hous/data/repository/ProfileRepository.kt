package com.hous.data.repository

import com.hous.data.entity.Homie
import com.hous.data.entity.ResultData
import com.hous.data.model.WrapperClass
import com.hous.data.model.request.PutTestResultRequest
import com.hous.data.model.response.TypeTestResponse

interface ProfileRepository {
    suspend fun getUserProfile(): Result<WrapperClass<Homie>>
    suspend fun putTestResult(typeScore: PutTestResultRequest): Result<WrapperClass<Any>>
    suspend fun getTypeTestList(): Result<WrapperClass<TypeTestResponse>>
    suspend fun getMyResult(): Result<WrapperClass<ResultData>>
}
