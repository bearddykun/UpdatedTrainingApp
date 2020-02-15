package com.example.updatedtrainingapp.fragments.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.dataBase.Constants
import com.example.updatedtrainingapp.dataBase.ExerciseViewModel
import com.example.updatedtrainingapp.dataBase.TrainingViewModel
import com.example.updatedtrainingapp.dataBase.objects.ExerciseObject
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val exerciseViewModel: ExerciseViewModel,
    private val trainingViewModel: TrainingViewModel
) : ViewModel() {

    public fun databaseLoading() {
        if (!MySharedPreferences.isInside(SplashFragment::class.java.simpleName)) {
            putInDB()

            MySharedPreferences.saveString(
                SplashFragment::class.java.simpleName,
                SplashFragment::class.java.simpleName
            )

            trainingViewModel.insertTraining(TrainingObject(null, Constants.DEFAULT_SET))
            Log.i("Inserted", "inserted")
        } else {
            Log.i("Inserted", "not")
        }
    }

    private fun putInDB() {
        exerciseViewModel.insertExercise(
            ExerciseObject(
                null,
                "Biceps Curls",
                Constants.BICEPS_GROUP,
                R.drawable.ic_fitness_center_black_24dp.toString()
            )
        )
        exerciseViewModel.insertExercise(
            ExerciseObject(
                null,
                "Shoulder Press",
                Constants.SHOULDERS_GROUP,
                R.drawable.ic_fitness_center_black_24dp.toString()
            )
        )
        exerciseViewModel.insertExercise(
            ExerciseObject(
                null,
                "Triceps Press",
                Constants.TRICEPS_GROUP,
                R.drawable.ic_fitness_center_black_24dp.toString()
            )
        )
        exerciseViewModel.insertExercise(
            ExerciseObject(
                null,
                "Pull ups",
                Constants.BACK_GROUP,
                R.drawable.ic_fitness_center_black_24dp.toString()
            )
        )
        exerciseViewModel.insertExercise(
            ExerciseObject(
                null,
                "Bench Press",
                Constants.CHEST_GROUP,
                R.drawable.ic_fitness_center_black_24dp.toString()
            )
        )
        exerciseViewModel.insertExercise(
            ExerciseObject(
                null,
                "Squats",
                Constants.LEGS_GROUP,
                R.drawable.ic_fitness_center_black_24dp.toString()
            )
        )
        exerciseViewModel.insertExercise(
            ExerciseObject(
                null,
                "Curls",
                Constants.ABS_GROUP,
                R.drawable.ic_fitness_center_black_24dp.toString()
            )
        )
    }
}