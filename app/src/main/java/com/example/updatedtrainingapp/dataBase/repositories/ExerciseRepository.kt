package com.example.updatedtrainingapp.dataBase.repositories

import androidx.lifecycle.LiveData
import com.example.updatedtrainingapp.dataBase.dao.ExerciseDao
import com.example.updatedtrainingapp.dataBase.objects.ExerciseObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ExerciseRepository @Inject constructor(private val exerciseDao: ExerciseDao) {

    fun getAllExercises(): LiveData<List<ExerciseObject>> {
        return exerciseDao.getAllExercises()
    }

    fun getExerciseWithName(exName: String): LiveData<ExerciseObject> {
        return exerciseDao.getExerciseWithName(exName)
    }

    suspend fun insertExerciseAsync(exerciseObject: ExerciseObject) {
        withContext(Dispatchers.Default) {
            exerciseDao.insertExercise(exerciseObject)
        }
    }

    suspend fun updateExerciseAsync(exerciseObject: ExerciseObject) {
        withContext(Dispatchers.Default) {
            exerciseDao.updateExercise(exerciseObject)
        }
    }

    suspend fun deleteExerciseAsync(exerciseObject: ExerciseObject) {
        withContext(Dispatchers.Default) {
            exerciseDao.deleteExercise(exerciseObject)
        }
    }
}