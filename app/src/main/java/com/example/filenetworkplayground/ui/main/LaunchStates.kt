package com.example.filenetworkplayground.ui.main

import java.io.File

sealed class LaunchStates {
  object Empty: LaunchStates()
  object Loading: LaunchStates()
  class Success(val data: List<File>): LaunchStates()
  class Failure(val msg: String): LaunchStates()
  object NetworkError: LaunchStates()
}
