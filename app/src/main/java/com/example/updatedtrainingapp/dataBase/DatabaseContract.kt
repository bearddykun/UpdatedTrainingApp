package com.example.updatedtrainingapp.dataBase

class DatabaseContract {

    companion object {
        const val DATABASE_NAME = "TRAINING_DATABASE"
        const val TABLE_TRAINING = "TABLE_TRAINING"
        const val TABLE_EXERCISE = "TABLE_EXERCISE"
    }

    class TrainingColumns {
        companion object {
            const val TRAINING_NAME = "trainingName"
            const val TRAINING_NAME_WITH_DATE = "trainingNameWithDate"
            const val TRAINING_DATE = "trainingDate"
            const val TRAINING_EXERCISE_NAME_LIST = "trainingExerciseNameList"
            const val TRAINING_TIME_BETWEEN_SETS = "trainingTimeBetweenSets"
            const val TRAINING_TOTAL_TIME = "trainingTotalTime"
            const val TRAINING_PROGRESS_LIST = "trainingProgressList"
        }
    }

    class ExerciseColumns {
        companion object {
            const val EXERCISE_NAME = "exerciseName"
            const val EXERCISE_IMAGE = "exerciseGroup"
            const val EXERCISE_GROUP = "exerciseImage"
        }
    }
}