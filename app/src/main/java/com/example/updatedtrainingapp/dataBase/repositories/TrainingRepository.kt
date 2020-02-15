package com.example.updatedtrainingapp.dataBase.repositories

import androidx.lifecycle.LiveData
import com.example.updatedtrainingapp.dataBase.dao.TrainingDao
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class TrainingRepository @Inject constructor(private val trainingDao: TrainingDao) {

    fun returnAllTrainings(): LiveData<List<TrainingObject>> {
        return trainingDao.getAllTrainings()
    }

    fun insertTrainingAsync(trainingObject: TrainingObject) {
        doAsync { trainingDao.insertTraining(trainingObject) }
    }

    fun updateTrainingAsync(trainingObject: TrainingObject) {
        doAsync { trainingDao.updateTraining(trainingObject) }
    }

    fun deleteTrainingAsync(trainingObject: TrainingObject) {
        doAsync { trainingDao.deleteTraining(trainingObject) }
    }

    fun getTraining(name: String):LiveData<TrainingObject>{
        return trainingDao.getTraining(name)
    }
}