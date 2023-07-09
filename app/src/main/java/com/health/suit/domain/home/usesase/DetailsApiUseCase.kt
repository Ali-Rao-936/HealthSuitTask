package com.health.suit.domain.home.usesase

import com.health.suit.data.common.utils.DataState
import com.health.suit.data.common.utils.NetworkHelper
import com.health.suit.domain.home.HomeRepository
import com.health.suit.domain.home.response.DetailsResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailsApiUseCase @Inject constructor(private val repo: HomeRepository) : NetworkHelper<DetailsApiUseCase.Params, DetailsResponse>() {

    data class Params(val number: Int)

    override suspend fun buildUseCase(parameter: Params): Flow<DataState<DetailsResponse>> {
        return repo.detailsResponse(parameter.number)
    }

}