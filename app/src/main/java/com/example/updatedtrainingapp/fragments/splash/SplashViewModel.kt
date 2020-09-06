package com.example.updatedtrainingapp.fragments.splash

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.dataBase.Constants
import com.example.updatedtrainingapp.dataBase.dbViewModels.ExerciseDBViewModel
import com.example.updatedtrainingapp.dataBase.dbViewModels.TrainingDBViewModel
import com.example.updatedtrainingapp.dataBase.objects.ExerciseObject
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject

class SplashViewModel @ViewModelInject constructor(
    private val exerciseDBViewModel: ExerciseDBViewModel,
    private val trainingViewModel: TrainingDBViewModel
) : ViewModel() {

    fun databaseLoading() {
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
        exerciseDBViewModel.insertExercise(
            ExerciseObject(
                null,
                "Biceps Curls",
                Constants.BICEPS_GROUP,
                R.drawable.ic_fitness_center_black_24dp.toString()
            )
        )
        exerciseDBViewModel.insertExercise(
            ExerciseObject(
                null,
                "Shoulder Press",
                Constants.SHOULDERS_GROUP,
                R.drawable.ic_fitness_center_black_24dp.toString()
            )
        )
        exerciseDBViewModel.insertExercise(
            ExerciseObject(
                null,
                "Triceps Press",
                Constants.TRICEPS_GROUP,
                R.drawable.ic_fitness_center_black_24dp.toString()
            )
        )
        exerciseDBViewModel.insertExercise(
            ExerciseObject(
                null,
                "Pull ups",
                Constants.BACK_GROUP,
                R.drawable.ic_fitness_center_black_24dp.toString()
            )
        )
        exerciseDBViewModel.insertExercise(
            ExerciseObject(
                null,
                "Bench Press",
                Constants.CHEST_GROUP,
                R.drawable.ic_fitness_center_black_24dp.toString()
            )
        )
        exerciseDBViewModel.insertExercise(
            ExerciseObject(
                null,
                "Squats",
                Constants.LEGS_GROUP,
                R.drawable.ic_fitness_center_black_24dp.toString()
            )
        )
        exerciseDBViewModel.insertExercise(
            ExerciseObject(
                null,
                "Curls",
                Constants.ABS_GROUP,
                R.drawable.ic_fitness_center_black_24dp.toString()
            )
        )
    }
}