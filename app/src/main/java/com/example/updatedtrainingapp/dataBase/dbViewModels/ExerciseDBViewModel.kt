package com.example.updatedtrainingapp.dataBase.dbViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.updatedtrainingapp.dataBase.TrainingDatabase
import com.example.updatedtrainingapp.dataBase.objects.ExerciseObject
import com.example.updatedtrainingapp.dataBase.repositories.ExerciseRepository
import javax.inject.Inject

class ExerciseDBViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {

    private val exerciseRepository: ExerciseRepository? =
        TrainingDatabase.getDatabase(
            application
        )?.let { ExerciseRepository(it.exerciseDao()) }

    fun getAllExercises(): LiveData<List<ExerciseObject>>? {
        return exerciseRepository?.getAllExercises()
    }

    fun getExerciseWithName(name: String): LiveData<ExerciseObject>? {
        return exerciseRepository?.getExerciseWithName(name)
    }

    suspend fun insertExercise(exerciseObject: ExerciseObject) {
        exerciseRepository?.insertExerciseAsync(exerciseObject)
    }

    suspend fun deleteExercise(exerciseObject: ExerciseObject) {
        exerciseRepository?.deleteExerciseAsync(exerciseObject)
    }

    suspend fun updateExercise(exerciseObject: ExerciseObject) {
        exerciseRepository?.updateExerciseAsync(exerciseObject)
    }
}