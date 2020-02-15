package com.example.updatedtrainingapp.dataBase.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.updatedtrainingapp.dataBase.DatabaseContract

@Entity(tableName = DatabaseContract.TABLE_EXERCISE)
data class ExerciseObject(
        @PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo(name = DatabaseContract.ExerciseColumns.EXERCISE_NAME) val exerciseName: String,
        @ColumnInfo(name = DatabaseContract.ExerciseColumns.EXERCISE_GROUP) val exerciseGroup: String,
        @ColumnInfo(name = DatabaseContract.ExerciseColumns.EXERCISE_IMAGE) val exerciseImage: String
)