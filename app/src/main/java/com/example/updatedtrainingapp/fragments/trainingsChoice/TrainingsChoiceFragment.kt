package com.example.updatedtrainingapp.fragments.trainingsChoice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.dataBase.Constants
import com.example.updatedtrainingapp.dataBase.dbViewModels.TrainingDBViewModel
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject
import com.example.updatedtrainingapp.databinding.TrainingsChoiceFragmentBinding
import com.example.updatedtrainingapp.fragments.BaseFragment
import com.example.updatedtrainingapp.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrainingsChoiceFragment : BaseFragment(R.layout.trainings_choice_fragment),
    TrainingsAdapter.OnTrainingItemListener,
    TrainingsAdapter.OnTrainingItemLongListener {

    private val trainingViewModel: TrainingDBViewModel by viewModels()
    private var binding: TrainingsChoiceFragmentBinding? = null

    private fun startTraining() {
        if (!MySharedPreferences.isInside(Constants.SAVE_START_TIME)) {
            val startTime = System.currentTimeMillis()
            MySharedPreferences.saveLong(Constants.SAVE_START_TIME, startTime)
        }
        findNavController().navigate(TrainingsChoiceFragmentDirections.actionFragmentThisTrainingFragmentToFragmentTrainingDay())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TrainingsChoiceFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onTrainingListItemClick(name: String) {
        Utils.getAlertDialog(
            requireActivity(),
            getString(R.string.start_training),
            getString(R.string.start_training_timer)
        )
        {
            MySharedPreferences.saveString(
                Constants.SAVE_TRAINING_NAME,
                name
            )
            trainingViewModel.insertExercise(
                TrainingObject(
                    null,
                    trainingName = name,
                    trainingNameWithDate = Utils.getNameWithDate(name)
                )
            )
            startTraining()
        }
    }

    override fun onTrainingListItemLongClick(name: String) {
        Utils.getAlertDialog(
            requireActivity(),
            getString(R.string.delete),
            getString(R.string.delete_training)
        )
        {
            val list = MySharedPreferences.getList(Constants.SAVE_NEW_TRAINING_LIST)
            list.remove(name)
            MySharedPreferences.saveList(Constants.SAVE_NEW_TRAINING_LIST, list)
        }
    }

    override fun onStart() {
        super.onStart()
        binding?.addTrainingBtn?.setOnClickListener {
            findNavController().navigate(TrainingsChoiceFragmentDirections.actionFragmentThisTrainingFragmentToFragmentCreateTraining())
        }
        setAdapter()
    }

    private fun setAdapter() {
        val adapter = TrainingsAdapter()
        adapter.swapAdapter(MySharedPreferences.getList(Constants.SAVE_NEW_TRAINING_LIST).toList())
        adapter.setOnTrainingItemListener(this)
        adapter.setOnTrainingItemLongListener(this)
        binding?.trainingRecyclerView?.adapter = adapter
    }
}


