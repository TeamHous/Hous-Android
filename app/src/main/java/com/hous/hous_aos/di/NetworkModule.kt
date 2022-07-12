package com.hous.hous_aos.di

import com.hous.hous_aos.BuildConfig
import com.hous.hous_aos.data.api.HomeApi
import com.hous.hous_aos.data.api.ProfileApi
import com.hous.hous_aos.data.api.RulesApi
import com.hous.hous_aos.data.source.local.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val HOUS_URL = BuildConfig.HOUS_URL

    @Provides
    @Singleton
    fun providesHousInterceptor(
        localDataSource: LocalDataSource
    ): Interceptor =
        Interceptor { chain ->
            with(chain) {
                proceed(
                    request()
                        .newBuilder()
                        .addHeader(
                            "Authorization",
                            "Bearer 나중에 기입"
                        )
                        .build()
                )
            }
        }

    @Provides
    @Singleton
    fun providesHousOkHttpClient(
        interceptor: Interceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

    @Provides
    @Singleton
    fun providesHousRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(HOUS_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideHomeService(retrofit: Retrofit): HomeApi =
        retrofit.create(HomeApi::class.java)

    @Provides
    fun provideRulesService(retrofit: Retrofit): RulesApi =
        retrofit.create(RulesApi::class.java)

    @Provides
    fun provideProfileService(retrofit: Retrofit): ProfileApi =
        retrofit.create(ProfileApi::class.java)
}
