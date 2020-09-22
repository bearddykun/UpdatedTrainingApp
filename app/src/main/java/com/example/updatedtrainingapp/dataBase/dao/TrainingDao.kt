package com.example.updatedtrainingapp.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject

@Dao
interface TrainingDao {

    @Query("SELECT * FROM table_training")
    fun getAllExercises(): LiveData<List<TrainingObject>>

    @Query("SELECT * FROM table_training WHERE EXERCISE_NAME LIKE :name AND REAL_DATE LIKE :data LIMIT 1")
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

    @Query("SELECT * FROM table_training WHERE EXERCISE_NAME LIKE :exerciseName AND REAL_DATE LIKE :realDate AND TRAINING_NAME LIKE :trainingName LIMIT 1")
    fun isExerciseInThisTraining(
        exerciseName: String,
        realDate: String,
        trainingName: String
    ): LiveData<TrainingObject>

    @Query("SELECT * FROM table_training WHERE REAL_DATE LIKE :date")
    fun getTrainingsWithData(date: String): LiveData<List<TrainingObject>>

    @Query("SELECT * FROM table_training WHERE EXERCISE_NAME LIKE :exerciseName AND TRAINING_NAME LIKE :trainingName LIMIT 1")
    fun getExerciseWithTrainingName(
        exerciseName: String,
        trainingName: String
    ): LiveData<TrainingObject>?
}