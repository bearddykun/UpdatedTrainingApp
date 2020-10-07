package com.example.updatedtrainingapp.fragments.splash


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment(R.layout.fragment_splash) {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        splashViewModel.databaseLoading()
        startApp()
    }

    private fun startApp() {
        Handler(Looper.getMainLooper()).postDelayed(
            { findNavController().navigate(SplashFragmentDirections.actionFragmentSplashToFragmentMainMenu()) },
            3000
        )
    }
}
