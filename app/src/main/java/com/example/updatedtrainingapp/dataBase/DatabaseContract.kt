package com.example.updatedtrainingapp.dataBase

class DatabaseContract {

    companion object {
        const val DATABASE_NAME = "TRAINING_DATABASE"
        const val TABLE_TRAINING = "TABLE_TRAINING"
        const val TABLE_EXERCISE = "TABLE_EXERCISE"
    }

    class TrainingColumns {
        companion object {
            const val DATE = "DATE"
            const val EXERCISE_NAME = "EXERCISE_NAME"
            const val EXERCISE_TEXT = "EXERCISE_TEXT"
            const val REAL_DATE = "REAL_DATE"
            const val WEIGHT = "WEIGHT"
            const val COMMENTS = "COMMENTS"
            const val TRAINING_NAME = "TRAINING_NAME"
            const val TRAINING_NAME_WITH_DATE = "TRAINING_NAME_WITH_DATE"
            const val TOTAL_WEIGHT = "TOTAL_WEIGHT"
            const val TRAINING_TIME_BETWEEN_SETS = "TRAINING_TIME_BETWEEN_SETS"
            const val TRAINING_TOTAL_TIME = "TRAINING_TOTAL_TIME"
        }
    }

    class ExerciseColumns {
        companion object {
            const val EXERCISE_NAME = "EXERCISE_NAME"
            const val EXERCISE_IMAGE = "EXERCISE_IMAGE"
            const val EXERCISE_GROUP = "EXERCISE_GROUP"
            const val EXERCISE_GROUP_IMAGE = "EXERCISE_GROUP_IMAGE"
        }
    }
}