package com.example.updatedtrainingapp.fragments.newExercise

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.updatedtrainingapp.dataBase.dbViewModels.ExerciseDBViewModel
import com.example.updatedtrainingapp.dataBase.objects.ExerciseObject
import kotlinx.coroutines.launch

class NewExerciseViewModel @ViewModelInject constructor(private val exerciseDBViewModel: ExerciseDBViewModel) :
    ViewModel() {

    private var exImageId = 0
    private var exGroupName = ""

    fun chooseMuscleGroup(imageId: Int, muscleGroup: String) {
        exImageId = imageId
        exGroupName = muscleGroup
    }

    fun addExerciseToDb(
        exName: String
    ) {
        val exercise = ExerciseObject(null, exName, exGroupName, exImageId.toString())
        viewModelScope.launch {
            exerciseDBViewModel.insertExercise(exercise)
        }
    }

    fun getExerciseWithName(name: String): LiveData<ExerciseObject> {
        return exerciseDBViewModel.getExerciseWithName(name)!!
    }
}