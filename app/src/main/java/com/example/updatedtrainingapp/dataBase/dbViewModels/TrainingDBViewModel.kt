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

    fun getTrainings(): LiveData<List<TrainingObject>>? {
        return trainingRepository?.let { it.returnAllTrainings() }
    }

    fun getTraining(trainingName: String): LiveData<TrainingObject>? {
        return trainingRepository?.let { it.getTraining(trainingName) }
    }

    fun getTrainingWithDate(trainingName: String): LiveData<TrainingObject>? {
        return trainingRepository?.let { it.getTrainingWithDate(trainingName) }
    }

    fun insertTraining(trainingObject: TrainingObject) {
        trainingRepository?.insertTrainingAsync(trainingObject)
    }

    fun updateTraining(trainingObject: TrainingObject) {
        trainingRepository?.updateTrainingAsync(trainingObject)
    }

    fun deleteTraining(trainingName: String) {
        trainingRepository?.getTraining(trainingName)
    }
}