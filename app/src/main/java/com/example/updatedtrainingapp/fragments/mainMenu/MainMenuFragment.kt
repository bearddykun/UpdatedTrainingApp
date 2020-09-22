package com.example.updatedtrainingapp.fragments.mainMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.databinding.FragmentMainMenuBinding
import com.example.updatedtrainingapp.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuFragment : BaseFragment(R.layout.fragment_main_menu) {

    private val viewModel: MainMenuViewModel by viewModels()
    private var binding: FragmentMainMenuBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.graphView?.addSeries(viewModel.getSeries())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        binding?.trainingContainer?.setOnClickListener {
            findNavController().navigate(
                MainMenuFragmentDirections.actionFragmentMainMenuToFragmentThisTrainingFragment()
            )
        }
        binding?.storyButton?.setOnClickListener {
            findNavController().navigate(
            MainMenuFragmentDirections.actionFragmentMainMenuToFragmentCalendar()
            )
        }
    }
}
