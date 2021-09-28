package com.example.data.repositories

import android.content.Context
import com.example.data.mappers.toFile
import com.example.domain.repositories.IRouteGuideRepo
import com.example.domain.utils.SafeResult
import io.grpc.examples.routeguide.Point
import io.grpc.examples.routeguide.Rectangle
import io.grpc.examples.routeguide.RouteGuideGrpcKt.RouteGuideCoroutineStub
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.File

class RouteGuideRepo constructor(
  private val stub: RouteGuideCoroutineStub,
  private val context: Context
) : IRouteGuideRepo {

  companion object {
    private const val TAG = "RouteGuideRepo"
  }

  override suspend fun getListOfFeaturesFromApi(
    lowLat: Int,
    lowLong: Int,
    hiLat: Int,
    hiLong: Int
  ): SafeResult<Flow<File>> {
    val rectangle = Rectangle.newBuilder()
      .setLo(point(lowLat, lowLong))
      .setHi(point(hiLat, hiLong))
      .build()

    val request = stub.listFeatures(rectangle)

    return SafeResult.Success(data = request.map { it.toFile(context) })
  }

  private fun point(
    lat: Int,
    lon: Int
  ): Point = Point.newBuilder()
    .setLatitude(lat)
    .setLongitude(lon)
    .build()
}