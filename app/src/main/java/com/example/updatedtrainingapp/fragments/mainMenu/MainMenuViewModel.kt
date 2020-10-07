package com.example.updatedtrainingapp.fragments.mainMenu

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.dataBase.Constants
import com.example.updatedtrainingapp.dataBase.dbViewModels.TrainingDBViewModel
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class MainMenuViewModel @ViewModelInject constructor(private val trainingDBViewModel: TrainingDBViewModel) :
    ViewModel() {

    fun getTrainingList(): List<String> {
        return MySharedPreferences.getList(Constants.SAVE_NEW_TRAINING_LIST).toList()
    }

    fun getTrainingWithName(trainingName: String): LiveData<List<TrainingObject>>? {
        return trainingDBViewModel.getExercisesWithTraining(trainingName)
    }

    fun prepareStatistics(list: List<TrainingObject>?): LineGraphSeries<DataPoint>? {
        val array: Array<DataPoint?> = arrayOfNulls(list?.size ?: 0)
        list?.let { listOfObjects ->
            for (i in listOfObjects.indices) {
                array[i] = (DataPoint(i.toDouble(), listOfObjects[i].trainingWeight.toDouble()))
            }
        }
        return LineGraphSeries(array)
    }
}
