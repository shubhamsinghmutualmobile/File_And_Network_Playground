package com.example.filenetworkplayground.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetLaunchesFilesUseCase
import com.example.domain.usecases.GetListOfFeaturesUseCase
import com.example.domain.utils.SafeResult
import com.example.filenetworkplayground.ui.main.LaunchStates.Empty
import com.example.filenetworkplayground.ui.main.LaunchStates.Failure
import com.example.filenetworkplayground.ui.main.LaunchStates.Loading
import com.example.filenetworkplayground.ui.main.LaunchStates.NetworkError
import com.example.filenetworkplayground.ui.main.LaunchStates.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
  private val getLaunchesFilesUseCase: GetLaunchesFilesUseCase,
  private val getListOfFeaturesUseCase: GetListOfFeaturesUseCase
) : ViewModel() {

  companion object {
    private const val TAG = "MainVM"
  }

  private val _launchesFlow: MutableStateFlow<LaunchStates> = MutableStateFlow(Empty)
  val launchesFlow: StateFlow<LaunchStates> = _launchesFlow

  private val _featuresFlow: MutableStateFlow<FeatureStates> = MutableStateFlow(FeatureStates.Empty)
  val featuresFlow: StateFlow<FeatureStates> = _featuresFlow

  init {
    getListOfFilesFromApi()
    getListOfFeaturesFromApi(409146138, -746188906, 0, 0)
  }

  private fun getListOfFilesFromApi() {
    viewModelScope.launch {

      _launchesFlow.emit(Loading)

      when (val result = getLaunchesFilesUseCase()) {
        is SafeResult.Success -> _launchesFlow.emit(Success(result.data))
        is SafeResult.Failure -> _launchesFlow.emit(Failure(result.message))
        is SafeResult.NetworkError -> _launchesFlow.emit(NetworkError)
      }
    }
  }

  private fun getListOfFeaturesFromApi(
    lowLat: Int,
    lowLong: Int,
    hiLat: Int,
    hiLong: Int
  ) {
    viewModelScope.launch {
      _featuresFlow.emit(FeatureStates.Loading)

      when (val result = getListOfFeaturesUseCase(lowLat, lowLong, hiLat, hiLong)) {
        is SafeResult.Success -> {
          result.data.collect {
            Log.d(TAG, "getListOfFeaturesFromApi: $it")
            _featuresFlow.emit(FeatureStates.Success(it))
          }
        }
        else -> _featuresFlow.emit(FeatureStates.NetworkError)
      }
    }
  }
}