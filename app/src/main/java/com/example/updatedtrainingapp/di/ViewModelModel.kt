package com.example.updatedtrainingapp.di

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.updatedtrainingapp.dataBase.ExerciseViewModel
import com.example.updatedtrainingapp.dataBase.TrainingViewModel
import com.example.updatedtrainingapp.dataBase.repositories.TrainingRepository
import com.example.updatedtrainingapp.fragments.mainMenu.MainMenuViewModel
import com.example.updatedtrainingapp.fragments.splash.SplashViewModel
import com.example.updatedtrainingapp.fragments.trainingChoice.TrainingChoiceViewModel
import com.example.updatedtrainingapp.fragments.trainingDay.TrainingDayViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModel {

    @Provides
    @Singleton
    fun provideSplashViewModel(
        exerciseViewModel: ExerciseViewModel,
        trainingViewModel: TrainingViewModel
    ): ViewModel = SplashViewModel(exerciseViewModel, trainingViewModel)

    @Provides
    @Singleton
    fun provideMainMenuViewModel(): ViewModel = MainMenuViewModel()

    @Provides
    @Singleton
    fun provideTrainingDayViewModel(trainingRepository: TrainingRepository): ViewModel =
        TrainingDayViewModel(trainingRepository)

    @Provides
    @Singleton
    fun provideThisTrainingViewModel(): ViewModel = TrainingChoiceViewModel()

    @Provides
    @Singleton
    fun provideTrainingViewModel(application: Application): ViewModel =
        TrainingViewModel(application)

    @Provides
    @Singleton
    fun provideExerciseViewModel(application: Application): ViewModel =
        ExerciseViewModel(application)
}