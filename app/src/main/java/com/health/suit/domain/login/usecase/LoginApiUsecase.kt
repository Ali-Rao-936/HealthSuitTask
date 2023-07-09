package com.health.suit.domain.login.usecase

import com.health.suit.data.common.utils.DataState
import com.health.suit.data.common.utils.NetworkHelper
import com.health.suit.data.common.utils.WrappedResponse
import com.health.suit.domain.login.LoginRepository
import com.health.suit.domain.login.entity.LoginApiResponse
import com.health.suit.domain.login.entity.LoginEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginApiUsecase @Inject constructor(private val loginRepository: LoginRepository) :
    NetworkHelper<LoginApiUsecase.Params, LoginApiResponse>() {
    class Params(val entity: LoginEntity)

    override suspend fun buildUseCase(parameter: Params): Flow<DataState<LoginApiResponse>> {
        return loginRepository.loginResponse(parameter.entity)
    }
}
