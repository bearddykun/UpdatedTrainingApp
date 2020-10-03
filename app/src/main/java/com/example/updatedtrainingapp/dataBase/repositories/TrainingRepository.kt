package com.example.updatedtrainingapp.dataBase.repositories

import androidx.lifecycle.LiveData
import com.example.updatedtrainingapp.dataBase.dao.TrainingDao
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject
import javax.inject.Inject

class TrainingRepository @Inject constructor(private val trainingDao: TrainingDao) {

    fun returnAllExercises(): LiveData<List<TrainingObject>> {
        return trainingDao.getAllExercises()
    }

    fun insertExerciseAsync(trainingObject: TrainingObject) {
        trainingDao.insertExercise(trainingObject)
    }

    fun updateExerciseAsync(trainingObject: TrainingObject) {
        trainingDao.updateExercise(trainingObject)
    }

    fun deleteExerciseAsync(trainingObject: TrainingObject) {
        trainingDao.deleteExercise(trainingObject)
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