package com.example.updatedtrainingapp.fragments.training

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.dataBase.objects.ExerciseObject
import com.example.updatedtrainingapp.databinding.TrainingFragmentBinding
import com.example.updatedtrainingapp.fragments.BaseFragment
import com.example.updatedtrainingapp.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrainingFragment : BaseFragment(R.layout.training_fragment),
    TrainingAdapter.OnTrainingItemClickListener, TrainingAdapter.OnTrainingItemLongClickListener {

    private val viewModel: TrainingViewModel by viewModels()
    private var binding: TrainingFragmentBinding? = null
    private var adapter: TrainingAdapter? = null

    override fun onStart() {
        super.onStart()
        setAdapter()
    }

    private fun fetchExerciseData() {
        val exerciseList: MutableList<ExerciseObject> = mutableListOf()
        val list = Utils.getTrainingExerciseList()
        list.forEach {
            viewModel.getExerciseWithName(it)?.observe(viewLifecycleOwner, { exercise ->
                exerciseList.add(exercise)
                if (exerciseList.size == list.size)
                    adapter?.swapAdapter(exerciseList)
            })
        }
    }

    private fun setAdapter() {
        adapter = TrainingAdapter()
        adapter?.setOnTrainingItemClickListener(this)
        adapter?.setOnTrainingItemLongClickListener(this)
        binding?.recyclerViewThisTraining?.adapter = adapter
        Handler().postDelayed({ fetchExerciseData() }, 100)
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

    override fun onTrainingItemLongClick(exerciseName: String) {
        Utils.getAlertDialog(
            requireActivity(),
            getString(R.string.delete), "Sure you want to delete $exerciseName?"
        ) {
            viewModel.deleteTrainingExercise(exerciseName)
            fetchExerciseData()
        }
    }
}
