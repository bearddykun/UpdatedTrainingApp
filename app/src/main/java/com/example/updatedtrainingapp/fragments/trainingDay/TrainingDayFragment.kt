package com.example.updatedtrainingapp.fragments.trainingDay

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.updatedtrainingapp.MainActivity
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.application.ThisApplication
import com.example.updatedtrainingapp.dataBase.Constants
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject
import com.example.updatedtrainingapp.utils.Utils
import kotlinx.android.synthetic.main.training_day_fragment.*
import javax.inject.Inject

class TrainingDayFragment : Fragment(R.layout.training_day_fragment) {

    @Inject
    lateinit var trainingDayViewModel: TrainingDayViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as ThisApplication).appComponent.inject(this)
    }

    override fun onStart() {
        super.onStart()
        trainingDayViewModel.getTraining(MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME))
            .observe(viewLifecycleOwner, Observer<TrainingObject> { training ->
                val list = Utils.stringToList(training.trainingExerciseNameList)

                val adapter = TrainingDayAdapter()
                adapter.swapAdapter(list)
                recyclerViewThisTraining.adapter = adapter
                MySharedPreferences.saveString(
                    Utils.getCurrentTrainingList(),
                    Utils.listToString(list)
                )
            })
    }
}
