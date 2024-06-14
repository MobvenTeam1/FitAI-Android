package com.mobven.fitai.data.di

import com.mobven.fitai.data.repository.UserRepositoryImpl
import com.mobven.fitai.data.repository.WorkoutRepositoryImpl
import com.mobven.fitai.domain.repository.UserRepository
import com.mobven.fitai.domain.repository.WorkoutRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
abstract class RepositoryModule {

    @[Binds Singleton]
    abstract fun bindFitAIRepository(fitAIRepositoryImpl: UserRepositoryImpl) : UserRepository

    @[Binds Singleton]
    abstract fun bindWorkoutRepository(workoutRepositoryImpl: WorkoutRepositoryImpl) : WorkoutRepository

}