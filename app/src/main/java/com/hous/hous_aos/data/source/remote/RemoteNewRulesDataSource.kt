package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.request.NewRulesRequest
import com.hous.hous_aos.data.model.response.NewRulesListResponse

interface RemoteNewRulesDataSource {
    suspend fun addNewRule(newRulesRequest: NewRulesRequest): WrapperClass<Any>
    suspend fun getNewRuleList(roomId: String): WrapperClass<NewRulesListResponse>
}
