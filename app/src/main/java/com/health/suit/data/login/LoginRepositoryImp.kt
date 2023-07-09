package com.health.suit.data.login

import com.health.suit.data.common.utils.DataState
import com.health.suit.data.common.utils.WrappedResponse
import com.health.suit.data.login.remote.LoginApi
import com.health.suit.domain.login.LoginRepository
import com.health.suit.domain.login.entity.LoginApiResponse
import com.health.suit.domain.login.entity.LoginEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(private val loginApi: LoginApi) : LoginRepository {
    override suspend fun loginResponse(entity: LoginEntity): Flow<DataState<LoginApiResponse>> = flow {
        emit(DataState.Success(loginApi.getLoginApiResponse("auth/login", entity)))
    }


}