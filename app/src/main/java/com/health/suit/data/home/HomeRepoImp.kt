package com.health.suit.data.home


import com.health.suit.data.common.utils.DataState
import com.health.suit.data.home.remote.HomeApi
import com.health.suit.domain.home.HomeRepository
import com.health.suit.domain.home.response.DetailsResponse
import com.health.suit.domain.home.response.HomeApiRes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepoImp @Inject constructor(private val homeApi: HomeApi) : HomeRepository {
    override suspend fun homeResponse(): Flow<DataState<HomeApiRes>> = flow {
        emit(DataState.Success(homeApi.getHomeItemsResponse("products")))
    }

    override suspend fun detailsResponse(number : Int):  Flow<DataState<DetailsResponse>> = flow {
        emit(DataState.Success(homeApi.getDetailsResponse("products/$number")))
    }
}