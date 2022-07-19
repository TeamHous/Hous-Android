package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.BuildConfig
import com.hous.hous_aos.data.api.NewRulesApi
import javax.inject.Inject

class RemoteNewRulesDataSourceImpl @Inject constructor(
    private val newRulesApi: NewRulesApi
) : RemoteNewRulesDataSource {
    private val ROOM_ID = BuildConfig.ROOM_ID
}
