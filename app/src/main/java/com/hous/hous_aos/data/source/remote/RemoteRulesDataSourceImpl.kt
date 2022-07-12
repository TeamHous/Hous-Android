package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.data.api.RulesService
import javax.inject.Inject

class RemoteRulesDataSourceImpl @Inject constructor(
    rulesService: RulesService
) : RemoteRulesDataSource
