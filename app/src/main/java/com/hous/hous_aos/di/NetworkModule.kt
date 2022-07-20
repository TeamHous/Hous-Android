package com.hous.hous_aos.di

import com.hous.hous_aos.BuildConfig
import com.hous.hous_aos.data.api.HomeApi
import com.hous.hous_aos.data.api.NewRulesApi
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
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val HOUS_URL = BuildConfig.HOUS_URL
    private const val DUMMY_ACCESS_TOKEN = BuildConfig.DUMMY_ACCESS_TOKEN

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
                            DUMMY_ACCESS_TOKEN
                        )
                        .addHeader("Content-Type", "Application/json")
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
            .addInterceptor(
                HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BODY }
            )
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
    @Singleton
    fun provideHomeService(retrofit: Retrofit): HomeApi =
        retrofit.create(HomeApi::class.java)

    @Provides
    @Singleton
    fun provideRulesService(retrofit: Retrofit): RulesApi =
        retrofit.create(RulesApi::class.java)

    @Provides
    @Singleton
    fun provideProfileService(retrofit: Retrofit): ProfileApi =
        retrofit.create(ProfileApi::class.java)

    @Provides
    @Singleton
    fun provideNewRulesApi(retrofit: Retrofit): NewRulesApi =
        retrofit.create(NewRulesApi::class.java)
}
