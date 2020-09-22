package com.example.updatedtrainingapp.fragments.calendar

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.updatedtrainingapp.dataBase.dbViewModels.TrainingDBViewModel
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject

class CalendarViewModel @ViewModelInject constructor(
    private val trainingDBViewModel: TrainingDBViewModel
) : ViewModel() {

    fun getTrainingOnDate(date: String): LiveData<List<TrainingObject>>? {
        return trainingDBViewModel.getTrainingsWithData(date)
    }

    fun getDate(year: Int, month: Int, dayOfMonth: Int): String {
        return if (month + 1 < 10) {
            if (dayOfMonth < 10) {
                "${year}0${month + 1}0$dayOfMonth"
            } else {
                "${year}0${month + 1}$dayOfMonth"
            }
        } else {
            if (dayOfMonth < 10) {
                "${year}${month + 1}0$dayOfMonth"
            } else {
                "${year}${month + 1}$dayOfMonth"
            }
        }
    }
}
