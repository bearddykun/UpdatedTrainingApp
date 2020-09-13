package com.example.updatedtrainingapp.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject

@Dao
interface TrainingDao {

    @Query("SELECT * FROM table_training")
    fun getAllExercises(): LiveData<List<TrainingObject>>

    @Query("SELECT * FROM table_training WHERE EXERCISE_NAME LIKE :name AND DATE LIKE :data LIMIT 1")
    fun getExerciseWithData(name: String, data: String): LiveData<TrainingObject>

    @Query("SELECT * FROM table_training WHERE TRAINING_NAME LIKE :trainingName")
    fun getExercisesWithTraining(
        trainingName: String
    ): LiveData<List<TrainingObject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercise(trainingObject: TrainingObject)

    @Update
    fun updateExercise(trainingObject: TrainingObject)

    @Delete
    fun deleteExercise(trainingObject: TrainingObject)
}