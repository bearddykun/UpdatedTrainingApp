package com.example.updatedtrainingapp.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.updatedtrainingapp.dataBase.objects.ExerciseInfoObject

@Dao
interface ExerciseInfoDao {

    @Insert
    fun insertExercise(exerciseInfoObject: ExerciseInfoObject)

    @Update
    fun updateExercise(exerciseInfoObject: ExerciseInfoObject)

    @Delete
    fun deleteExercise(exerciseInfoObject: ExerciseInfoObject)

    @Query("SELECT * FROM table_exercise_info WHERE exerciseName LIKE :exName AND exerciseTrainingName LIKE :exTrName")
    fun getExerciseWithTrainingName(exName: String, exTrName: String): LiveData<ExerciseInfoObject>
}