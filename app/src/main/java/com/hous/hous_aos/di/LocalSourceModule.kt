package com.hous.hous_aos.di

import com.hous.hous_aos.data.source.local.LocalDataSource
import com.hous.hous_aos.data.source.local.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalSourceModule {
    @Singleton
    @Provides
    fun providesLocalDataSource(impl: LocalDataSourceImpl): LocalDataSource = impl
}
