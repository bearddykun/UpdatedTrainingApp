package com.example.updatedtrainingapp.dataBase.repositories

import androidx.lifecycle.LiveData
import com.example.updatedtrainingapp.dataBase.dao.TrainingDao
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrainingRepository @Inject constructor(private val trainingDao: TrainingDao) {

    fun returnAllExercises(): LiveData<List<TrainingObject>> {
        return trainingDao.getAllExercises()
    }

    suspend fun insertExerciseAsync(trainingObject: TrainingObject) {
        withContext(Dispatchers.Default) {
            trainingDao.insertExercise(trainingObject)
        }
    }

    suspend fun updateExerciseAsync(trainingObject: TrainingObject) {
        withContext(Dispatchers.Default) {
            trainingDao.updateExercise(trainingObject)
        }
    }

    suspend fun deleteExerciseAsync(trainingObject: TrainingObject) {
        withContext(Dispatchers.Default) {
            trainingDao.deleteExercise(trainingObject)
        }
    }

    fun getExerciseWithData(name: String, data: String): LiveData<TrainingObject> {
        return trainingDao.getExerciseWithData(name, data)
    }

    fun getExercisesWithTraining(trainingName: String): LiveData<List<TrainingObject>>? {
        return trainingDao.getExercisesWithTraining(trainingName)
    }

    fun isExerciseInThisTraining(
        exerciseName: String,
        realDate: String,
        trainingName: String
    ): LiveData<TrainingObject>? {
        return trainingDao.isExerciseInThisTraining(exerciseName, realDate, trainingName)
    }

    fun getTrainingsWithData(date: String): LiveData<List<TrainingObject>> {
        return trainingDao.getTrainingsWithData(date)
    }

    fun getExerciseWithTrainingName(
        exerciseName: String,
        trainingName: String
    ): LiveData<TrainingObject>? {
        return trainingDao.getExerciseWithTrainingName(exerciseName, trainingName)
    }
}