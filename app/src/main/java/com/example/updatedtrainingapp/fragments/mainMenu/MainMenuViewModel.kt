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

    fun getSeries(): LineGraphSeries<DataPoint>? {
        return LineGraphSeries(
            arrayOf(
                DataPoint(0.toDouble(), 1.toDouble()), DataPoint(1.toDouble(), 2.toDouble()),
                DataPoint(2.toDouble(), 0.toDouble())
            )
        )
    }

    fun getTrainingWithName(trainingName: String): LiveData<List<TrainingObject>>? {
        return trainingDBViewModel.getExercisesWithTraining(trainingName)
    }

    fun prepareStatistics(list: List<TrainingObject>?) {
        list?.forEach{

        }
    }
}
