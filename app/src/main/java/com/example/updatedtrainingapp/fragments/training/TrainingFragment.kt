package com.example.updatedtrainingapp.fragments.training

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.dataBase.Constants
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject
import com.example.updatedtrainingapp.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.training_fragment.*

@AndroidEntryPoint
class TrainingFragment : BaseFragment(R.layout.training_fragment),
    TrainingAdapter.OnTrainingItemClickListener {

    private val viewModel: TrainingViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        viewModel.getExercisesWithTraining()
            ?.let {
                it.observe(viewLifecycleOwner, { trainings ->
                    saveTrainingExercises(trainings)
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

    private fun saveTrainingExercises(trainings: List<TrainingObject>) {
        val list = mutableSetOf<String>()
        trainings.forEach {
            list.add(it.exerciseName)
        }
        MySharedPreferences.saveList(Constants.EXERCISE_LIST, list)
    }
}
