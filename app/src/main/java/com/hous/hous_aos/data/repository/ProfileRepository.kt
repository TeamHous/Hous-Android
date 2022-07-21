package com.hous.hous_aos.data.repository

import com.hous.hous_aos.data.entity.Homie
import com.hous.hous_aos.data.entity.ResultData
import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.request.PutTestResultRequest
import com.hous.hous_aos.data.model.response.TypeTestResponse

interface ProfileRepository {
    suspend fun getUserProfile(): Result<WrapperClass<Homie>>
    suspend fun putTestResult(typeScore: PutTestResultRequest): Result<WrapperClass<Any>>
    suspend fun getTypeTestList(): Result<WrapperClass<TypeTestResponse>>
    suspend fun getMyResult(): Result<WrapperClass<ResultData>>
}
