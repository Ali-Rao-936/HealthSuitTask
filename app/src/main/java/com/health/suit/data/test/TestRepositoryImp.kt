package com.health.suit.data.test

import com.health.suit.data.common.utils.DataState
import com.health.suit.data.common.utils.WrappedResponse
import com.health.suit.data.test.remote.TestApi
import com.health.suit.domain.test.entity.TestApiResponse
import com.health.suit.domain.test.TestRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TestRepositoryImp @Inject constructor(private val testApi: TestApi): TestRepository {
    override suspend fun testResponse(): Flow<DataState<WrappedResponse<TestApiResponse>>> = flow {
        emit(DataState.Success(testApi.getTestApiResponse("entries")))
    }

    override suspend fun xyz(): String {
        return "eetete"
    }
}