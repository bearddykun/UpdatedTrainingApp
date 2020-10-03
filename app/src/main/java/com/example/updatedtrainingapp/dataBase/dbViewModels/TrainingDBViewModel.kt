package com.example.updatedtrainingapp.dataBase.dbViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.updatedtrainingapp.dataBase.TrainingDatabase
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject
import com.example.updatedtrainingapp.dataBase.repositories.TrainingRepository
import javax.inject.Inject

class TrainingDBViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {

    private val trainingRepository: TrainingRepository? =
        TrainingDatabase.getDatabase(
            application
        )?.let { TrainingRepository(it.trainingDao()) }

    fun getExercises(): LiveData<List<TrainingObject>>? {
        return trainingRepository?.returnAllExercises()
    }

    fun getExerciseWithData(exerciseName: String, data: String): LiveData<TrainingObject>? {
        return trainingRepository?.getExerciseWithData(exerciseName, data)
    }

    suspend fun insertExercise(trainingObject: TrainingObject) {
        trainingRepository?.insertExerciseAsync(trainingObject)
    }

    suspend fun updateExercise(trainingObject: TrainingObject) {
        trainingRepository?.updateExerciseAsync(trainingObject)
    }

    suspend fun deleteExercise(trainingName: TrainingObject) {
        trainingRepository?.deleteExerciseAsync(trainingName)
    }

    fun getExercisesWithTraining(trainingName: String): LiveData<List<TrainingObject>>? {
        return trainingRepository?.getExercisesWithTraining(trainingName)
    }

    fun getExerciseWithTrainingName(
        exerciseName: String,
        trainingName: String
    ): LiveData<TrainingObject>? {
        return trainingRepository?.getExerciseWithTrainingName(exerciseName, trainingName)
    }

    fun isExerciseInThisTraining(
        exerciseName: String,
        realDate: String,
        trainingName: String
    ): LiveData<TrainingObject>? {
        return trainingRepository?.isExerciseInThisTraining(exerciseName, realDate, trainingName)
    }

    fun getTrainingsWithData(date: String): LiveData<List<TrainingObject>>? {
        return trainingRepository?.getTrainingsWithData(date)
    }
}