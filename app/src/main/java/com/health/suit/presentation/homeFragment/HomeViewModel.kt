package com.health.suit.presentation.homeFragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.health.suit.data.common.utils.DataState
import com.health.suit.domain.home.usesase.DetailsApiUseCase
import com.health.suit.domain.home.usesase.HomeApiUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val homeApiUseCase: HomeApiUseCase, private val detailsApiUseCase: DetailsApiUseCase) : ViewModel() {

    private val state = MutableStateFlow<HomeStateModel>(HomeStateModel.Init)
    val mState: StateFlow<HomeStateModel> get() = state
    private val TAG = "HomeViewModel"
    private fun setLoading() {

        state.value = HomeStateModel.IsLoading(true)
    }

    private fun hideLoading() {

        state.value = HomeStateModel.IsLoading(false)
    }

    fun getHomeItems() = viewModelScope.launch {
        homeApiUseCase.execute(HomeApiUseCase.Params(""))
            .onStart {
                Log.d(TAG, " Called on start")
                setLoading()
            }
            .collect {
                hideLoading()
                Log.d(TAG, " Called collect")
                when (it) {
                    is DataState.GenericError -> {
                        Log.d(TAG, " Called Generic error")
                    }

                    is DataState.Success -> {
                        Log.d(TAG, "Enter SUCCESS")
                        state.value =  HomeStateModel.HomeResponse(it.value)


                    }
                }
            }
    }

    fun getProductDetails(number : Int) = viewModelScope.launch {
        detailsApiUseCase.execute(DetailsApiUseCase.Params(number))
            .onStart {
                Log.d(TAG, " Called on start")
                setLoading()
            }
            .collect {
                hideLoading()
                Log.d(TAG, " Called collect")
                when (it) {
                    is DataState.GenericError -> {
                        Log.d(TAG, " Called Generic error")
                    }

                    is DataState.Success -> {
                        Log.d(TAG, "Enter SUCCESS")
                        state.value =  HomeStateModel.DetailResponse(it.value)


                    }
                }
            }
    }
}