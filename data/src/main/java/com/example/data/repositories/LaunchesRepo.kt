package com.example.data.repositories

import android.content.Context
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.example.LaunchListQuery
import com.example.data.mappers.toFile
import com.example.data.utils.safeApiCall
import com.example.domain.repositories.ILaunchesRepo
import com.example.domain.utils.SafeResult
import java.io.File

class LaunchesRepo(
  private val apolloClient: ApolloClient,
  private val context: Context
) : ILaunchesRepo {
  override suspend fun getLaunchesFromApi(): SafeResult<List<File>> {
    return when (val response = safeApiCall { apolloClient.query(LaunchListQuery()).await() }) {
      is SafeResult.Success -> SafeResult.Success(response.data.launches.launches.map {
        it?.toFile(context) ?: File(context.getExternalFilesDir(null), "")
      })
      is SafeResult.Failure -> SafeResult.Failure(response.message)
      is SafeResult.NetworkError -> SafeResult.NetworkError
    }
  }
}