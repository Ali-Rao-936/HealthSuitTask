package com.health.suit.presentation.details

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.health.suit.databinding.ActivityProductDetailsBinding
import com.health.suit.domain.home.response.DetailsResponse
import com.health.suit.presentation.homeFragment.HomeStateModel
import com.health.suit.presentation.homeFragment.HomeViewModel
import com.health.suit.utils.loadImageFromPath
import com.health.suit.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel : HomeViewModel

    private val TAG = "ProductDetailsActivity"

    private lateinit var binding: ActivityProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObserver()
        val id = intent.extras?.getInt("itemId")
        id?.let { viewModel.getProductDetails(it) }

        binding.imgBack.setOnClickListener { onBackPressed() }

    }
    private fun initObserver() {
        viewModel.mState.flowWithLifecycle(
            this.lifecycle, Lifecycle.State.STARTED
        ).onEach {
            handleState(it)
        }.launchIn(this.lifecycleScope)
    }



    private fun handleState(state: HomeStateModel) {
        when (state) {
            is HomeStateModel.IsLoading -> handleIsLoadingState(state.isLoading)
            is HomeStateModel.DetailResponse -> handleApiResponse(state.detailApiResponse)
            is HomeStateModel.FoundException -> handleException(state.exception)
            is HomeStateModel.StatusFailed -> handleFailure(state.message)
            else -> {
            }
        }
    }

    private fun handleFailure(message: String) {
        showToast(message)
    }

    private fun handleException(exception: Exception) {
        Log.d(TAG, exception.message.toString())

    }

    private fun handleApiResponse(response: DetailsResponse) {
        Log.d(TAG, response.id.toString())
        binding.imgProduct.loadImageFromPath(response.image)
    }

    private fun handleIsLoadingState(loading: Boolean) {
        if (loading)
            Log.d(TAG, "show loader....")
        else
            Log.d(TAG, "..... stop loader")
    }


}