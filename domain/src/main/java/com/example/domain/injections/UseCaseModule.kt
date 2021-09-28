package com.example.domain.injections

import com.example.domain.repositories.ILaunchesRepo
import com.example.domain.repositories.IRouteGuideRepo
import com.example.domain.usecases.GetLaunchesFilesUseCase
import com.example.domain.usecases.GetListOfFeaturesUseCase
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
  fun providesGetLaunchesFilesUseCase(launchesRepo: ILaunchesRepo) = GetLaunchesFilesUseCase(launchesRepo)

  @Provides
  @Singleton
  fun providesGetListOfFeaturesUseCase(repo: IRouteGuideRepo): GetListOfFeaturesUseCase = GetListOfFeaturesUseCase(repo)
}