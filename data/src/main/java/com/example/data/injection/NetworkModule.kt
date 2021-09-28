package com.example.data.injection

import com.apollographql.apollo.ApolloClient
import com.example.data.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.examples.routeguide.RouteGuideGrpcKt.RouteGuideCoroutineStub
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun providesApolloClient(): ApolloClient = ApolloClient.builder()
    .serverUrl(BASE_URL)
    .build()

  @Provides
  @Singleton
  fun providesGrpcChannel(): ManagedChannel = ManagedChannelBuilder.forAddress("192.168.0.105", 8980)
    .usePlaintext()
    .executor(Dispatchers.Default.asExecutor())
    .build()

  @Provides
  @Singleton
  fun providesRoutineGuideCoroutineStub(channel: ManagedChannel): RouteGuideCoroutineStub = RouteGuideCoroutineStub(channel)
}