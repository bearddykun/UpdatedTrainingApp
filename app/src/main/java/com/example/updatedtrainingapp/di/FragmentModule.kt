package com.example.updatedtrainingapp.di

import com.example.updatedtrainingapp.fragments.trainingsChoice.TrainingsChoiceFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class FragmentModule {

    @Provides
    @Singleton
    fun provideTrainingChoiceFragment(): TrainingsChoiceFragment = TrainingsChoiceFragment()
}