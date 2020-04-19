package com.example.updatedtrainingapp.di

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.updatedtrainingapp.dataBase.dbViewModels.ExerciseDBViewModel
import com.example.updatedtrainingapp.dataBase.dbViewModels.ExerciseInfoDBViewModel
import com.example.updatedtrainingapp.dataBase.dbViewModels.TrainingDBViewModel
import com.example.updatedtrainingapp.fragments.currentExercise.CurrentExerciseViewModel
import com.example.updatedtrainingapp.fragments.exerciseChoice.ExerciseChoiceViewModel
import com.example.updatedtrainingapp.fragments.mainMenu.MainMenuViewModel
import com.example.updatedtrainingapp.fragments.splash.SplashViewModel
import com.example.updatedtrainingapp.fragments.trainingsChoice.TrainingsChoiceViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModel {

    @Provides
    @Singleton
    fun provideSplashViewModel(
        exerciseDBViewModel: ExerciseDBViewModel,
        trainingViewModel: TrainingDBViewModel
    ): ViewModel = SplashViewModel(exerciseDBViewModel, trainingViewModel)

    @Provides
    @Singleton
    fun provideMainMenuViewModel(): ViewModel = MainMenuViewModel()

    @Provides
    @Singleton
    fun provideTrainingDayViewModel(application: Application): ViewModel =
        TrainingDBViewModel(
            application
        )

    @Provides
    @Singleton
    fun provideTrainingsChoiceViewModel(): ViewModel = TrainingsChoiceViewModel()

    @Provides
    @Singleton
    fun provideTrainingViewModel(application: Application): ViewModel =
        TrainingDBViewModel(
            application
        )

    @Provides
    @Singleton
    fun provideExerciseViewModel(application: Application): ViewModel =
        ExerciseDBViewModel(
            application
        )

    @Provides
    @Singleton
    fun provideExerciseChoiceViewModel(): ViewModel = ExerciseChoiceViewModel()

    @Provides
    @Singleton
    fun provideCurrentExerciseViewModel(
        trainingViewModel: TrainingDBViewModel,
        exerciseInfoDBViewModel: ExerciseInfoDBViewModel
    ): ViewModel = CurrentExerciseViewModel(trainingViewModel, exerciseInfoDBViewModel)
}