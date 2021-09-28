package com.example.data.injection

import android.content.Context
import com.apollographql.apollo.ApolloClient
import com.example.data.repositories.LaunchesRepo
import com.example.data.repositories.RouteGuideRepo
import com.example.domain.repositories.ILaunchesRepo
import com.example.domain.repositories.IRouteGuideRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.grpc.examples.routeguide.RouteGuideGrpcKt.RouteGuideCoroutineStub
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

  @Provides
  @Singleton
  fun providesLaunchesRepo(
    apolloClient: ApolloClient,
    @ApplicationContext context: Context
  ): ILaunchesRepo = LaunchesRepo(apolloClient, context)

  @Provides
  @Singleton
  fun providesRouteGuideRepo(
    routeGuideCoroutineStub: RouteGuideCoroutineStub,
    @ApplicationContext context: Context
  ): IRouteGuideRepo = RouteGuideRepo(routeGuideCoroutineStub, context)
}