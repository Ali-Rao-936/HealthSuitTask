package com.health.suit.presentation.homeFragment

import com.health.suit.domain.home.response.DetailsResponse
import com.health.suit.domain.home.response.HomeApiRes
import java.lang.Exception

sealed class HomeStateModel{

    object Init : HomeStateModel()
    data class IsLoading(val isLoading : Boolean) : HomeStateModel()
    data class HomeResponse(val homeApiResponse: HomeApiRes) : HomeStateModel()
    data class DetailResponse(val detailApiResponse: DetailsResponse) : HomeStateModel()
    data class FoundException(val exception: Exception) : HomeStateModel()
    data class StatusFailed(val message: String) : HomeStateModel()
}
