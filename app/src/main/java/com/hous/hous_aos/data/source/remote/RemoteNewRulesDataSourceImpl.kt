package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.BuildConfig
import com.hous.hous_aos.data.api.NewRulesApi
import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.model.request.NewRulesRequest
import com.hous.hous_aos.data.model.response.NewRulesListResponse
import javax.inject.Inject

class RemoteNewRulesDataSourceImpl @Inject constructor(
    private val newRulesApi: NewRulesApi
) : RemoteNewRulesDataSource {
    private val ROOM_ID = BuildConfig.ROOM_ID

    override suspend fun addNewRule(newRulesRequest: NewRulesRequest): WrapperClass<Any> =
        newRulesApi.addNewRule(ROOM_ID, newRulesRequest)

    override suspend fun getNewRuleList(roomId: String): WrapperClass<NewRulesListResponse> =
        newRulesApi.getNewRuleList(ROOM_ID)
}
