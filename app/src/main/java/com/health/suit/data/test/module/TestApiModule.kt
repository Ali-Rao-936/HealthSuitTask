package com.health.suit.data.test.module

import com.health.suit.data.test.TestRepositoryImp
import com.health.suit.data.test.remote.TestApi
import com.health.suit.domain.test.TestRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent :: class)
object TestApiModule{

    @Provides
    fun providesTestRepository(testApi: TestApi): TestRepository {
        return TestRepositoryImp(testApi)
    }

}


