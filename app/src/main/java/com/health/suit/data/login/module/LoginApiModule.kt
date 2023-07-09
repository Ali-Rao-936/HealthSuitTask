package com.health.suit.data.login.module

import com.health.suit.data.login.LoginRepositoryImp
import com.health.suit.data.login.remote.LoginApi
import com.health.suit.domain.login.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent :: class)
object LoginApiModule {

    @Provides
    fun providesLoginRepository(loginApi: LoginApi) : LoginRepository {
        return LoginRepositoryImp(loginApi)
    }

    @Provides
    fun provideLoginApi(retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }
}