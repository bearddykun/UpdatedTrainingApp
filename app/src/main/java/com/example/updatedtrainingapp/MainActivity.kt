package com.example.updatedtrainingapp

import android.os.Bundle
import android.view.View
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.updatedtrainingapp.utils.SoundManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : GeneralActivity() {

    @Inject
    lateinit var soundManager: SoundManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runOnUiThread {
            setupBottomNavigation()
        }
        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, destination, _ ->
            showBottomNavigation(destination)
        }
        soundManager.loadSound()
    }

    private fun setupBottomNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(
            navController, AppBarConfiguration(
                setOf(
                    R.id.fragment_training_day,
                    R.id.fragment_exercise_choice,
                    R.id.fragment_create_new_exercise
                )
            )
        )
        nav_view.setupWithNavController(navController)
    }

    private fun showBottomNavigation(navDestination: NavDestination) {
        when (navDestination.id) {
            R.id.fragment_exercise_choice -> nav_view.visibility = View.VISIBLE
            R.id.fragment_create_new_exercise -> nav_view.visibility = View.VISIBLE
            R.id.fragment_training_day -> nav_view.visibility = View.VISIBLE
            else -> nav_view.visibility = View.GONE
        }
    }
}

