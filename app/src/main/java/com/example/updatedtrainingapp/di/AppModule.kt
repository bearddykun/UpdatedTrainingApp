package com.example.updatedtrainingapp.di

import android.app.Application
import android.media.SoundPool
import com.example.updatedtrainingapp.dataBase.TrainingDatabase
import com.example.updatedtrainingapp.dataBase.dao.ExerciseDao
import com.example.updatedtrainingapp.dataBase.dao.ExerciseInfoDao
import com.example.updatedtrainingapp.dataBase.dao.TrainingDao
import com.example.updatedtrainingapp.dataBase.repositories.ExerciseInfoRepository
import com.example.updatedtrainingapp.dataBase.repositories.ExerciseRepository
import com.example.updatedtrainingapp.dataBase.repositories.TrainingRepository
import com.example.updatedtrainingapp.utils.SoundManager
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
    fun provideExerciseInfoDao(): ExerciseInfoDao =
        TrainingDatabase.getDatabase(application)!!.exerciseInfoDao()

    @Provides
    @Singleton
    fun providesTrainingRepository(trainingDao: TrainingDao): TrainingRepository =
        TrainingRepository(trainingDao)

    @Provides
    @Singleton
    fun providesExerciseRepository(exerciseDao: ExerciseDao): ExerciseRepository =
        ExerciseRepository(exerciseDao)

    @Provides
    @Singleton
    fun providesExerciseInfoRepository(exerciseInfoDao: ExerciseInfoDao): ExerciseInfoRepository =
        ExerciseInfoRepository(exerciseInfoDao)

    @Provides
    @Singleton
    fun providesSoundManager(): SoundManager =
        SoundManager(SoundPool.Builder().setMaxStreams(10).build())
}