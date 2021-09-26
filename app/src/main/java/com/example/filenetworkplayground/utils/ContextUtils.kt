package com.example.filenetworkplayground.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.shortToast(msg: String) {
  Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun View.indefiniteSnackBar(msg: String, action: () -> Unit) {
  Snackbar.make(this, msg, Snackbar.LENGTH_INDEFINITE).setAction("Ok") {action()}.show()
}

fun Int?.findPostFix() = if (this in 11..13) "th"
else when (this?.rem(10)) {
  1 -> "st"
  2 -> "nd"
  3 -> "rd"
  else -> "th"
}