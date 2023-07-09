package com.health.suit.presentation.homeFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.health.suit.R
import com.health.suit.databinding.FragmentHomeBinding
import com.health.suit.domain.home.response.HomeApiRes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
     lateinit var viewModel : HomeViewModel

    lateinit var binding: FragmentHomeBinding

    private lateinit var rvHome : RecyclerView

    private val TAG = "HomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

       return inflater.inflate(R.layout.fragment_home, container, false)

     //   binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)

       // binding.rvHome.layoutManager = LinearLayoutManager(activity)

     //   return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvHome = view.findViewById(R.id.rvHome)
        rvHome.layoutManager = LinearLayoutManager(activity)

        initObserver()
        viewModel.getHomeItems()
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
            is HomeStateModel.HomeResponse -> handleApiResponse(state.homeApiResponse)
            is HomeStateModel.FoundException -> handleException(state.exception)
            is HomeStateModel.StatusFailed -> handleFailure(state.message)
            else -> {
            }
        }
    }

    private fun handleFailure(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    private fun handleException(exception: Exception) {
        Log.d(TAG, exception.message.toString())

    }

    private fun handleApiResponse(response: HomeApiRes) {
        Log.d(TAG, response.size.toString())
        rvHome.adapter = context?.let { HomeItemsAdapter(it, response) }
    }

    private fun handleIsLoadingState(loading: Boolean) {
        if (loading)
            Log.d(TAG, "show loader....")
        else
            Log.d(TAG, "..... stop loader")
    }

}