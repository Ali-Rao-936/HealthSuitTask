package com.health.suit.presentation.test

import com.health.suit.domain.test.entity.TestApiResponse
import java.lang.Exception

sealed class TestFragmentStateModel{
    object Init : TestFragmentStateModel()
    data class IsLoading(val isLoading:Boolean): TestFragmentStateModel()
    data class TestApiResponsee(val testApiResponse: TestApiResponse): TestFragmentStateModel()
    data class FoundException(val exception:Exception): TestFragmentStateModel()
}
