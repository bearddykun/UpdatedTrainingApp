package com.example.updatedtrainingapp.fragments.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.dataBase.Constants
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject
import com.example.updatedtrainingapp.databinding.TrainingFragmentBinding
import com.example.updatedtrainingapp.fragments.BaseFragment
import com.example.updatedtrainingapp.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrainingFragment : BaseFragment(R.layout.training_fragment),
    TrainingAdapter.OnTrainingItemClickListener, TrainingAdapter.OnTrainingItemLongClickListener {

    private val viewModel: TrainingViewModel by viewModels()
    private var binding: TrainingFragmentBinding? = null

    override fun onStart() {
        super.onStart()
        viewModel.getExercisesWithTraining()
            ?.let {
                it.observe(viewLifecycleOwner, { trainings ->
                    saveTrainingExercises(trainings)
                    val adapter = TrainingAdapter()
                    adapter.swapAdapter(trainings)
                    adapter.setOnTrainingItemClickListener(this)
                    adapter.setOnTrainingItemLongClickListener(this)
                    binding?.recyclerViewThisTraining?.adapter = adapter
                })
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TrainingFragmentBinding.inflate(inflater, container, false)
        return binding?.root
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

    override fun onTrainingItemLongClick(trainingObject: TrainingObject) {
        Utils.getAlertDialog(
            requireActivity(),
            getString(R.string.delete), "Sure you want to delete this?"
        ) { viewModel.deleteTrainingExercise(trainingObject) }
    }
}
