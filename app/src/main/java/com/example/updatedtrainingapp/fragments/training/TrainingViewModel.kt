package com.example.updatedtrainingapp.fragments.training

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.updatedtrainingapp.dataBase.dbViewModels.TrainingDBViewModel
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject
import javax.inject.Inject

class TrainingViewModel @ViewModelInject constructor(private val trainingDBViewModel: TrainingDBViewModel): ViewModel() {

    fun getTrainingWithDate(trainingName: String): LiveData<TrainingObject>? {
        return trainingDBViewModel.getTrainingWithDate(trainingName)
    }
}
