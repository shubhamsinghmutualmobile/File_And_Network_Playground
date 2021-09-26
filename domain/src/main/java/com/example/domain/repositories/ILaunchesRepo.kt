package com.example.domain.repositories

import com.example.domain.utils.SafeResult
import java.io.File

interface ILaunchesRepo {
  suspend fun getLaunchesFromApi(): SafeResult<List<File>>
}