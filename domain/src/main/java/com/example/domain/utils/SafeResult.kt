package com.example.domain.utils

sealed class SafeResult <out T> {
  class Success<out T>(val data: T): SafeResult<T>()
  class Failure(val message: String): SafeResult<Nothing>()
  object NetworkError: SafeResult<Nothing>()
}
