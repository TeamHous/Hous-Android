package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.data.api.HomeApi
import javax.inject.Inject

class RemoteHomeDataSourceImpl @Inject constructor(
    homeApi: HomeApi
) : RemoteHomeDataSource
