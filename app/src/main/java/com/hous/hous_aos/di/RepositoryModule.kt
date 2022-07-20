package com.hous.hous_aos.di

import com.hous.hous_aos.data.repository.NewRulesRepository
import com.hous.hous_aos.data.repository.NewRulesRepositoryImpl
import com.hous.hous_aos.data.repository.RulesTodayRepository
import com.hous.hous_aos.data.repository.RulesTodayRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesNewRulesRepository(
        impl: NewRulesRepositoryImpl
    ): NewRulesRepository = impl

    @Provides
    @Singleton
    fun providesRulesTodayRepository(
        impl: RulesTodayRepositoryImpl
    ): RulesTodayRepository = impl
}
