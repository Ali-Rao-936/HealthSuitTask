package com.health.suit.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.health.suit.data.common.utils.DataState
import com.health.suit.domain.login.entity.LoginEntity
import com.health.suit.domain.login.usecase.LoginApiUsecase
import com.health.suit.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginApiUsecase: LoginApiUsecase) : ViewModel(){

    private val state = MutableStateFlow<LoginStateModel>(LoginStateModel.Init)
    val mState: StateFlow<LoginStateModel> get() =state
    private val TAG = "LoginViewModel"
    private fun setLoading(){

        state.value = LoginStateModel.IsLoading(true)
    }
    private fun hideLoading(){
        Log.d(TAG, " Called on start")
        state.value = LoginStateModel.IsLoading(false)
    }

    fun getLoginApiResponse(entity: LoginEntity) = viewModelScope.launch {
        loginApiUsecase.execute(LoginApiUsecase.Params(entity))
            .onStart {
                setLoading()
            }
            .collect {
                hideLoading()
                Log.d(TAG, " Called collect")
                when(it){
                    is DataState.GenericError-> {
                        Log.d(TAG,"Enter generic error")
                        val message = it.error?.errorResponse?.errorMessage?: Constants.UNKNOWN_ERROR
                        Log.d(TAG,"Message: $message")
                        state.value = LoginStateModel.GenericError(message)

                    }

                    is DataState.Success -> {
                        Log.d(TAG,"Enter SUCCESS")
                        state.value =   LoginStateModel.LoginResponse(it.value)


                    }
                }
            }
    }

}
