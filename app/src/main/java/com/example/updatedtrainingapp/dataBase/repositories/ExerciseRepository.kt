package com.example.updatedtrainingapp.dataBase.repositories

import androidx.lifecycle.LiveData
import com.example.updatedtrainingapp.dataBase.dao.ExerciseDao
import com.example.updatedtrainingapp.dataBase.objects.ExerciseObject
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class ExerciseRepository @Inject constructor(private val exerciseDao: ExerciseDao) {

    fun getAllExercises(): LiveData<List<ExerciseObject>> {
        return exerciseDao.getAllExercises()
    }

    fun getExerciseWithName(exName: String): LiveData<ExerciseObject> {
        return exerciseDao.getExerciseWithName(exName)
    }

    fun insertExerciseAsync(exerciseObject: ExerciseObject) {
        doAsync { exerciseDao.insertExercise(exerciseObject) }
    }

    fun updateExerciseAsync(exerciseObject: ExerciseObject) {
        doAsync { exerciseDao.updateExercise(exerciseObject) }
    }

    fun deleteExerciseAsync(exerciseObject: ExerciseObject) {
        doAsync { exerciseDao.deleteExercise(exerciseObject) }
    }
}