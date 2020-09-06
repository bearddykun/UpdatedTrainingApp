package com.example.updatedtrainingapp.dataBase.dbViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.updatedtrainingapp.dataBase.TrainingDatabase
import com.example.updatedtrainingapp.dataBase.objects.ExerciseInfoObject
import com.example.updatedtrainingapp.dataBase.repositories.ExerciseInfoRepository
import javax.inject.Inject

class ExerciseInfoDBViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {

    private val exerciseRepository: ExerciseInfoRepository? =
        TrainingDatabase.getDatabase(
            application
        )?.let {
            ExerciseInfoRepository(
                it.exerciseInfoDao()
            )
        }

    fun getExerciseInfoWithName(name: String, trainingName: String): LiveData<ExerciseInfoObject>? {
        return exerciseRepository?.let { it.getExerciseInfoWithName(name, trainingName) }
    }

    fun insertExercise(exerciseInfoObject: ExerciseInfoObject) {
        exerciseRepository?.insertExerciseInfoAsync(exerciseInfoObject)
    }

    fun deleteExercise(exerciseInfoObject: ExerciseInfoObject) {
        exerciseRepository?.deleteExerciseInfoAsync(exerciseInfoObject)
    }

    fun updateInfoExercise(exerciseInfoObject: ExerciseInfoObject) {
        exerciseRepository?.updateExerciseInfoAsync(exerciseInfoObject)
    }
}