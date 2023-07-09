package com.health.suit.data.login.remote

import com.health.suit.data.common.utils.WrappedResponse
import com.health.suit.domain.login.entity.LoginApiResponse
import com.health.suit.domain.login.entity.LoginEntity
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface LoginApi {

    @POST
    suspend fun getLoginApiResponse(@Url url:String, @Body entity: LoginEntity) : LoginApiResponse
}