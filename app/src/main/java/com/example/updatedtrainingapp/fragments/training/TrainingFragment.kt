package com.example.updatedtrainingapp.fragments.training

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.training_fragment.*

@AndroidEntryPoint
class TrainingFragment : BaseFragment(R.layout.training_fragment),
    TrainingAdapter.OnTrainingItemClickListener {

    private val trainingDayViewModel: TrainingViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        trainingDayViewModel.getExercisesWithTraining()
            ?.let {
                it.observe(viewLifecycleOwner, { trainings ->
                    val adapter = TrainingAdapter()
                    adapter.swapAdapter(trainings)
                    adapter.setOnTrainingItemClickListener(this)
                    recyclerViewThisTraining.adapter = adapter
                })
            }
    }

    override fun onTrainingItemClick(exerciseName: String) {
        findNavController().navigate(
            TrainingFragmentDirections.actionFragmentTrainingDayToCurrentExerciseFragment(
                exerciseName
            )
        )
    }
}
