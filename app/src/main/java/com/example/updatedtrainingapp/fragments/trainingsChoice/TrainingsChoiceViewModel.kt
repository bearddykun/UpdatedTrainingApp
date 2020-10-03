package com.example.updatedtrainingapp.fragments.trainingsChoice

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.dataBase.Constants
import com.example.updatedtrainingapp.utils.Utils

class TrainingsChoiceViewModel @ViewModelInject constructor() : ViewModel() {

    fun startTraining(name: String) {
        MySharedPreferences.saveString(
            Constants.SAVE_TRAINING_NAME,
            name
        )
        if (!MySharedPreferences.isInside(name)) {
            Utils.saveTrainingExerciseString(mutableListOf())
        }
        if (!MySharedPreferences.isInside(Constants.SAVE_START_TIME)) {
            val startTime = System.currentTimeMillis()
            MySharedPreferences.saveLong(Constants.SAVE_START_TIME, startTime)
        }
    }

    fun deleteAndUpdate(name: String) {
        val list = MySharedPreferences.getList(Constants.SAVE_NEW_TRAINING_LIST)
        list.remove(name)
        MySharedPreferences.saveList(Constants.SAVE_NEW_TRAINING_LIST, list)
    }

}
