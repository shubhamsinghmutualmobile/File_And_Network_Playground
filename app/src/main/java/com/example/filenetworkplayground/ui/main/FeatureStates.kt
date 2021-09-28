package com.example.filenetworkplayground.ui.main

import java.io.File

sealed class FeatureStates {
  object Empty : FeatureStates()
  object Loading : FeatureStates()
  class Success(val data: File) : FeatureStates()
  class Failure(val msg: String) : FeatureStates()
  object NetworkError : FeatureStates()
}
