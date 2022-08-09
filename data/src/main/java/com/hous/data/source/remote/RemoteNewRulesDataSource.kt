package com.hous.data.source.remote

import com.hous.data.model.WrapperClass
import com.hous.data.model.request.NewRulesRequest
import com.hous.data.model.response.NewRulesListResponse

interface RemoteNewRulesDataSource {
    suspend fun addNewRule(newRulesRequest: NewRulesRequest)
    suspend fun getNewRuleList(roomId: String): WrapperClass<NewRulesListResponse>
}
