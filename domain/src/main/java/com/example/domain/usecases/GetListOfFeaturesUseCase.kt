package com.example.domain.usecases

import com.example.domain.repositories.IRouteGuideRepo
import com.example.domain.utils.SafeResult
import kotlinx.coroutines.flow.Flow
import java.io.File

class GetListOfFeaturesUseCase(
  private val routeGuideRepo: IRouteGuideRepo
) {
  suspend operator fun invoke(
    lowLat: Int,
    lowLong: Int,
    hiLat: Int,
    hiLong: Int
  ): SafeResult<Flow<File>> = routeGuideRepo.getListOfFeaturesFromApi(lowLat, lowLong, hiLat, hiLong)
}