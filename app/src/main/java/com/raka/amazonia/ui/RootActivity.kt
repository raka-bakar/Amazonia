package com.raka.amazonia.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.raka.amazonia.R
import com.raka.amazonia.databinding.ActivityRootBinding
import com.raka.amazonia.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RootActivity : FragmentActivity() {
    private val viewModel: RootViewModel by viewModels()
    private lateinit var binding: ActivityRootBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openHomeScreen()
    }
    private fun openHomeScreen() {
        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                // Only open next screen when fetching data is finished
                if (!isLoading) {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frameContainerView, HomeFragment.newInstance())
                        commit()
                    }
                }
            }
        }
    }
}