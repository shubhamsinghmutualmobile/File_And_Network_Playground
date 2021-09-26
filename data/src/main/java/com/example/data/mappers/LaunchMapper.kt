package com.example.data.mappers

import android.content.Context
import com.example.LaunchListQuery.Launch
import java.io.File

fun Launch.toFile(context: Context): File = File(context.getExternalFilesDir(null), "GraphQL File #${this.id}.txt").also { it.writeText(this.site ?:
"") }