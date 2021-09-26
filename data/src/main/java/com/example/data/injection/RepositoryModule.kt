package com.example.data.injection

import android.content.Context
import com.apollographql.apollo.ApolloClient
import com.example.data.repositories.LaunchesRepo
import com.example.domain.repositories.ILaunchesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

  @Provides
  @Singleton
  fun providesLaunchesRepo(apolloClient: ApolloClient, @ApplicationContext context: Context): ILaunchesRepo = LaunchesRepo(apolloClient, context)

}