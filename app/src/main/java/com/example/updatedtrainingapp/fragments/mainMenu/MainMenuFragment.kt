package com.example.updatedtrainingapp.fragments.mainMenu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.databinding.FragmentMainMenuBinding
import com.example.updatedtrainingapp.fragments.BaseFragment
import com.example.updatedtrainingapp.utils.Utils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainMenuFragment : BaseFragment(R.layout.fragment_main_menu) {

    private val viewModel: MainMenuViewModel by activityViewModels()
    private var binding: FragmentMainMenuBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        binding?.viewModel = viewModel
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.graphChoiceViewPager?.adapter = ViewPagerAdapter(
            requireActivity().supportFragmentManager,
            viewModel.getTrainingList()
        )
    }

    override fun onStart() {
        super.onStart()
        binding?.startTrainingBtn?.setOnClickListener {
            findNavController().navigate(
                MainMenuFragmentDirections.actionFragmentMainMenuToFragmentThisTrainingFragment()
            )
        }
        binding?.historyBtn?.setOnClickListener {
            findNavController().navigate(
                MainMenuFragmentDirections.actionFragmentMainMenuToFragmentCalendar()
            )
        }
        onBackClick {
            Utils.getAlertDialog(requireActivity(), "Leave the app", "Are you sure you want to quit the app?"
            ) {
                val a = Intent(Intent.ACTION_MAIN)
                a.addCategory(Intent.CATEGORY_HOME)
                a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(a)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
