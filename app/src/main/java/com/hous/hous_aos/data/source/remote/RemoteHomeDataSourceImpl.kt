package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.data.api.HomeService
import javax.inject.Inject

class RemoteHomeDataSourceImpl @Inject constructor(
    homeService: HomeService
) : RemoteHomeDataSource
