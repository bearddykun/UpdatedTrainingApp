package com.example.updatedtrainingapp.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject

@Dao
interface TrainingDao {

    @Query("SELECT * FROM table_training")
    fun getAllTrainings(): LiveData<List<TrainingObject>>

    @Query("SELECT * FROM table_training WHERE trainingNameWithDate LIKE :name LIMIT 1")
    fun getTrainingWithDate(name: String): LiveData<TrainingObject>

    @Insert
    fun insertTraining(trainingObject: TrainingObject)

    @Update
    fun updateTraining(trainingObject: TrainingObject)

    @Delete
    fun deleteTraining(trainingObject: TrainingObject)

}