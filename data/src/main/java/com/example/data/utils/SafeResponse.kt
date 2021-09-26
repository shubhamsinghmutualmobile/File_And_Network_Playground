package com.example.data.utils

import com.apollographql.apollo.api.Response
import com.example.domain.utils.SafeResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

suspend fun <T> safeApiCall(
  dispatcher: CoroutineDispatcher = Dispatchers.IO,
  method: suspend () -> Response<T>
): SafeResult<T> {
  return withContext(dispatcher) {
    try {
      method().data?.let {
        SafeResult.Success(it)
      } ?: kotlin.run {
        SafeResult.Failure("")
      }
    } catch (e: Exception) {
      when (e) {
        is HttpException -> SafeResult.NetworkError
        else -> SafeResult.Failure("${e.localizedMessage}")
      }
    }
  }
}