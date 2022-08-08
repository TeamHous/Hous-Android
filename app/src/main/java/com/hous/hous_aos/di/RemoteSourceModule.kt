package com.hous.hous_aos.di

import com.hous.data.source.remote.RemoteHomeDataSource
import com.hous.data.source.remote.RemoteHomeDataSourceImpl
import com.hous.data.source.remote.RemoteNewRulesDataSource
import com.hous.data.source.remote.RemoteNewRulesDataSourceImpl
import com.hous.data.source.remote.RemoteProfileDataSource
import com.hous.data.source.remote.RemoteProfileDataSourceImpl
import com.hous.data.source.remote.RemoteRulesTodayDataSource
import com.hous.data.source.remote.RemoteRulesTodayDataSourceImpl
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
        impl: RemoteRulesTodayDataSourceImpl
    ): RemoteRulesTodayDataSource = impl

    @Provides
    @Singleton
    fun providesRemoteProfileSource(
        impl: RemoteProfileDataSourceImpl
    ): RemoteProfileDataSource = impl

    @Provides
    @Singleton
    fun providesRemoteNewRulesSource(
        impl: RemoteNewRulesDataSourceImpl
    ): RemoteNewRulesDataSource = impl
}
