package com.example.updatedtrainingapp.dataBase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject
import com.example.updatedtrainingapp.dataBase.repositories.TrainingRepository
import javax.inject.Inject

class TrainingViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private val trainingRepository: TrainingRepository = TrainingRepository(TrainingDatabase.getDatabase(application)!!.trainingDao())

    fun getTrainings(): LiveData<List<TrainingObject>> {
        return trainingRepository.returnAllTrainings()
    }

    fun getTraining(trainingName: String): LiveData<TrainingObject> {
        return trainingRepository.getTraining(trainingName)
    }

    fun insertTraining(trainingObject: TrainingObject){
        trainingRepository.insertTrainingAsync(trainingObject)
    }

    fun updateTraining(trainingObject: TrainingObject){
        trainingRepository.updateTrainingAsync(trainingObject)
    }

    fun deleteTraining(trainingName: String){
        trainingRepository.getTraining(trainingName)
    }
}