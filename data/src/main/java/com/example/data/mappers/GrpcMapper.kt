package com.example.data.mappers

import android.content.Context
import io.grpc.examples.routeguide.Feature
import java.io.File
import java.util.UUID

fun Feature.toFile(context: Context) =
  File(context.getExternalFilesDir(null), "Grpc file #${UUID.randomUUID()}").apply { writeText(this@toFile.name) }