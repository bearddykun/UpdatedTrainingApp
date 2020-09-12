package com.example.updatedtrainingapp.fragments.training

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.dataBase.Constants
import com.example.updatedtrainingapp.fragments.BaseFragment
import com.example.updatedtrainingapp.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.training_fragment.*

@AndroidEntryPoint
class TrainingFragment : BaseFragment(R.layout.training_fragment),
    TrainingAdapter.OnTrainingItemClickListener {

    private val trainingDayViewModel: TrainingViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        trainingDayViewModel.getTrainingWithDate(MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME))
            ?.let {
                it.observe(viewLifecycleOwner, Observer { training ->
                    val list = Utils.stringToList(training.trainingExerciseNameList)
                    list.remove("")

                    val adapter = TrainingAdapter()
                    adapter.swapAdapter(list)
                    adapter.setOnTrainingItemClickListener(this)
                    recyclerViewThisTraining.adapter = adapter
                    MySharedPreferences.saveString(
                        Utils.getCurrentTrainingList(),
                        Utils.listToString(list)
                    )
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
