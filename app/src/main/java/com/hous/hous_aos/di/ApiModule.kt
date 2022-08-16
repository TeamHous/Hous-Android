package com.hous.hous_aos.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideHomeService(retrofit: Retrofit): com.hous.data.api.HomeApi =
        retrofit.create(com.hous.data.api.HomeApi::class.java)

    @Provides
    @Singleton
    fun provideRulesService(retrofit: Retrofit): com.hous.data.api.RulesApi =
        retrofit.create(com.hous.data.api.RulesApi::class.java)

    @Provides
    @Singleton
    fun provideProfileService(retrofit: Retrofit): com.hous.data.api.ProfileApi =
        retrofit.create(com.hous.data.api.ProfileApi::class.java)

    @Provides
    @Singleton
    fun provideNewRulesApi(retrofit: Retrofit): com.hous.data.api.NewRulesApi =
        retrofit.create(com.hous.data.api.NewRulesApi::class.java)
}
