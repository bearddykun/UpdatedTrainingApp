package com.example.updatedtrainingapp.fragments.mainMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.application.ThisApplication
import kotlinx.android.synthetic.main.fragment_main_menu.*
import javax.inject.Inject

class MainMenuFragment : Fragment() {

    @Inject
    lateinit var viewModel: MainMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity?.application as ThisApplication).appComponent.inject(this)

        graphView.addSeries(viewModel.getSeries())
    }

    override fun onStart() {
        super.onStart()
        trainingContainer.setOnClickListener { findNavController().navigate(MainMenuFragmentDirections.actionFragmentMainMenuToFragmentThisTrainingFragment()) }
    }
}
