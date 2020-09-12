package com.example.updatedtrainingapp.fragments.mainMenu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main_menu.*

@AndroidEntryPoint
class MainMenuFragment : BaseFragment(R.layout.fragment_main_menu) {

    private val viewModel: MainMenuViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        graphView.addSeries(viewModel.getSeries())
    }

    override fun onStart() {
        super.onStart()
        trainingContainer.setOnClickListener {
            findNavController().navigate(
                MainMenuFragmentDirections.actionFragmentMainMenuToFragmentThisTrainingFragment()
            )
        }
    }
}
