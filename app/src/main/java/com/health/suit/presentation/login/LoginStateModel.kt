package com.health.suit.presentation.login


import com.health.suit.domain.login.entity.LoginApiResponse
import java.lang.Exception

sealed class LoginStateModel    {

    object Init : LoginStateModel()
    data class IsLoading(val isLoading : Boolean) : LoginStateModel()
    data class LoginResponse(val loginApiResponse: LoginApiResponse) : LoginStateModel()
    data class FoundException(val exception: Exception) : LoginStateModel()
    data class GenericError(val message: String) : LoginStateModel()
    data class StatusFailed(val message: String) : LoginStateModel()


}
