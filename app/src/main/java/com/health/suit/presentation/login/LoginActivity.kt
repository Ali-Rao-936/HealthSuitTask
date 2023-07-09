package com.health.suit.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.health.suit.MainActivity
import com.health.suit.databinding.ActivityLoginBinding
import com.health.suit.domain.login.entity.LoginApiResponse
import com.health.suit.domain.login.entity.LoginEntity
import com.health.suit.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: LoginViewModel

    private val TAG = "PhoneNumberActivity"
    private lateinit var binding : ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObserver()

        binding.btnContinue.setOnClickListener {
            if (binding.etEmail.text.isEmpty()){
                showToast("Please Enter Email")
            }
            if (binding.etPassword.text.isEmpty()){
                showToast("Please Enter Password")
            }else {
                viewModel.getLoginApiResponse(LoginEntity(binding.etEmail.text.toString(), binding.etPassword.text.toString()))
            }
        }
    }

    private fun initObserver() {
        viewModel.mState.flowWithLifecycle(
            this.lifecycle, Lifecycle.State.STARTED
        ).onEach {
            handleState(it)
        }.launchIn(this.lifecycleScope)
    }



    private fun handleState(state: LoginStateModel) {
        when (state) {
            is LoginStateModel.IsLoading -> handleIsLoadingState(state.isLoading)
            is LoginStateModel.LoginResponse -> handleApiResponse(state.loginApiResponse)
            is LoginStateModel.GenericError -> handleException(state.message)
            is LoginStateModel.StatusFailed -> handleFailure(state.message)
            else -> {
            }
        }
    }

    private fun handleFailure(message: String) {
        if (message == "User not found"){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }else
            showToast(message)
    }

    private fun handleException(message: String) {
        Log.d(TAG, message)
        showToast("username or password is incorrect")
    }

    private fun handleApiResponse(loginApiResponse: LoginApiResponse) {
        Log.d(TAG, loginApiResponse.msg)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }

    private fun handleIsLoadingState(loading: Boolean) {
        if (loading)
            Log.d(TAG, "show loader....")
        else
            Log.d(TAG, "..... stop loader")
    }
}
