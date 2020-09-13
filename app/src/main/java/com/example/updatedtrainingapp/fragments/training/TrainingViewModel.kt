package com.example.updatedtrainingapp.fragments.training

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.dataBase.Constants
import com.example.updatedtrainingapp.dataBase.dbViewModels.TrainingDBViewModel
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject

class TrainingViewModel @ViewModelInject constructor(private val trainingDBViewModel: TrainingDBViewModel) :
    ViewModel() {

    fun getExercisesWithTraining(
    ): LiveData<List<TrainingObject>>? {
        return trainingDBViewModel.getExercisesWithTraining(
            MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME)
        )
    }
}
