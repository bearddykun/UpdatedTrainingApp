package com.example.updatedtrainingapp.dataBase

class DatabaseContract {

    companion object {
        const val DATABASE_NAME = "TRAINING_DATABASE"
        const val TABLE_TRAINING = "TABLE_TRAINING"
        const val TABLE_EXERCISE = "TABLE_EXERCISE"
        const val TABLE_EXERCISE_INFO = "TABLE_EXERCISE_INFO"
    }

    class TrainingColumns {
        companion object {
            const val TRAINING_NAME = "trainingName"
            const val TRAINING_NAME_WITH_DATE = "trainingNameWithDate"
            const val TRAINING_DATE = "trainingDate"
            const val TRAINING_EXERCISE_NAME = "trainingExerciseNameList"
            const val TRAINING_TIME_BETWEEN_SETS = "trainingTimeBetweenSets"
            const val TRAINING_TOTAL_TIME = "trainingTotalTime"
        }
    }

    class ExerciseColumns {
        companion object {
            const val EXERCISE_NAME = "exerciseName"
            const val EXERCISE_IMAGE = "exerciseGroup"
            const val EXERCISE_GROUP = "exerciseImage"
        }
    }

    class ExerciseInfoColumns {
        companion object {
            const val EXERCISE_NAME = "exerciseName"
            const val EXERCISE_TRAINING_NAME = "exerciseTrainingName"
            const val EXERCISE_REPS = "exerciseReps"
            const val EXERCISE_MAX_WEIGHT = "exerciseMaxWeight"
        }
    }
}