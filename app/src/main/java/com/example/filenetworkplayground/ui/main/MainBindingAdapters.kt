package com.example.filenetworkplayground.ui.main

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.example.filenetworkplayground.ui.main.LaunchStates.Loading

@BindingAdapter("loadingState")
fun View.loadingState(flowState: LaunchStates) {
  when (flowState) {
    is Loading -> this.isVisible = true
    else -> this.isVisible = false
  }
}