package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.data.entity.Homie
import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.ui.profile.TypeTest

interface RemoteProfileDataSource {
    suspend fun getUserProfile(): WrapperClass<Homie>
    suspend fun putTestResult(typeScore: List<Int>): WrapperClass<Any>
    suspend fun getTypeTestList(): WrapperClass<List<TypeTest>>
}
