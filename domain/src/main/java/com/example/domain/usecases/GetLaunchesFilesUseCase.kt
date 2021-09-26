package com.example.domain.usecases

import com.example.domain.repositories.ILaunchesRepo
import com.example.domain.utils.SafeResult
import java.io.File

class GetLaunchesFilesUseCase constructor(
  private val launchesRepo: ILaunchesRepo
) {
  suspend operator fun invoke(): SafeResult<List<File>> = launchesRepo.getLaunchesFromApi()
}