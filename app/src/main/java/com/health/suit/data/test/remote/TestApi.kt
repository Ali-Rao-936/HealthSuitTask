package com.health.suit.data.test.remote

import com.health.suit.data.common.utils.WrappedResponse
import com.health.suit.domain.test.entity.TestApiResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface TestApi {
    @GET
    suspend fun getTestApiResponse(@Url url:String): WrappedResponse<TestApiResponse>
}