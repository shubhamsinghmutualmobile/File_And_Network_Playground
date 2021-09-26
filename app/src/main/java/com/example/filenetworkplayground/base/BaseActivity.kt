package com.example.filenetworkplayground.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.filenetworkplayground.BR

abstract class BaseActivity<B : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {
  protected lateinit var binding: B
  lateinit var viewModel: VM

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // Bind the view and bind the viewModel to layout
    bindContentView(layoutId())
  }

  private fun bindContentView(layoutId: Int) {
    binding = DataBindingUtil.setContentView(this, layoutId)
    viewModel = ViewModelProvider(this)[getViewModelClass()]
    with(binding) {
      setVariable(BR.viewModel, viewModel)
      lifecycleOwner = this@BaseActivity
    }
  }

  abstract fun getViewModelClass(): Class<VM>

  @LayoutRes protected abstract fun layoutId(): Int
}