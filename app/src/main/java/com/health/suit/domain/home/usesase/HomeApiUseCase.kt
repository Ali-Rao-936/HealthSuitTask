package com.health.suit.domain.home.usesase


import com.health.suit.domain.home.HomeRepository
import com.health.suit.data.common.utils.DataState
import com.health.suit.data.common.utils.NetworkHelper
import com.health.suit.data.common.utils.WrappedListResponse
import com.health.suit.data.common.utils.WrappedResponse
import com.health.suit.domain.home.response.HomeApiRes
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeApiUseCase @Inject constructor(private val homeRepository: HomeRepository) : NetworkHelper<HomeApiUseCase.Params, HomeApiRes>() {

     data class Params(val entity: String)

    override suspend fun buildUseCase(parameter: Params): Flow<DataState<HomeApiRes>> {
        return homeRepository.homeResponse()
    }
}