package com.example.updatedtrainingapp.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.updatedtrainingapp.dataBase.objects.ExerciseObject

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM table_exercise")
    fun getAllExercises(): LiveData<List<ExerciseObject>>

    @Insert
    fun insertExercise(exerciseObject: ExerciseObject)

    @Update
    fun updateExercise(exerciseObject: ExerciseObject)

    @Delete
    fun deleteExercise(exerciseObject: ExerciseObject)

    @Query("SELECT * FROM table_exercise WHERE EXERCISE_NAME LIKE :exName")
    fun getExerciseWithName(exName: String): LiveData<ExerciseObject>
}