package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.data.api.ProfileService
import javax.inject.Inject

class RemoteProfileDataSourceImpl @Inject constructor(
    profileService: ProfileService
) : RemoteProfileDataSource
