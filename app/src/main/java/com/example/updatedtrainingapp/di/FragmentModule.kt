package com.example.updatedtrainingapp.di

import com.example.updatedtrainingapp.fragments.trainingChoice.TrainingChoiceFragment
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FragmentModule {

    @Provides
    @Singleton
    fun provideTrainingChoiceFragment(): TrainingChoiceFragment = TrainingChoiceFragment()
}