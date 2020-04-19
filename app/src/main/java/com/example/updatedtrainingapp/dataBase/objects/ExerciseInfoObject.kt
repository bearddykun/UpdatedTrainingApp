package com.example.updatedtrainingapp.dataBase.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.updatedtrainingapp.dataBase.DatabaseContract

@Entity(tableName = DatabaseContract.TABLE_EXERCISE_INFO)
class ExerciseInfoObject(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = DatabaseContract.ExerciseInfoColumns.EXERCISE_NAME) var exerciseName: String = "",
    @ColumnInfo(name = DatabaseContract.ExerciseInfoColumns.EXERCISE_TRAINING_NAME) var exerciseTrainingName: String = "",
    @ColumnInfo(name = DatabaseContract.ExerciseInfoColumns.EXERCISE_REPS) var exerciseReps: String = "",
    @ColumnInfo(name = DatabaseContract.ExerciseInfoColumns.EXERCISE_MAX_WEIGHT) var exerciseMaxWeight: String = ""
)