package com.health.suit.data.home.remote

import com.health.suit.domain.home.response.DetailsResponse
import com.health.suit.domain.home.response.HomeApiRes
import retrofit2.http.GET
import retrofit2.http.Url

interface HomeApi {

    @GET
    suspend fun getHomeItemsResponse(@Url url:String) : HomeApiRes

    @GET
    suspend fun getDetailsResponse(@Url url:String) : DetailsResponse
}