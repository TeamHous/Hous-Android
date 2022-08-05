package com.hous.hous_aos.di

import com.hous.hous_aos.data.source.remote.*
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
        impl: com.hous.data.source.remote.RemoteHomeDataSourceImpl
    ): com.hous.data.source.remote.RemoteHomeDataSource = impl

    @Provides
    @Singleton
    fun providesRemoteRulesSource(
        impl: com.hous.data.source.remote.RemoteRulesTodayDataSourceImpl
    ): com.hous.data.source.remote.RemoteRulesTodayDataSource = impl

    @Provides
    @Singleton
    fun providesRemoteProfileSource(
        impl: com.hous.data.source.remote.RemoteProfileDataSourceImpl
    ): com.hous.data.source.remote.RemoteProfileDataSource = impl

    @Provides
    @Singleton
    fun providesRemoteNewRulesSource(
        impl: com.hous.data.source.remote.RemoteNewRulesDataSourceImpl
    ): com.hous.data.source.remote.RemoteNewRulesDataSource = impl
}
