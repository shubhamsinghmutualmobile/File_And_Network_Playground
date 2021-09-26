package com.example.filenetworkplayground.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetLaunchesFilesUseCase
import com.example.domain.utils.SafeResult
import com.example.filenetworkplayground.ui.main.LaunchStates.Empty
import com.example.filenetworkplayground.ui.main.LaunchStates.Failure
import com.example.filenetworkplayground.ui.main.LaunchStates.Loading
import com.example.filenetworkplayground.ui.main.LaunchStates.NetworkError
import com.example.filenetworkplayground.ui.main.LaunchStates.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
  private val getLaunchesFilesUseCase: GetLaunchesFilesUseCase
) : ViewModel() {

  private val _launchesFlow: MutableStateFlow<LaunchStates> = MutableStateFlow(Empty)
  val launchesFlow: StateFlow<LaunchStates> = _launchesFlow

  init {
    getListOfFilesFromApi()
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
}