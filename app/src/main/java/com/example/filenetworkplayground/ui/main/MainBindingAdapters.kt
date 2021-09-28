package com.example.filenetworkplayground.ui.main

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.example.filenetworkplayground.ui.main.LaunchStates.Loading

@BindingAdapter("launchLoadingState")
fun View.launchLoadingState(flowState: LaunchStates) {
  when (flowState) {
    is Loading -> this.isVisible = true
    else -> this.isVisible = false
  }
}

@BindingAdapter("featureLoadingState")
fun View.featureLoadingState(flowState: FeatureStates) {
  when (flowState) {
    is FeatureStates.Loading -> this.isVisible = true
    else -> this.isVisible = false
  }
}