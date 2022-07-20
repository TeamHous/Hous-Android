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
