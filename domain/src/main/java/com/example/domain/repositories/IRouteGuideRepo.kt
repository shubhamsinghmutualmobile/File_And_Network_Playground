package com.example.domain.repositories

import com.example.domain.utils.SafeResult
import kotlinx.coroutines.flow.Flow
import java.io.File

interface IRouteGuideRepo {

  suspend fun getListOfFeaturesFromApi(
    lowLat: Int,
    lowLong: Int,
    hiLat: Int,
    hiLong: Int
  ): SafeResult<Flow<File>>
}