package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.request.NewRulesRequest

interface RemoteNewRulesDataSource {
    suspend fun addNewRule(newRulesRequest: NewRulesRequest): WrapperClass<Any>
}
