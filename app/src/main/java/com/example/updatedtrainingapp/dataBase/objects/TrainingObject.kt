package com.example.updatedtrainingapp.dataBase.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.updatedtrainingapp.dataBase.DatabaseContract

@Entity(tableName = DatabaseContract.TABLE_TRAINING)
data class TrainingObject(
        @PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo(name = DatabaseContract.TrainingColumns.DATE) var date: String = "",
        @ColumnInfo(name = DatabaseContract.TrainingColumns.EXERCISE_NAME) var exerciseName: String = "",
        @ColumnInfo(name = DatabaseContract.TrainingColumns.EXERCISE_TEXT) var exerciseText: String = "",
        @ColumnInfo(name = DatabaseContract.TrainingColumns.EXERCISE_IMAGE) var exerciseImage: String = "",
        @ColumnInfo(name = DatabaseContract.TrainingColumns.REAL_DATE) var realDate: String = "",
        @ColumnInfo(name = DatabaseContract.TrainingColumns.WEIGHT) var weight: String = "",
        @ColumnInfo(name = DatabaseContract.TrainingColumns.COMMENTS) var comments: String = "",
        @ColumnInfo(name = DatabaseContract.TrainingColumns.TRAINING_NAME) var trainingName: String = "",
        @ColumnInfo(name = DatabaseContract.TrainingColumns.TOTAL_WEIGHT) var trainingWeight: Int = 0,
        @ColumnInfo(name = DatabaseContract.TrainingColumns.TRAINING_TIME_BETWEEN_SETS) var trainingTimeBetweenSets: String = "",
        @ColumnInfo(name = DatabaseContract.TrainingColumns.TRAINING_TOTAL_TIME) var trainingTotalTime: String = ""
)