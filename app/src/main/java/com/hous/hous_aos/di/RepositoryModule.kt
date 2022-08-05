package com.hous.hous_aos.di

import com.hous.data.repository.HomeRepository
import com.hous.data.repository.HomeRepositoryImpl
import com.hous.hous_aos.data.repository.NewRulesRepository
import com.hous.hous_aos.data.repository.NewRulesRepositoryImpl
import com.hous.data.repository.ProfileRepository
import com.hous.data.repository.ProfileRepositoryImpl
import com.hous.data.repository.RulesTodayRepository
import com.hous.data.repository.RulesTodayRepositoryImpl
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
        impl: com.hous.data.repository.RulesTodayRepositoryImpl
    ): com.hous.data.repository.RulesTodayRepository = impl

    @Provides
    @Singleton
    fun providesProfileRepository(
        impl: com.hous.data.repository.ProfileRepositoryImpl
    ): com.hous.data.repository.ProfileRepository = impl

    @Provides
    @Singleton
    fun providesHomeRepository(
        impl: com.hous.data.repository.HomeRepositoryImpl
    ): com.hous.data.repository.HomeRepository = impl
    
}
