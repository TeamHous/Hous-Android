package com.hous.data.source.remote

import com.hous.data.BuildConfig
import com.hous.data.api.NewRulesApi
import com.hous.data.model.WrapperClass
import com.hous.data.model.request.NewRulesRequest
import com.hous.data.model.response.NewRulesListResponse
import javax.inject.Inject

class RemoteNewRulesDataSourceImpl @Inject constructor(
    private val newRulesApi: NewRulesApi
) : RemoteNewRulesDataSource {
    private val ROOM_ID = BuildConfig.ROOM_ID

    override suspend fun addNewRule(newRulesRequest: NewRulesRequest) {
        runCatching { newRulesApi.addNewRule(ROOM_ID, newRulesRequest) }
    }

    override suspend fun getNewRuleList(roomId: String): WrapperClass<NewRulesListResponse> =
        newRulesApi.getNewRuleList(ROOM_ID)
}
