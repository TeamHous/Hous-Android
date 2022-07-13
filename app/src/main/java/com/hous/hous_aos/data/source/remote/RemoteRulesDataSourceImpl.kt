package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.data.api.RulesApi
import javax.inject.Inject

class RemoteRulesDataSourceImpl @Inject constructor(
    rulesApi: RulesApi
) : RemoteRulesDataSource
