package com.hous.hous_aos.data.source.remote

import com.hous.hous_aos.BuildConfig
import com.hous.hous_aos.data.api.ProfileApi
import javax.inject.Inject

class RemoteProfileDataSourceImpl @Inject constructor(
    profileApi: ProfileApi
) : RemoteProfileDataSource {
    private val ROOM_ID = BuildConfig.ROOM_ID
}
