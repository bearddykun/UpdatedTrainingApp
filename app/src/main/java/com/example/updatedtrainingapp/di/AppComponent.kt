package com.example.updatedtrainingapp.di

import com.example.updatedtrainingapp.MainActivity
import com.example.updatedtrainingapp.fragments.currentExercise.CurrentExerciseFragment
import com.example.updatedtrainingapp.fragments.exerciseChoice.ExerciseChoiceFragment
import com.example.updatedtrainingapp.fragments.mainMenu.MainMenuFragment
import com.example.updatedtrainingapp.fragments.newExercise.CreateNewExerciseFragment
import com.example.updatedtrainingapp.fragments.splash.SplashFragment
import com.example.updatedtrainingapp.fragments.trainingsChoice.CreateTrainingFragment
import com.example.updatedtrainingapp.fragments.trainingsChoice.TrainingsChoiceFragment
import com.example.updatedtrainingapp.fragments.training.TrainingFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModel::class])
interface AppComponent {

    fun inject(target: MainActivity)
    fun inject(target: SplashFragment)
    fun inject(target: MainMenuFragment)
    fun inject(target: TrainingFragment)
    fun inject(target: TrainingsChoiceFragment)
    fun inject(target: CreateTrainingFragment)
    fun inject(target: CreateNewExerciseFragment)
    fun inject(target: ExerciseChoiceFragment)
    fun inject(target: CurrentExerciseFragment)
}