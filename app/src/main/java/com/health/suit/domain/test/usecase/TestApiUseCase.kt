package com.health.suit.domain.test.usecase

import com.health.suit.data.common.utils.DataState
import com.health.suit.data.common.utils.NetworkHelper
import com.health.suit.data.common.utils.WrappedResponse
import com.health.suit.domain.test.TestRepository
import com.health.suit.domain.test.entity.TestApiResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TestApiUseCase @Inject constructor(private val testRepository: TestRepository): NetworkHelper<TestApiUseCase.Params, WrappedResponse<TestApiResponse>>() {
    class Params

    override suspend fun buildUseCase(parameter: Params): Flow<DataState<WrappedResponse<TestApiResponse>>> {
        return testRepository.testResponse()
    }
}