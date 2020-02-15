package com.example.updatedtrainingapp.fragments.splash


import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.postAtTime
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.application.ThisApplication
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

/**
 * A simple [Fragment] subclass.
 */
class SplashFragment : Fragment() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as ThisApplication).appComponent.inject(this)
        splashViewModel.databaseLoading()
        startApp()
    }


    private fun startApp() {
        Handler().postDelayed(
            { findNavController().navigate(SplashFragmentDirections.actionFragmentSplashToFragmentMainMenu()) },
            3000
        )
    }
}
