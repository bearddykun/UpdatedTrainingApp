package com.example.updatedtrainingapp.dataBase.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.updatedtrainingapp.dataBase.DatabaseContract

@Entity(tableName = DatabaseContract.TABLE_TRAINING)
data class TrainingObject(
        @PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo(name = DatabaseContract.TrainingColumns.TRAINING_NAME) var trainingName: String = "",
        @ColumnInfo(name = DatabaseContract.TrainingColumns.TRAINING_DATE) var trainingDate: String = "",
        @ColumnInfo(name = DatabaseContract.TrainingColumns.TRAINING_EXERCISE_NAME) var trainingExerciseNameList: String = "",
        @ColumnInfo(name = DatabaseContract.TrainingColumns.TRAINING_EXERCISE_REPETITIONS) var trainingExerciseRepetitions: String = "",
        @ColumnInfo(name = DatabaseContract.TrainingColumns.TRAINING_TIME_BETWEEN_SETS) var trainingTimeBetweenSets: String = "",
        @ColumnInfo(name = DatabaseContract.TrainingColumns.TRAINING_TOTAL_TIME) var trainingTotalTime: String = "")