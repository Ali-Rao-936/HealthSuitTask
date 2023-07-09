package com.health.suit.data.home.module

import com.health.suit.domain.home.HomeRepository
import com.health.suit.data.home.HomeRepoImp
import com.health.suit.data.home.remote.HomeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent :: class)
object HomeApiModule{

    @Provides
    fun providesHomeRepository(homeApi: HomeApi): HomeRepository {
        return HomeRepoImp(homeApi)
    }

    @Provides
    fun provideHomeApi(retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }

}