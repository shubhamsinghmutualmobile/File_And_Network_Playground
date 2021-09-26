package com.example.data.injection

import com.example.domain.repositories.ILaunchesRepo
import com.example.domain.usecases.GetLaunchesFilesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

  @Provides
  @Singleton
  fun providesGetLaunchesFilesUseCase(launchesRepo: ILaunchesRepo): GetLaunchesFilesUseCase = GetLaunchesFilesUseCase(launchesRepo)

}