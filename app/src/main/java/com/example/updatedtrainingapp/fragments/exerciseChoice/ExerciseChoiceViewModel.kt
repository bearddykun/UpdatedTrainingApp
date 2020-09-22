package com.example.updatedtrainingapp.fragments.exerciseChoice

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.updatedtrainingapp.dataBase.dbViewModels.ExerciseDBViewModel
import com.example.updatedtrainingapp.dataBase.objects.ExerciseObject
import com.example.updatedtrainingapp.utils.Utils

class ExerciseChoiceViewModel @ViewModelInject constructor(
    private val exerciseDBViewModel: ExerciseDBViewModel
) : ViewModel() {

    val listOfNewExercises: MutableList<String> = mutableListOf()

    fun getAllExercises(): LiveData<List<ExerciseObject>>? {
        return exerciseDBViewModel.getAllExercises()
    }

    fun addExercise() {
        val list = Utils.getTrainingExerciseList()
        list.addAll(listOfNewExercises)
        Utils.saveTrainingExerciseString(list)
        listOfNewExercises.clear()
    }

    fun getList(exerciseList: List<ExerciseObject>): MutableList<ExerciseObject> {
        val oldList = Utils.getTrainingExerciseList()

        val newExList: MutableList<ExerciseObject> = mutableListOf()
        for (i in exerciseList) {
            if (!oldList.contains(i.exerciseName)) {
                newExList.add(i)
            }
        }
        return newExList
    }
}