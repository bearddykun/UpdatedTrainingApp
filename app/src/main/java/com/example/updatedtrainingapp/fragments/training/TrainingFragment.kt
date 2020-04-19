package com.example.updatedtrainingapp.fragments.training

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.application.ThisApplication
import com.example.updatedtrainingapp.dataBase.Constants
import com.example.updatedtrainingapp.utils.Utils
import kotlinx.android.synthetic.main.training_fragment.*
import javax.inject.Inject

class TrainingFragment : Fragment(R.layout.training_fragment), TrainingAdapter.OnTrainingItemClickListener {

    @Inject
    lateinit var trainingDayViewModel: TrainingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as ThisApplication).appComponent.inject(this)
    }

    override fun onStart() {
        super.onStart()
        trainingDayViewModel.getTraining(MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME))
            .observe(viewLifecycleOwner, Observer { training ->
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

    override fun onTrainingItemClick(exerciseName: String) {
        findNavController().navigate(TrainingFragmentDirections.actionFragmentTrainingDayToCurrentExerciseFragment(exerciseName))
    }
}
