package com.example.data.injection

import com.apollographql.apollo.ApolloClient
import com.example.data.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun providesApolloClient(): ApolloClient = ApolloClient.builder()
    .serverUrl(BASE_URL)
    .build()

}