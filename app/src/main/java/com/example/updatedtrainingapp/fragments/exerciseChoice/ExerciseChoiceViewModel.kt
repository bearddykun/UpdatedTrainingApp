package com.example.updatedtrainingapp.fragments.exerciseChoice

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.dataBase.Constants
import com.example.updatedtrainingapp.dataBase.dbViewModels.ExerciseDBViewModel
import com.example.updatedtrainingapp.dataBase.dbViewModels.TrainingDBViewModel
import com.example.updatedtrainingapp.dataBase.objects.ExerciseObject
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject
import com.example.updatedtrainingapp.utils.Utils

class ExerciseChoiceViewModel @ViewModelInject constructor(
    private val exerciseDBViewModel: ExerciseDBViewModel,
    private val trainingViewModel: TrainingDBViewModel
) : ViewModel() {

    var list: MutableSet<Pair<String, String>> = mutableSetOf()

    fun getAllExercises(): LiveData<List<ExerciseObject>>? {
        return exerciseDBViewModel.getAllExercises()
    }

    fun addExercise(pair: Pair<String, String>) {
        trainingViewModel.insertExercise(
            TrainingObject(
                null,
                exerciseName = pair.first,
                exerciseImage = pair.second,
                trainingName = MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME),
            )
        )
    }

    fun getList(exerciseList: List<ExerciseObject>): MutableList<ExerciseObject> {
        val oldList =
            Utils.stringToList(
                MySharedPreferences.getString(
                    Utils.getCurrentTrainingList()
                )
            )
        val newExList: MutableList<ExerciseObject> = mutableListOf()
        for (i in exerciseList) {
            if (!oldList.contains(i.exerciseName)) {
                newExList.add(i)
            }
        }
        return newExList
    }
}