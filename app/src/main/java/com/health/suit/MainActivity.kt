package com.health.suit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.health.suit.databinding.ActivityMainBinding
import com.health.suit.domain.login.entity.LoginApiResponse
import com.health.suit.presentation.fragments.CartFragment
import com.health.suit.presentation.fragments.FavouriteFragment
import com.health.suit.presentation.homeFragment.HomeFragment
import com.health.suit.presentation.fragments.ProfileFragment
import com.health.suit.presentation.login.LoginStateModel
import com.health.suit.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(HomeFragment())

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.ic_cart -> {
                    loadFragment(CartFragment())
                    true
                }
                R.id.ic_like -> {
                    loadFragment(FavouriteFragment())
                    true
                }
                else -> {loadFragment(ProfileFragment())
                true}
            }
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_view,fragment)
        transaction.commit()
    }


}