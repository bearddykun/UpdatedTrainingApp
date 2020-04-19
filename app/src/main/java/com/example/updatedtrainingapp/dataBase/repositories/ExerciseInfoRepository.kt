package com.example.updatedtrainingapp.dataBase.repositories

import androidx.lifecycle.LiveData
import com.example.updatedtrainingapp.dataBase.dao.ExerciseInfoDao
import com.example.updatedtrainingapp.dataBase.objects.ExerciseInfoObject
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class ExerciseInfoRepository @Inject constructor(private val exerciseInfoDao: ExerciseInfoDao) {

    fun getExerciseInfoWithName(exName: String, exTrName: String): LiveData<ExerciseInfoObject> {
        return exerciseInfoDao.getExerciseWithTrainingName(exName, exTrName)
    }

    fun insertExerciseInfoAsync(exerciseInfoObject: ExerciseInfoObject) {
        doAsync { exerciseInfoDao.insertExercise(exerciseInfoObject) }
    }

    fun updateExerciseInfoAsync(exerciseInfoObject: ExerciseInfoObject) {
        doAsync { exerciseInfoDao.updateExercise(exerciseInfoObject) }
    }

    fun deleteExerciseInfoAsync(exerciseInfoObject: ExerciseInfoObject) {
        doAsync { exerciseInfoDao.deleteExercise(exerciseInfoObject) }
    }
}