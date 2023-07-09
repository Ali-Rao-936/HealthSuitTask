package com.health.suit.domain.home

import com.health.suit.data.common.utils.DataState
import com.health.suit.data.common.utils.WrappedListResponse
import com.health.suit.data.common.utils.WrappedResponse
import com.health.suit.domain.home.response.DetailsResponse
import com.health.suit.domain.home.response.HomeApiRes
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

  suspend fun homeResponse() : Flow<DataState<HomeApiRes>>

  suspend fun detailsResponse(number : Int) : Flow<DataState<DetailsResponse>>
}