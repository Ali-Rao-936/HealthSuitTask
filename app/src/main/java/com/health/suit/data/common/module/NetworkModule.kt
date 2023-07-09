package com.health.suit.data.common.module

import com.health.suit.data.common.utils.NoInternetConnectionInterceptor
import com.health.suit.data.common.utils.RequestInterceptor
import com.health.suit.BuildConfig
import com.health.suit.data.test.remote.TestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent :: class)
object NetworkModule {

    @Provides
    fun provideTestApi(retrofit:Retrofit): TestApi {
        return retrofit.create(TestApi::class.java)
    }
    @Provides
    fun provideOkHttpClient (noInternetConnectionInterceptor: NoInternetConnectionInterceptor): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            addInterceptor(RequestInterceptor())
            addInterceptor(httpLoggingInterceptor)
            addInterceptor(noInternetConnectionInterceptor)
        }.build()
    }

    @Provides
    fun provideRetrofit(okHttp: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create())
            client(okHttp)
            baseUrl(BuildConfig.BaseURL)
        }.build()
    }

}

