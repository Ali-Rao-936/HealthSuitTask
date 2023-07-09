package com.health.suit.domain.test

import com.health.suit.data.common.utils.DataState
import com.health.suit.data.common.utils.WrappedResponse
import com.health.suit.domain.test.entity.TestApiResponse
import kotlinx.coroutines.flow.Flow

interface TestRepository {
    suspend fun testResponse(): Flow<DataState<WrappedResponse<TestApiResponse>>>
    suspend fun xyz():String
}