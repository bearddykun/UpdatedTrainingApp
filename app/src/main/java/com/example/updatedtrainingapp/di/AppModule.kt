package com.example.updatedtrainingapp.di

import android.app.Application
import com.example.updatedtrainingapp.dataBase.TrainingDatabase
import com.example.updatedtrainingapp.dataBase.dao.ExerciseDao
import com.example.updatedtrainingapp.dataBase.dao.TrainingDao
import com.example.updatedtrainingapp.dataBase.repositories.TrainingRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {
    @Provides
    @Singleton
    fun provideContext(): Application = application

    @Provides
    @Singleton
    fun provideTrainingDao(): TrainingDao =
        TrainingDatabase.getDatabase(application)!!.trainingDao()

    @Provides
    @Singleton
    fun provideExerciseDao(): ExerciseDao =
        TrainingDatabase.getDatabase(application)!!.exerciseDao()

    @Provides
    @Singleton
    fun providesTrainingRepository(trainingDao: TrainingDao): TrainingRepository =
        TrainingRepository(trainingDao)
}