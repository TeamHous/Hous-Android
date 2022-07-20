package com.hous.hous_aos.data.repository

import com.hous.hous_aos.data.entity.Homie
import com.hous.hous_aos.data.model.WrapperClass

interface ProfileRepository {
    suspend fun getUserProfile(): Result<WrapperClass<Homie>>
    suspend fun putTestResult(typeScore: List<Int>): Result<WrapperClass<Any>>
}
