package com.example.updatedtrainingapp.fragments.training

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.updatedtrainingapp.dataBase.dbViewModels.ExerciseDBViewModel
import com.example.updatedtrainingapp.dataBase.objects.ExerciseObject
import com.example.updatedtrainingapp.utils.Utils

class TrainingViewModel @ViewModelInject constructor(private val exerciseDBViewModel: ExerciseDBViewModel) :
    ViewModel() {

    fun getExerciseWithName(
        exerciseName: String
    ): LiveData<ExerciseObject>? {
        return exerciseDBViewModel.getExerciseWithName(exerciseName)
    }

    fun deleteTrainingExercise(exerciseName: String) {
        val list = Utils.getTrainingExerciseList()
        list.remove(exerciseName)
        Utils.saveTrainingExerciseString(list)
    }
}
