package com.example.updatedtrainingapp.fragments.exerciseChoice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.databinding.FragmentChooseExerciseBinding
import com.example.updatedtrainingapp.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_choose_exercise.*


@AndroidEntryPoint
class ExerciseChoiceFragment : BaseFragment(R.layout.fragment_choose_exercise) {

    private var _binding: FragmentChooseExerciseBinding? = null
    private val binding get() = _binding

    private val viewModel: ExerciseChoiceViewModel by activityViewModels()

    override fun onStart() {
        super.onStart()
        setAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseExerciseBinding.inflate(inflater, container, false)
        return binding?.root
    }

    private val imageArray = arrayOf(
        R.mipmap.abs,
        R.mipmap.back,
        R.mipmap.biceps,
        R.mipmap.chest,
        R.mipmap.legs,
        R.mipmap.shoulders,
        R.mipmap.triceps,
        R.mipmap.traps
    )

    private fun setAdapter() {
        viewModel.getAllExercises()?.observe(viewLifecycleOwner, { exerciseList ->
            binding?.exerciseChoiceViewPager?.adapter =
                ViewPagerAdapter(
                    requireActivity().supportFragmentManager,
                    viewModel.getList(exerciseList)
                )
            binding?.exerciseChoiceTabs?.setupWithViewPager(exerciseChoiceViewPager)
            for (i in 0 until exerciseChoiceTabs.tabCount) {
                binding?.exerciseChoiceTabs?.getTabAt(i)?.setIcon(imageArray[i])
            }
        })
    }

    override fun onStop() {
        super.onStop()
        viewModel.addExercise()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
