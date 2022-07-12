package com.hous.hous_aos.di

import com.hous.hous_aos.data.source.remote.RemoteHomeDataSource
import com.hous.hous_aos.data.source.remote.RemoteHomeDataSourceImpl
import com.hous.hous_aos.data.source.remote.RemoteProfileDataSource
import com.hous.hous_aos.data.source.remote.RemoteProfileDataSourceImpl
import com.hous.hous_aos.data.source.remote.RemoteRulesDataSource
import com.hous.hous_aos.data.source.remote.RemoteRulesDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteSourceModule {
    @Provides
    @Singleton
    fun providesRemoteHomeSource(
        impl: RemoteHomeDataSourceImpl
    ): RemoteHomeDataSource = impl

    @Provides
    @Singleton
    fun providesRemoteRulesSource(
        impl: RemoteRulesDataSourceImpl
    ): RemoteRulesDataSource = impl

    @Provides
    @Singleton
    fun providesRemoteProfileSource(
        impl: RemoteProfileDataSourceImpl
    ): RemoteProfileDataSource = impl
}
