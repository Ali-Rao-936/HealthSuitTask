package com.health.suit.domain.login

import com.health.suit.data.common.utils.DataState
import com.health.suit.data.common.utils.WrappedResponse
import com.health.suit.domain.login.entity.LoginApiResponse
import com.health.suit.domain.login.entity.LoginEntity
import kotlinx.coroutines.flow.Flow


interface LoginRepository {
    suspend fun loginResponse(entity: LoginEntity) : Flow<DataState<LoginApiResponse>>
}